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
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TDSListaVideosDAO implements ListaVideosDAO{

	
	
	
	// Constantes Atributos
	
	private static final String LISTA_VIDEOS = "ListaVideos";

	private static final String NOMBRE = "nombre";
	private static final String USUARIO = "usuario";
	private static final String VIDEOS = "videos";

	// Constructor
	private ServicioPersistencia servPersistencia;

	public TDSListaVideosDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public ListaVideos create(ListaVideos listaVideos) {
		Entidad entidadListaVideos = listaVideosToEntidad(listaVideos);
		entidadListaVideos = servPersistencia.registrarEntidad(entidadListaVideos);
		entidadListaVideos.setId(entidadListaVideos.getId());
		return entidadToListaVideos(entidadListaVideos);
	}
	
	// Metodos auxiliares
	
	
	private String obtenerCodigoVideos(List<Video> videos){
		if(videos.isEmpty()) {
			return "";
		}
		String lineas = "";
		for (Video video: videos) {
		lineas += video.getId() + " ";
		}
		return lineas.substring(0, lineas.length()-1);
	}
	private Entidad listaVideosToEntidad(ListaVideos listaVideos) {
		Entidad eListaVideos = new Entidad();
		eListaVideos.setNombre(LISTA_VIDEOS);

		List<Propiedad> propiedades = new ArrayList<Propiedad>(1);
		
		propiedades.add(new Propiedad(NOMBRE, listaVideos.getNombre()));
		propiedades.add(new Propiedad(VIDEOS, obtenerCodigoVideos(listaVideos.getVideos())));

		eListaVideos.setPropiedades(propiedades);
		
		return eListaVideos;
	}
	

	private List<Video> obtenerVideosCodigos(String videos_str){
		List<Video> videos = new LinkedList<Video>();
		if(videos_str!=null && !videos_str.isEmpty()) {
			StringTokenizer strTok = new StringTokenizer(videos_str, " ");
			while (strTok.hasMoreTokens()) {
				
				
				
				int id = Integer.valueOf((String)strTok.nextToken());
				Video video = FactoriaDAO.getInstance().getVideoDAO().getVideo(id);
				videos.add(video);
			}
		}
		
		return videos;
	}
	
	private ListaVideos entidadToListaVideos(Entidad eListaVideos) {
		
		if(eListaVideos==null)
			return null;

		String nombre = servPersistencia.recuperarPropiedadEntidad(eListaVideos, NOMBRE);


		List<Video> videos = obtenerVideosCodigos(servPersistencia.recuperarPropiedadEntidad(eListaVideos, VIDEOS));
		ListaVideos listaVideos = new ListaVideos(nombre);
		listaVideos.setId(eListaVideos.getId());
		listaVideos.setVideos(videos);

		return listaVideos;
	}
	

	
	@Override
	public ListaVideos get(int id) {
		Entidad eListaVideos = servPersistencia.recuperarEntidad(id);

		return entidadToListaVideos(eListaVideos);
	}
	
	@Override
	public List<ListaVideos> getListaVideos() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(LISTA_VIDEOS);

		List<ListaVideos> listasVideos = new LinkedList<ListaVideos>();
		for (Entidad eEtiqueta : entidades) {
			listasVideos.add(get(eEtiqueta.getId()));
		}

		return listasVideos;
	}

	@Override
	public void update(ListaVideos listaVideos) {
		

		Entidad eLista = servPersistencia.recuperarEntidad(listaVideos.getId());
		
		for(Propiedad prop:eLista.getPropiedades()){

			if(prop.getNombre().equals(NOMBRE)){
				prop.setValor(listaVideos.getNombre());
			}else if(prop.getNombre().equals(VIDEOS)) {
				prop.setValor(obtenerCodigoVideos(listaVideos.getVideos()));
			}

			
			servPersistencia.modificarPropiedad(prop);
			
		}
	}

	@Override
	public void delete(ListaVideos listaVideos) {

		Entidad eLista = servPersistencia.recuperarEntidad(listaVideos.getId());
		servPersistencia.borrarEntidad(eLista);
		
	}
	

	
}
