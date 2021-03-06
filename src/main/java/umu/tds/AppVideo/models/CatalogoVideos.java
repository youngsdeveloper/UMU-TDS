package umu.tds.AppVideo.models;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.VideoDAO;
import umu.tds.AppVideo.seeders.VideoSeeder;

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
		
		
		// SOLO TESTING
		// LLAMAR AL SEEDER DE ETIQUETAS PARA QUE CREE LAS ETIQUETAS (reseteadas a 0)
		
		VideoDAO videoDAO = FactoriaDAO.getInstance().getVideoDAO();
		List<Video> vids = videoDAO.getVideos();

		if(vids.isEmpty()) {
			
			CatalogoEtiquetas.getInstance(); // Force seed etiquetas
			
			VideoSeeder.getInstance().clearVideos();
			VideoSeeder.getInstance().seedVideos();
			// SOLO TESTING CREAR PRIMERA VEZ
			vids = videoDAO.getVideos();

		}
		
		
		

		for (Video video:vids) {
			videos.put(video.getVideoID(), video);
		}
	
	}
	
	public LinkedList<Video> getVideos() {
		return new LinkedList<Video>(videos.values());
	}
	
	public List<Video> getVideosMasVistos() {
		List<Video> vids = new LinkedList<Video>(videos.values());
		vids = vids
				.stream()
				.sorted(Comparator.comparingInt(Video::getNumReproducciones).reversed())
				.limit(10)
				.collect(Collectors.toList());
		return vids;
	}
	
	public void addVideo(Video video) {
		videos.put(video.getVideoID(), video);
	}
	
	public Video getUsuario(String videoID) {
		return videos.get(videoID);
	}
	
	public void updateVideo(Video video) {
		videos.put(video.getVideoID(), video);
	}

}
