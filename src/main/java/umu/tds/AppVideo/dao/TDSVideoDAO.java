package umu.tds.AppVideo.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.models.Etiqueta;
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
		if(etiquetas.isEmpty()) {
			return lineas;
		}
		for (Etiqueta etiqueta: etiquetas) {
		lineas += etiqueta.getNombre() + " ";
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
	
	private Set<Etiqueta> obtenerEtiquetasCodigos(String etiquetas_str){
		
		Set<Etiqueta> etiquetas = new HashSet<Etiqueta>();
		if(etiquetas_str==null) {
			return etiquetas;
		}
		StringTokenizer strTok = new StringTokenizer(etiquetas_str, " ");
		while (strTok.hasMoreTokens()) {
			
			// TODO: Obtener etiqueta. ??Esta bien hacerlo asi?
			// TODO: Revisar CatalogoEtiquetas. Usar en vez de un catalogo un Pool de Objetos.
			
			
			Etiqueta e = new Etiqueta(strTok.nextToken());
			etiquetas.add(e);
		}
		return etiquetas;
	}
	
	private Video entidadToVideo(Entidad eVideo) {

		if(eVideo==null) {
			return null;
		}
		
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, TITULO);
		String videoID = servPersistencia.recuperarPropiedadEntidad(eVideo, VIDEO_ID);
		String numReproducciones = servPersistencia.recuperarPropiedadEntidad(eVideo, NUM_REPRODUCCIONES);
		String etiquetas = servPersistencia.recuperarPropiedadEntidad(eVideo, ETIQUETAS);
		
		int nReproducciones = 0;
		if(numReproducciones!=null){
			nReproducciones = Integer.parseInt(numReproducciones);
		}

		Video video = new Video(titulo,  obtenerEtiquetasCodigos(etiquetas), videoID, nReproducciones);
		
		
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

	@Override
	public void clearAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(VIDEO);
		for (Entidad eEtiqueta : entidades) {
			servPersistencia.borrarEntidad(eEtiqueta);
		}
	}

	@Override
	public Video getVideo(int id){
		return entidadToVideo(servPersistencia.recuperarEntidad(id));
	}
	
	@Override
	public void insertEtiqueta(Video video, Etiqueta etiqueta) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getId());
		
		
		for(Propiedad propiedad:eVideo.getPropiedades()) {
			if(propiedad.getNombre().equals(ETIQUETAS)) {
				
				
				String etiquetas = propiedad.getValor();
						

				Set<Etiqueta> etiquetas_list = obtenerEtiquetasCodigos(etiquetas).stream().collect(Collectors.toSet());
				etiquetas_list.add(etiqueta);
				
				String new_etiquetas = obtenerCodigosEtiquetas(etiquetas_list.stream().collect(Collectors.toList()));

				propiedad.setValor(new_etiquetas);
				
				servPersistencia.modificarPropiedad(propiedad);

			}
		}
		
		
		
	}

	@Override
	public void update(Video video) {

		Entidad eVideo = servPersistencia.recuperarEntidad(video.getId());
		
		for(Propiedad prop:eVideo.getPropiedades()){

			if(prop.getNombre().equals(TITULO)){
				prop.setValor(video.getTitulo());
			}else if(prop.getNombre().equals(VIDEO_ID)){
				prop.setValor(video.getVideoID());
			}else if(prop.getNombre().equals(NUM_REPRODUCCIONES)){
				prop.setValor(String.valueOf(video.getNumReproducciones()));
			}else if(prop.getNombre().equals(ETIQUETAS)){
				prop.setValor(obtenerCodigosEtiquetas(video.getEtiquetas()));
			}
			

			
			servPersistencia.modificarPropiedad(prop);
			
		}
	}

	
}
