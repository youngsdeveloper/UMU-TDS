package umu.tds.AppVideo.gui;

import javax.swing.table.AbstractTableModel;

public class TableModelVideo extends AbstractTableModel {

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getValueAt(int y, int x) {

		int numVideo = (x+1) + (y*getColumnCount());
		
		return "Video " + numVideo;
	}

}
