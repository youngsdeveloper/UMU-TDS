package umu.tds.AppVideo.dao;

import java.util.List;

import umu.tds.AppVideo.models.Etiqueta;

public interface EtiquetaDAO {
	void create(Etiqueta etiqueta);
	List<Etiqueta> getEtiquetas();
}
