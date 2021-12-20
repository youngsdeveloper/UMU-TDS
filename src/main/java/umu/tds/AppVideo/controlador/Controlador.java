package umu.tds.AppVideo.controlador;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;
import umu.tds.AppVideo.events.UsuarioLoggedListener;
import umu.tds.AppVideo.models.CatalogoEtiquetas;
import umu.tds.AppVideo.models.CatalogoUsuarios;
import umu.tds.AppVideo.models.CatalogoVideos;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

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

		
		// Comprobamos que el usuario no existe
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();

		if(catalogoUsuario.getUsuario(username).isPresent()) {
			// El usuario ya existe, no crear.
			return Optional.empty();
		}
		
		
		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password);
		
		// DONE: Guardar Usuario en UsuarioDAO
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.create(usuario);
		
		// DONE: Guardar Usuario en CatalogoUsuarios
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
		
		Optional<Usuario> usuario = catalogoUsuario.getUsuario(username);
				
		if(usuario.isPresent() && usuario.get().getPassword().equals(password)) {
			this.usuarioActual = usuario;
			fireUsuarioLoggedEvent(usuario.get());
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
	
	
	// Explorador
	
	public List<Etiqueta> getEtiquetas(){
		CatalogoEtiquetas catalogoEtiquetas = CatalogoEtiquetas.getInstance();
		return catalogoEtiquetas.getEtiquetas();
	}
	
	public List<Video> searchVideos(String q, Collection<String> etiquetasSeleccionadas){
		CatalogoVideos catalogoVideos = CatalogoVideos.getInstance();

		List<Video> videos = catalogoVideos.getVideos();
				
		
		
		List<Video> videosFiltered = videos
					.stream()
					.filter(video -> video.getTitulo().toLowerCase().contains(q.toLowerCase())) //Fix: Case insesitive search
					.filter(video -> video.getEtiquetas()
											.stream()
											.anyMatch(etiqueta -> etiquetasSeleccionadas.contains(etiqueta.getNombre())) || etiquetasSeleccionadas.size()==0)
					.collect(Collectors.toList());
		
		return videosFiltered;
	}
	
	
	
	
}
