package umu.tds.AppVideo.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;

public class CatalogoVideos {
	

	private static CatalogoVideos instance = null;

	private HashMap<String, Video> videos;

	
	public static CatalogoVideos getInstance(){
		if(instance==null) {
			instance = new CatalogoVideos();
		}
		return instance;
	}
	
	private CatalogoVideos() {
		videos = new HashMap<String, Video>();
		
		/*
		
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		
		List<Usuario> users = usuarioDAO.getUsuarios();
		for (Usuario usuario:users) {
			usuarios.put(usuario.getUsername(), usuario);
		}
		*/
	}
	
	public LinkedList<Video> getVideos() {
		return new LinkedList<Video>(videos.values());
	}	
	
	public void addVideo(Video video) {
		videos.put(video.getVideoID(), video);
	}
	
	public Video getUsuario(String videoID) {
		return videos.get(videoID);
	}

}
