package umu.tds.AppVideo.dao;


public final class TDSFactoriaDAO extends FactoriaDAO{

	
	public TDSFactoriaDAO(){}

	@Override
	public TDSUsuarioDAO getUsuarioDAO() {
		return new TDSUsuarioDAO();
	}

}
