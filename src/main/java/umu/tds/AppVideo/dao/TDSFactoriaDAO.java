package umu.tds.AppVideo.dao;


public final class TDSFactoriaDAO extends FactoriaDAO{

	
	public TDSFactoriaDAO(){}

	@Override
	public TDSUsuarioDAO getUsuarioDAO() {
		return new TDSUsuarioDAO();
	}

	@Override
	public VideoDAO getVideoDAO() {
		return new TDSVideoDAO();
	}

	@Override
	public EtiquetaDAO getEtiquetaDAO() {
		return new TDSEtiquetaDAO();
	}

	@Override
	public ListaVideosDAO getListasDAO() {
		return new TDSListaVideosDAO();
	}

}
