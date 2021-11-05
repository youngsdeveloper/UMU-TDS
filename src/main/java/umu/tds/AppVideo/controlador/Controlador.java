package umu.tds.AppVideo.controlador;

import java.util.Date;
import java.util.Optional;

import umu.tds.AppVideo.models.Usuario;

public class Controlador {

	
	private static Controlador instance = null;
	private Optional<Usuario> usuarioActual;

	private Controlador() {
		usuarioActual = Optional.empty();
	}

	public static Controlador getInstance() {
		return instance != null ? instance : new Controlador();
	}
	
	public Optional<Usuario> getUsuarioActual() {
		return usuarioActual;
	}
	
	
	public Optional<Usuario> registrarUsuario(String nombre, String apellidos,Date fechaNacimiento, String email, String username, String password) {

		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password);
		
		// TODO: Guardar Usuario en UsuarioDAO
		// TODO: Guardar Usuario en CatalogoUsuarios
		
		return Optional.of(usuario);
	}
}
