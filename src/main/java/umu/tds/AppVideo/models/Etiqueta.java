package umu.tds.AppVideo.models;

public class Etiqueta {
	
	// Atributos
	private int id;
	private String nombre;
	
	// Constructor
	public Etiqueta(String nombre) {
		this.id = 0;
		this.nombre = nombre;
	}
	
	// Getters & Setters
	public String getNombre() {
		return nombre;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
