package umu.tds.AppVideo.filtros;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class FiltroMisListas implements Filtro {

	@Override
	public Predicate<Video> getFilter(Usuario u) {
		// Ejemplo de filtro
		
		List<Video> videosLista =
				u
					.getListasVideos()
					.stream()
					.flatMap(lv -> lv.getVideos().stream())
					.collect(Collectors.toList());
					
		
		
		return v -> !videosLista.contains(v);
	}

}
