package umu.tds.AppVideo.filtros;

public final class FactoriaFiltroTDS extends FactoriaFiltro {

	@Override
	public FiltroNoFiltro getNoFiltro() {
		return new FiltroNoFiltro();
	}

	@Override
	public FiltroMisListas getFiltroMisListas() {
		return new FiltroMisListas();
	}

}
