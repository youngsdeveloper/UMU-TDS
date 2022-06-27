package umu.tds.AppVideo.filtros;

import java.util.function.Predicate;

import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public interface Filtro {

	Predicate<Video> getFilter(Usuario u);
	
}

