package umu.tds.AppVideo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Usuario;

public class TDSListaVideosDAO implements ListaVideosDAO{

	
	
	
	// Constantes Atributos
	
	private static final String LISTA_VIDEOS = "ListaVideos";

	private static final String NOMBRE = "nombre";
	private static final String USUARIO = "usuario";
	private static final String VIDEOS = "vidoes";

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
	
	private Entidad listaVideosToEntidad(ListaVideos listaVideos) {
		Entidad eListaVideos = new Entidad();
		eListaVideos.setNombre(LISTA_VIDEOS);

		List<Propiedad> propiedades = new ArrayList<Propiedad>(1);
		
		propiedades.add(new Propiedad(NOMBRE, listaVideos.getNombre()));

		eListaVideos.setPropiedades(propiedades);
		
		return eListaVideos;
	}
	
	private ListaVideos entidadToListaVideos(Entidad eListaVideos) {
		
		if(eListaVideos==null)
			return null;

		String nombre = servPersistencia.recuperarPropiedadEntidad(eListaVideos, NOMBRE);

		ListaVideos listaVideos = new ListaVideos(nombre);
		listaVideos.setId(eListaVideos.getId());

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
	

	
}
