package umu.tds.AppVideo.controlador;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;
import umu.tds.AppVideo.events.UsuarioLoggedListener;
import umu.tds.AppVideo.models.CatalogoUsuarios;
import umu.tds.AppVideo.models.Usuario;

public class Controlador {

	
	private static Controlador instance = null;
	private Optional<Usuario> usuarioActual;
	
	// Evento onUsuarioLogged
	
	private List<UsuarioLoggedListener> usuarioLoggedListeners;

	private Controlador() {
		usuarioActual = Optional.empty();
		usuarioLoggedListeners = new LinkedList<UsuarioLoggedListener>();
	}

	public static Controlador getInstance() {
		if(instance==null)
			instance = new Controlador();
		
		return instance;
	}
	
	public Optional<Usuario> getUsuarioActual() {
		return usuarioActual;
	}
	
	
	public Optional<Usuario> registrarUsuario(String nombre, String apellidos,Date fechaNacimiento, String email, String username, String password) {

		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password);
		
		// DONE: Guardar Usuario en UsuarioDAO
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.create(usuario);
		
		// DONE: Guardar Usuario en CatalogoUsuarios
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.addUsuario(usuario);
		
		
		return Optional.of(usuario);
	}
	
	public void addUsuarioLoggedListener(UsuarioLoggedListener listener){
		this.usuarioLoggedListeners.add(listener);
	}
	
	private void fireUsuarioLoggedEvent(Usuario u){
		for(UsuarioLoggedListener l:usuarioLoggedListeners) {
			l.onUsuarioLogged(u);
		}
	}
	
	private void fireUsuarioLoggedOutEvent(Usuario u){
		for(UsuarioLoggedListener l:usuarioLoggedListeners) {
			l.onUsuarioLogout(u);
		}
	}
	
	public boolean loginUsuario(String username, String password) {
		
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		
		Usuario usuario = catalogoUsuario.getUsuario(username);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = Optional.of(usuario);
			fireUsuarioLoggedEvent(usuario);
			return true;
		}
		return false;
	}
	
	
	public boolean logout(){
	
		if(usuarioActual.isEmpty()) {
			return false; // Si no hay usuario actual, no se pude hacer logout.
		}
		
		Usuario usuario_copia = this.usuarioActual.get();
		this.usuarioActual = Optional.empty();
		fireUsuarioLoggedOutEvent(usuario_copia);

		return true;
	}
	
	
	
	
}
