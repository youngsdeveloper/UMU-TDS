package umu.tds.AppVideo.dao;

import java.util.List;

import umu.tds.AppVideo.models.Etiqueta;

public interface EtiquetaDAO {
	Etiqueta create(Etiqueta etiqueta);
	List<Etiqueta> getEtiquetas();
	void clearAll();
}
