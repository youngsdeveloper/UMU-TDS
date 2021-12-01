package umu.tds.AppVideo.events;

import umu.tds.AppVideo.models.Usuario;

public interface UsuarioLoggedListener {
	void onUsuarioLogged(Usuario u);
	void onUsuarioLogout(Usuario u);

}
