package umu.tds.AppVideo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.models.CatalogoEtiquetas;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TDSVideoDAO implements VideoDAO{

	
	// Constantes Atributos
	
	private static final String VIDEO = "Video";

	private static final String TITULO = "titulo";
	private static final String VIDEO_ID = "videoID";
	private static final String NUM_REPRODUCCIONES = "numReproducciones";
	private static final String ETIQUETAS = "etiquetas";
	
	// Constructor
	private ServicioPersistencia servPersistencia;

	public TDSVideoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public void create(Video video) {
		Entidad entidadVideo = videoToEntidad(video);
		entidadVideo = servPersistencia.registrarEntidad(entidadVideo);
		video.setId(entidadVideo.getId());
	}
	
	// Metodos auxiliares
	
	private String obtenerCodigosEtiquetas(List<Etiqueta> etiquetas){
		String lineas = "";
		for (Etiqueta etiqueta: etiquetas) {
		lineas += etiqueta.getId() + " ";
		}
		return lineas.substring(0, lineas.length()-1);
	}
	
	private Entidad videoToEntidad(Video video) {
		Entidad eVideo = new Entidad();
		eVideo.setNombre(VIDEO);

		List<Propiedad> propiedades = new ArrayList<Propiedad>(6);
		
		propiedades.add(new Propiedad(TITULO, video.getTitulo()));
		propiedades.add(new Propiedad(VIDEO_ID, video.getVideoID()));
		propiedades.add(new Propiedad(NUM_REPRODUCCIONES, String.valueOf(video.getNumReproducciones())));
		propiedades.add(new Propiedad(ETIQUETAS, obtenerCodigosEtiquetas(video.getEtiquetas())));

		eVideo.setPropiedades(propiedades);
		
		return eVideo;
	}
	
	private List<Etiqueta> obtenerEtiquetasCodigos(String etiquetas_str){
		List<Etiqueta> etiquetas = new LinkedList<Etiqueta>();
		StringTokenizer strTok = new StringTokenizer(etiquetas_str, " ");
		while (strTok.hasMoreTokens()) {
			
			// TODO: Obtener etiqueta. ¿Esta bien hacerlo asi?
			CatalogoEtiquetas catalogoEtiquetas = CatalogoEtiquetas.getInstance();
			etiquetas.add(catalogoEtiquetas.getEtiqueta(Integer.valueOf((String)strTok.nextToken())));
		}
		return etiquetas;
	}
	
	private Video entidadToVideo(Entidad eVideo) {

		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, TITULO);
		String videoID = servPersistencia.recuperarPropiedadEntidad(eVideo, VIDEO_ID);
		String numReproducciones = servPersistencia.recuperarPropiedadEntidad(eVideo, NUM_REPRODUCCIONES);
		String etiquetas = servPersistencia.recuperarPropiedadEntidad(eVideo, ETIQUETAS);

		Video video = new Video(titulo,  obtenerEtiquetasCodigos(etiquetas), videoID, Integer.parseInt(numReproducciones));
		
		video.setId(eVideo.getId());

		return video;
	}

	
	public Video get(int id) {
		Entidad eVideo = servPersistencia.recuperarEntidad(id);

		return entidadToVideo(eVideo);
	}
	@Override
	public List<Video> getVideos() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(VIDEO);

		List<Video> videos = new LinkedList<Video>();
		for (Entidad eVideo : entidades) {
			videos.add(entidadToVideo(eVideo));
		}

		return videos;
	}

	
}
