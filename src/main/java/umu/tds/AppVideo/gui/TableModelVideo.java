package umu.tds.AppVideo.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import umu.tds.AppVideo.models.Video;

public class TableModelVideo extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Video> videos;
	
	public TableModelVideo(List<Video> videos){
		this.videos = videos;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		int rows = videos.size()/getColumnCount();
		if(videos.size() % getColumnCount() > 0) {
			rows++;
		}
		return rows;
	}

	@Override
	public Object getValueAt(int y, int x) {

		int index_video = (x) + (y * getColumnCount());
		
		if(index_video<videos.size()) {
			Video video = videos.get(index_video);
			return video;
		}
		
		return null;
		
	}

}
