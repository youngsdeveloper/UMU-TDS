package umu.tds.AppVideo.models;

import java.util.List;

public class Video {
	
	// Atributos
	private int id;
	
	private String titulo;
	private List<Etiqueta> etiquetas;
	private String videoID;
	private int numReproducciones;
	
	// Constructor
	public Video(String titulo, List<Etiqueta> etiquetas, String videoID, int numReproducciones) {
		this.id=0;
		this.titulo = titulo;
		this.etiquetas = etiquetas;
		this.videoID = videoID;
		this.numReproducciones = numReproducciones;
	}
	
	
	// Getters & Setters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}
	
	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	public String getURL() {
		return "https://www.youtube.com/watch?v=" + videoID;
	}
	
	public String getVideoID() {
		return videoID;
	}
	

}
