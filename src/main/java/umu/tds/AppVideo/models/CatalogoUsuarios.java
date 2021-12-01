package umu.tds.AppVideo.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;



public class CatalogoUsuarios {
	private static CatalogoUsuarios instance = null;

	private HashMap<String, Usuario> usuarios;

	
	public static CatalogoUsuarios getInstance(){
		if(instance==null) {
			instance = new CatalogoUsuarios();
		}
		return instance;
	}
	
	private CatalogoUsuarios() {
		usuarios = new HashMap<String, Usuario>();
		
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		
		List<Usuario> users = usuarioDAO.getUsuarios();
		for (Usuario usuario:users) {
			usuarios.put(usuario.getUsername(), usuario);
		}
	}
	
	public LinkedList<Usuario> getUsuarios() {
		return new LinkedList<Usuario>(usuarios.values());
	}
	
	
	
	public void addUsuario(Usuario usuario) {
		usuarios.put(usuario.getUsername(), usuario);
	}
	
	public Usuario getUsuario(String username) {
		return usuarios.get(username);
	}
	

	
}
