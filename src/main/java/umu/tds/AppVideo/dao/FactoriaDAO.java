package umu.tds.AppVideo.dao;

import java.lang.reflect.InvocationTargetException;


public abstract class FactoriaDAO {
	
	public static final String DAO_TDS = "umu.tds.AppVideo.dao.TDSFactoriaDAO";

	
	private static FactoriaDAO instance = null;

	
	public static FactoriaDAO getInstance(String type) {
		
		if(instance==null) {
			try {
				instance = (FactoriaDAO) Class.forName(type).getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	public static FactoriaDAO getInstance(){
		return getInstance(DAO_TDS);
	}

	
	protected FactoriaDAO() {
		
	}
	
	// Metodos factoria para obtener adaptadores
	public abstract UsuarioDAO getUsuarioDAO();	

}
