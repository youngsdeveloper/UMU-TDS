package umu.tds.AppVideo.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Video {
	
	// Atributos
	private int id;
	
	private String titulo;
	private Set<Etiqueta> etiquetas;
	private String videoID;
	private int numReproducciones;
	
	// Constructor
	public Video(String titulo, Set<Etiqueta> etiquetas, String videoID, int numReproducciones) {
		this.id=0;
		this.titulo = titulo;
		this.etiquetas = etiquetas;
		this.videoID = videoID;
		this.numReproducciones = numReproducciones;
	}
	
	
	public void insertEtiqueta(Etiqueta etiqueta) {
		this.etiquetas.add(etiqueta);		
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
		return new LinkedList<Etiqueta>(etiquetas); //Devolvemos copia
	}
	
	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	public String getURL() {
		return "https://www.youtube.com/watch?v=" + videoID;
	}
	
	public String getThumbnailURL() {
		return "https://i3.ytimg.com/vi/" + getVideoID() + "/hqdefault.jpg";
	}
	
	public String getVideoID() {
		return videoID;
	}

	public void agregarReproduccion() {
		this.numReproducciones++;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", titulo=" + titulo + ", etiquetas=" + etiquetas + ", videoID=" + videoID
				+ ", numReproducciones=" + numReproducciones + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etiquetas == null) ? 0 : etiquetas.hashCode());
		result = prime * result + id;
		result = prime * result + numReproducciones;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((videoID == null) ? 0 : videoID.hashCode());
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
		Video other = (Video) obj;
		if (etiquetas == null) {
			if (other.etiquetas != null)
				return false;
		} else if (!etiquetas.equals(other.etiquetas))
			return false;
		if (id != other.id)
			return false;
		if (numReproducciones != other.numReproducciones)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (videoID == null) {
			if (other.videoID != null)
				return false;
		} else if (!videoID.equals(other.videoID))
			return false;
		return true;
	}
	
	
	
	
	

}
