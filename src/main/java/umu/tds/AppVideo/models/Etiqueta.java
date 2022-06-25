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

	// toString
	@Override
	public String toString() {
		return "Etiqueta [id=" + id + ", nombre=" + nombre + "]";
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
		Etiqueta other = (Etiqueta) obj;
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
