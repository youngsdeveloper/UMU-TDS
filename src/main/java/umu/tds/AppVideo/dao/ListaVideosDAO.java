package umu.tds.AppVideo.dao;

import java.util.List;

import umu.tds.AppVideo.models.ListaVideos;

public interface ListaVideosDAO {
	ListaVideos create(ListaVideos listaVideos);
	List<ListaVideos> getListaVideos();
	ListaVideos get(int id);
	void update(ListaVideos listaVideos);
	void delete(ListaVideos listaVideos);

}
