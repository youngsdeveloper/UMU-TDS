package umu.tds.AppVideo.seeders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import umu.tds.AppVideo.dao.EtiquetaDAO;
import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.VideoDAO;
import umu.tds.AppVideo.models.CatalogoEtiquetas;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Video;

public class VideoSeeder {

	
	private static VideoSeeder instance;
	
	private VideoDAO videoDAO;
	
	
	public static VideoSeeder getInstance() {
		if(instance==null) {
			instance = new VideoSeeder();
		}
		return instance;
	}
	private VideoSeeder() {
		videoDAO = FactoriaDAO.getInstance().getVideoDAO();
	}
	
	/**
	 * 
	 * Limpiar todas las etiquets insertadas en la base de datos.
	 * 
	 * */
	public void clearVideos(){
		videoDAO.clearAll();
	}
	
	public void seedVideos(){
		
		// Mapa de videos codigo-titulo
		Map<String, String> mapVideos = new HashMap<String, String>();
		mapVideos.put("y6120QOlsfU", "Darude - Sandstorm");
		mapVideos.put("ChxPDdw2Tto", "La velada del año 2");
		mapVideos.put("7q2VBGIKeYc", "Curso Java. Aplicaciones gráficas Swing I. Vídeo 55");
		mapVideos.put("Xkn0xiJKHXs", "La ruta para ser desarrollador web profesional en 2022 🛣️ (Completa)");
		mapVideos.put("JOV22n9ARg4", "El partido del ascenso del Real Murcia");
		mapVideos.put("ebQphhLpJG0", "KOTLIN: Curso ANDROID desde CERO para PRINCIPIANTES [2021]");
		mapVideos.put("cF8dcQHM3cY", "Mi libro, Luna de Plutón... (Versión Original)");
		mapVideos.put("HqoVuaR-WI0", "Facultad de Informática de la Universidad de Murcia");
		mapVideos.put("V8FJYwogIJ8", "OIRM Vive la experiencia");
		mapVideos.put("-F4A-GTQFEI", "Descubre la ciudad de Murcia");
		mapVideos.put("5SodqEc8mAc","Java vs Kotlin para Desarrollo Backend");

		
		for(String video_code:mapVideos.keySet()) {
			
			Set<Etiqueta> etiquetas = EtiquetaSeeder.getInstance().getSomeEtiquetas();
			
	        Random random = new Random();
	        int num_reps = random.ints(1, 20000).findFirst().getAsInt();
	        //num_reps initial to 0
	        num_reps = 0;
			
			Video video = new Video(mapVideos.get(video_code), etiquetas,  video_code, num_reps);
			
			videoDAO.create(video);
		}
	}
}
