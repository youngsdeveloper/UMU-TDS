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

public class TDSEtiquetaDAO implements EtiquetaDAO{

	
	// Constantes Atributos
	
	private static final String ETIQUETA = "Etiqueta";

	private static final String NOMBRE = "nombre";
	
	// Constructor
	private ServicioPersistencia servPersistencia;

	public TDSEtiquetaDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public void create(Etiqueta etiqueta) {
		Entidad entidadEtiqueta = etiquetaToEntidad(etiqueta);
		entidadEtiqueta = servPersistencia.registrarEntidad(entidadEtiqueta);
		etiqueta.setId(entidadEtiqueta.getId());
	}
	
	// Metodos auxiliares
	
	private Entidad etiquetaToEntidad(Etiqueta etiqueta) {
		Entidad eEtiqueta = new Entidad();
		eEtiqueta.setNombre(ETIQUETA);

		List<Propiedad> propiedades = new ArrayList<Propiedad>(1);
		
		propiedades.add(new Propiedad(NOMBRE, etiqueta.getNombre()));

		eEtiqueta.setPropiedades(propiedades);
		
		return eEtiqueta;
	}
	
	private Etiqueta entidadToEtiqueta(Entidad eEtiqueta) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eEtiqueta, NOMBRE);

		Etiqueta etiqueta = new Etiqueta(nombre);
		etiqueta.setId(eEtiqueta.getId());

		return etiqueta;
	}

	
	public Etiqueta get(int id) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(id);

		return entidadToEtiqueta(eEtiqueta);
	}
	
	@Override
	public List<Etiqueta> getEtiquetas() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(ETIQUETA);

		List<Etiqueta> etiquetas = new LinkedList<Etiqueta>();
		for (Entidad eEtiqueta : entidades) {
			etiquetas.add(get(eEtiqueta.getId()));
		}

		return etiquetas;
	}

	@Override
	public void clearAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(ETIQUETA);
		for (Entidad eEtiqueta : entidades) {
			servPersistencia.borrarEntidad(eEtiqueta);
		}
	}


	
}
