package umu.tds.AppVideo.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppVideo.dao.EtiquetaDAO;
import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;

public class CatalogoEtiquetas {
	

	private static CatalogoEtiquetas instance = null;

	private HashMap<Integer, Etiqueta> etiquetas;

	
	public static CatalogoEtiquetas getInstance(){
		if(instance==null) {
			instance = new CatalogoEtiquetas();
		}
		return instance;
	}
	
	private CatalogoEtiquetas() {
		etiquetas = new HashMap<Integer, Etiqueta>();
				
		EtiquetaDAO etiquetaDAO = FactoriaDAO.getInstance().getEtiquetaDAO();
		
		List<Etiqueta> etqs = etiquetaDAO.getEtiquetas();
		for (Etiqueta etiqueta:etqs) {
			etiquetas.put(etiqueta.getId(), etiqueta);
		}
		
	}
	
	public LinkedList<Etiqueta> getEtiquetas() {
		return new LinkedList<Etiqueta>(etiquetas.values());
	}	
	
	public void addEtiqueta(Etiqueta etiqueta) {
		etiquetas.put(etiqueta.getId(), etiqueta);
	}
	
	public Etiqueta getEtiqueta(Integer id) {
		return etiquetas.get(id);
	}

}
