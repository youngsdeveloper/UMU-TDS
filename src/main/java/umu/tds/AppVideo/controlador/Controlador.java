package umu.tds.AppVideo.controlador;

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
}