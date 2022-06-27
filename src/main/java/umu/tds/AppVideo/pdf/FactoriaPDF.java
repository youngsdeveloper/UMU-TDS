package umu.tds.AppVideo.pdf;

import java.lang.reflect.InvocationTargetException;


public abstract class FactoriaPDF {
	
	public static final String PDF_ITEXT = "umu.tds.AppVideo.pdf.iTEXTFactoriaPDF";

	
	private static FactoriaPDF instance = null;
	
	public static FactoriaPDF getInstance(String type) {
		
		if(instance==null) {
			try {
				instance = (FactoriaPDF) Class.forName(type).getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	public static FactoriaPDF getInstance(){
		return getInstance(PDF_ITEXT);
	}

	
	protected FactoriaPDF() {
		
	}
	
	// Metodos factoria para obtener adaptadores
	public abstract ListVideoPDF getListVideoPDF();	



}
