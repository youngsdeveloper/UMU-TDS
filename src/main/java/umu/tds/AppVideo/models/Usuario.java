package umu.tds.AppVideo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import umu.tds.AppVideo.filtros.FactoriaFiltro;
import umu.tds.AppVideo.filtros.Filtro;
import umu.tds.AppVideo.filtros.FiltroMisListas;
import umu.tds.AppVideo.filtros.FiltroNoFiltro;
import umu.tds.AppVideo.filtros.FiltroType;

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
	private List<Video> recientes;
	private boolean premium;
	private FiltroType filtroType;

	//Constructor
	public Usuario(String nombre, String apellidos, Date fechaNacimiento, String email, String username, String password, boolean premium) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.username = username;
		this.password = password;
		this.premium = premium;
		this.listasVideos = new ArrayList<ListaVideos>(10);
		this.recientes = new LinkedList<Video>(); 
		this.filtroType = FiltroType.NOFILTRO;
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
		return new LinkedList<ListaVideos>(listasVideos);
	}
	
	public void setListasVideos(List<ListaVideos> listasVideos) {
		this.listasVideos = listasVideos;
	}
	
	public void addListaToListaVideos(ListaVideos lista) {
		this.listasVideos.add(lista);
	}
	
	public void addVideoToListaVideos(Video v, ListaVideos lista){
		
		int index = this.listasVideos.indexOf(lista);
		this.listasVideos.get(index).insertarVideo(v);
		
	}
	
	public void addVideoToRecientes(Video v) {
		
		// Delete other recents vids
		if(this.recientes.contains(v)) {
			this.recientes.remove(this.recientes.indexOf(v));
		}
		
		this.recientes.add(0,v); //add to start
		
		// Max 10 recientes
		if(this.recientes.size()>10) {
			this.recientes.remove(this.recientes.size()-1);
		}
	}
	
	public List<Video> getRecientes() {
		return new LinkedList<>(recientes);
	}
	
	public void setRecientes(List<Video> recientes) {
		this.recientes = recientes;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public FiltroType getFiltroType() {
		return filtroType;
	}

	public void setFiltroType(FiltroType filtroType) {
		this.filtroType = filtroType;
	}
	
	public Predicate<Video> getFiltroVideo(){
		return getFiltro().getFilter(this);
	}
	
	public Filtro getFiltro() {
		switch(this.filtroType) {
			case FILTRO_MIS_LISTAS: return FactoriaFiltro.getInstance().getFiltroMisListas();
			case FILTRO_IMPOPULARES: return FactoriaFiltro.getInstance().getFiltroImpopulares();
			default: return FactoriaFiltro.getInstance().getNoFiltro();
		}
	}
	
	// toString


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", listasVideos=" + listasVideos + ", recientes=" + recientes + ", premium=" + premium + "]";
	}
	
	
	
	

}
