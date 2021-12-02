package umu.tds.AppVideo.dao;

import java.util.List;

import umu.tds.AppVideo.models.Video;

public interface VideoDAO {
	void create(Video video);
	List<Video> getVideos();
}
