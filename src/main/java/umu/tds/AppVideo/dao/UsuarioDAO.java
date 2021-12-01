package umu.tds.AppVideo.dao;

import java.util.List;

import umu.tds.AppVideo.models.Usuario;

public interface UsuarioDAO {
	void create(Usuario usuario);
	List<Usuario> getUsuarios();

}
