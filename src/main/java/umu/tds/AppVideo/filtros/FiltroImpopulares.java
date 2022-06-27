package umu.tds.AppVideo.filtros;

import java.util.function.Predicate;

import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class FiltroImpopulares implements Filtro{

	@Override
	public Predicate<Video> getFilter(Usuario u) {
		return v -> v.getNumReproducciones()>=5;
	}

}
