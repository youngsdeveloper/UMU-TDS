package umu.tds.AppVideo.controlador;

import java.util.Date;
import java.util.Optional;

import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;
import umu.tds.AppVideo.models.Usuario;

public class Controlador {

	
	private static Controlador instance = null;
	private Optional<Usuario> usuarioActual;

	private Controlador() {
		usuarioActual = Optional.empty();
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
		
		// TODO: Guardar Usuario en CatalogoUsuarios
		
		
		
		return Optional.of(usuario);
	}
}
