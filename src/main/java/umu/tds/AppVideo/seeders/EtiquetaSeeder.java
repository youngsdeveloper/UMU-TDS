package umu.tds.AppVideo.seeders;

import java.util.ArrayList;
import java.util.List;

import umu.tds.AppVideo.dao.EtiquetaDAO;
import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.models.CatalogoEtiquetas;
import umu.tds.AppVideo.models.Etiqueta;

public class EtiquetaSeeder {

	
	private static EtiquetaSeeder instance;
	
	private EtiquetaDAO etiquetaDAO;
	
	
	public static EtiquetaSeeder getInstance() {
		if(instance==null) {
			instance = new EtiquetaSeeder();
		}
		return instance;
	}
	private EtiquetaSeeder() {
		etiquetaDAO = FactoriaDAO.getInstance().getEtiquetaDAO();
	}
	
	/**
	 * 
	 * Limpiar todas las etiquets insertadas en la base de datos.
	 * 
	 * */
	public void clearEtiquetas(){
		etiquetaDAO = FactoriaDAO.getInstance().getEtiquetaDAO();
		etiquetaDAO.clearAll();
	}
	
	public void seedEtiquetas(){
		
		
		String[] etiquetas_str = {"Humor", "Educación", "Infantil", "Series", "Peliculas", "Estrenos", "Comedia", "Drama"};
		
		List<Etiqueta> etiquetas = new ArrayList<Etiqueta>(etiquetas_str.length);
		for(String etiqueta_str:etiquetas_str) {
			Etiqueta etiqueta = new Etiqueta(etiqueta_str);
			etiquetaDAO.create(etiqueta);
		}
	}
}
