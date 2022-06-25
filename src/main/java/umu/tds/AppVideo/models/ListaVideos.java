package umu.tds.AppVideo.models;

import java.util.LinkedList;
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
		this.videos = new LinkedList<Video>();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListaVideos other = (ListaVideos) obj;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	

	
		
}
