package umu.tds.AppVideo.dao;

import java.util.List;

import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Video;

public interface VideoDAO {
	void create(Video video);
	List<Video> getVideos();
	Video getVideo(int id);
	void update(Video video);

	void clearAll();
	
	void insertEtiqueta(Video video, Etiqueta etiqueta);
	
}
