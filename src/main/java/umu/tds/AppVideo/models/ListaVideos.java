package umu.tds.AppVideo.models;

import java.util.List;

public class ListaVideos {
	
	// Atributos
	private int id;
	private String nombre;
	private Usuario usuario;
	private List<Video> videos;
	
	// Constructor
	public ListaVideos(String nombre) {
		super();
		this.id = 0;
		this.nombre = nombre;
	}
	
	// Getters & Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "ListaVideos [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", videos=" + videos + "]";
	}
	
	
	

	
		
}
