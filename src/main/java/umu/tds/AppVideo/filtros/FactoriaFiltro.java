package umu.tds.AppVideo.filtros;

import java.lang.reflect.InvocationTargetException;


public abstract class FactoriaFiltro {

	public static final String TDS_FILTROS = "umu.tds.AppVideo.filtros.FactoriaFiltroTDS";

	
	private static FactoriaFiltro instance = null;
	
	public static FactoriaFiltro getInstance(String type) {
		
		if(instance==null) {
			try {
				instance = (FactoriaFiltro) Class.forName(type).getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	

	public static FactoriaFiltro getInstance(){
		return getInstance(TDS_FILTROS);
	}

	
	protected FactoriaFiltro() {
		
	}
	
	// Metodos factoria para obtener adaptadores
	public abstract FiltroNoFiltro getNoFiltro();	
	public abstract FiltroMisListas getFiltroMisListas();	
	public abstract FiltroImpopulares getFiltroImpopulares();	

}
