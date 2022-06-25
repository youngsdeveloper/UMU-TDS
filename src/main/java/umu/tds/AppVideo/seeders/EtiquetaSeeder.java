package umu.tds.AppVideo.seeders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
		etiquetaDAO.clearAll();
	}
	
	public List<Etiqueta> getSomeEtiquetas() {
		List<Etiqueta> etiquetas = etiquetaDAO.getEtiquetas();
        Collections.shuffle(etiquetas);
        
        Random random = new Random();
        int last_index = random.ints(1, etiquetas.size()-2).findFirst().getAsInt();
        
        return etiquetas.subList(0, last_index);
	}
	
	public void seedEtiquetas(){
		
		
		String[] etiquetas_str = {"Humor", "Educación", "Infantil", "Series", "Peliculas", "Estrenos", "Comedia", "Drama"};
		
		for(String etiqueta_str:etiquetas_str) {
			Etiqueta etiqueta = new Etiqueta(etiqueta_str);
			etiquetaDAO.create(etiqueta);
		}
	}
}
