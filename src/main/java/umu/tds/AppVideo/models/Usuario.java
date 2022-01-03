package umu.tds.AppVideo.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	// Atributos
	private int id;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private String email;
	private String username;
	private String password;
	private List<ListaVideos> listasVideos;
	
	//Constructor
	public Usuario(String nombre, String apellidos, Date fechaNacimiento, String email, String username, String password) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.username = username;
		this.password = password;
		this.listasVideos = new LinkedList<ListaVideos>();
	}
	
	// Getters & Setteres

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<ListaVideos> getListasVideos() {
		return listasVideos;
	}
	
	public void setListasVideos(List<ListaVideos> listasVideos) {
		this.listasVideos = listasVideos;
	}
	
	public void addListaToListaVideos(ListaVideos lista) {
		this.listasVideos.add(lista);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", listasVideos=" + listasVideos + "]";
	}
	
	
	
	

}
