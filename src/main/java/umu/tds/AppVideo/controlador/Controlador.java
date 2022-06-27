package umu.tds.AppVideo.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import umu.tds.AppVideo.dao.EtiquetaDAO;
import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.ListaVideosDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;
import umu.tds.AppVideo.dao.VideoDAO;
import umu.tds.AppVideo.events.EtiquetaInsertedListener;
import umu.tds.AppVideo.events.UsuarioLoggedListener;
import umu.tds.AppVideo.events.UsuarioUpdatedListener;
import umu.tds.AppVideo.models.CatalogoEtiquetas;
import umu.tds.AppVideo.models.CatalogoUsuarios;
import umu.tds.AppVideo.models.CatalogoVideos;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;
import umu.tds.AppVideo.pdf.FactoriaPDF;

public class Controlador {

	public enum Environment{PRODUCTION,WBUILDER};
	
	public Environment env = Environment.WBUILDER;
	
	private static Controlador instance = null;
	private Optional<Usuario> usuarioActual;
	
	// Listeners
	
	private List<UsuarioLoggedListener> usuarioLoggedListeners;
	private List<UsuarioUpdatedListener> usuarioUpdatedListeners;
	private List<EtiquetaInsertedListener> etiquetaInsertedListeners;

	private Controlador() {
		usuarioActual = Optional.empty();
		usuarioLoggedListeners = new LinkedList<UsuarioLoggedListener>();
		usuarioUpdatedListeners = new LinkedList<UsuarioUpdatedListener>();
		etiquetaInsertedListeners = new LinkedList<EtiquetaInsertedListener>();
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
		
		
		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password, false);
		
		// DONE: Guardar Usuario en UsuarioDAO
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.create(usuario);
		
		// DONE: Guardar Usuario en CatalogoUsuarios
		catalogoUsuario.addUsuario(usuario);
		
		
		return Optional.of(usuario);
	}
	
	
	//Eventos
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
	
	public void addUsuarioUpdatedListener(UsuarioUpdatedListener listener){
		this.usuarioUpdatedListeners.add(listener);
	}
	
	private void fireUsuarioUpdatedEvent(Usuario u){
		for(UsuarioUpdatedListener l:usuarioUpdatedListeners) {
			l.onUsuarioUpdated(u);
		}
	}
	
	public void addEtiquetaInsertedListener(EtiquetaInsertedListener listener){
		this.etiquetaInsertedListeners.add(listener);
	}
	
	private void fireEtiquetaInsertedEvent(Etiqueta e){
		for(EtiquetaInsertedListener l:etiquetaInsertedListeners) {
			l.onNewEtiqueta(e);
		}
	}
	//Fin eventos
	
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
	
	

	public List<Video> getVideos(){
		CatalogoVideos catalogoVideos = CatalogoVideos.getInstance();

		List<Video> videos = catalogoVideos.getVideos();
		
		return videos;
	}
	
	public List<Video> searchVideos(String q, Optional<Collection<String>> etiquetasSeleccionadas){
		CatalogoVideos catalogoVideos = CatalogoVideos.getInstance();

		List<Video> videos = catalogoVideos.getVideos();
				
		
		
		Stream<Video> stream = videos
					.stream()
					.filter(video -> video.getTitulo().toLowerCase().contains(q.toLowerCase()));//Fix: Case insesitive search;
		
		
				
		
		if(etiquetasSeleccionadas.isPresent()) {
			
			Collection<String> etiquetas = etiquetasSeleccionadas.get();
			
			stream = stream.filter(video -> video.getEtiquetas()
						.stream()
						.anyMatch(etiqueta -> etiquetas.contains(etiqueta.getNombre()) || etiquetas.size()==0));

		}

		
		return stream.collect(Collectors.toList());
	}
	
	public List<ListaVideos> getListasUsuario(){
		
		if(usuarioActual.isEmpty()) {
			return new ArrayList<ListaVideos>();
		}
		return usuarioActual
				.get()
				.getListasVideos();
	}
	
	public Optional<ListaVideos> getLista(String nombre){
		return 
				getListasUsuario()
				.stream()
				.filter(lista -> lista.getNombre().equals(nombre))
				.findFirst();
	}
	
	public ListaVideos registrarLista(String nombre){
		
		if(usuarioActual.isEmpty()){
			return null; 
		}
		
		ListaVideos lista = new ListaVideos(nombre);

		// Insertamos la lista en el DAO
		ListaVideosDAO listaDAO = FactoriaDAO.getInstance().getListasDAO();
		lista = listaDAO.create(lista);
		
		usuarioActual.get().addListaToListaVideos(lista);
		
		// Actualizamos el usuario actual en el catalogo
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.updateUsuario(usuarioActual.get());
		
		// Actualizamos el usuario actual en el DAO


		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.update(usuarioActual.get());

		return lista;
	}
	
	public ListaVideos insertarVideoLista(ListaVideos lista, Video video){
		
		if(usuarioActual.isEmpty()){
			return null; 
		}
		
		usuarioActual.get().addVideoToListaVideos(video, lista);

		
		// Insertamos la lista en el DAO
		ListaVideosDAO listaDAO = FactoriaDAO.getInstance().getListasDAO();
		listaDAO.update(lista);
		
		
		// Actualizamos el usuario actual en el catalogo
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.updateUsuario(usuarioActual.get());
		
		// Actualizamos el usuario actual en el DAO
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.update(usuarioActual.get());
		
		// Notificamos actualizacion usuario actual
		fireUsuarioUpdatedEvent(usuarioActual.get());

		return lista;
	}
	
	public ListaVideos updateLista(ListaVideos lista){
		
		if(usuarioActual.isEmpty()){
			return null; 
		}
		
		// Insertamos la lista en el DAO
		ListaVideosDAO listaDAO = FactoriaDAO.getInstance().getListasDAO();
		listaDAO.update(lista);
		
		
		int index = usuarioActual.get().getListasVideos().indexOf(lista);
		usuarioActual.get().getListasVideos().set(index, lista);
		
		// Actualizamos el usuario actual en el catalogo
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.updateUsuario(usuarioActual.get());
		
		// Actualizamos el usuario actual en el DAO
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.update(usuarioActual.get());
		
		// Notificamos actualizacion usuario actual
		fireUsuarioUpdatedEvent(usuarioActual.get());

		return lista;
	}
	
	public void deleteLista(ListaVideos lista) {
		if(usuarioActual.isEmpty()){
			return; 
		}
		
		// Eliminamos de la lista en el DAO
		ListaVideosDAO listaDAO = FactoriaDAO.getInstance().getListasDAO();
		listaDAO.delete(lista);
		
		usuarioActual.get().getListasVideos().remove(lista);
		
		// Actualizamos el usuario actual en el catalogo
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.updateUsuario(usuarioActual.get());
		
		// Actualizamos el usuario actual en el DAO
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.update(usuarioActual.get());
		
		// Notificamos actualizacion usuario actual
		fireUsuarioUpdatedEvent(usuarioActual.get());
		
	}
	
	public Video insertarEtiquetaVideo(Etiqueta etiqueta, Video video){
		
		
		if(video.getEtiquetas().contains(etiqueta))
			return null; //No añadir misma etiqueta 2 veces.
		
		EtiquetaDAO etiquetaDAO = FactoriaDAO.getInstance().getEtiquetaDAO();
		
		
		List<Etiqueta> etiquetas = etiquetaDAO.getEtiquetas();
		
		Etiqueta new_etiqueta; 
		if(etiquetas.contains(etiqueta)) {
			// Si existe la etiqueta, usar.
			
			
			new_etiqueta = etiquetas.get(etiquetas.indexOf(etiqueta));
		
			
		}else {
			// Crear etiqueta y luego usar.
			
			CatalogoEtiquetas.getInstance().addEtiqueta(etiqueta);
			
			new_etiqueta = etiquetaDAO.create(etiqueta);
			fireEtiquetaInsertedEvent(new_etiqueta);
		}
		
		VideoDAO videoDAO = FactoriaDAO.getInstance().getVideoDAO();
		videoDAO.insertEtiqueta(video, etiqueta);
		

		video.insertEtiqueta(new_etiqueta);
		
		CatalogoVideos.getInstance().updateVideo(video);
		
		return video;
		
	}
	
		
	public void addVideoRecientes(Video video){
			
	
		if(usuarioActual.isEmpty()){
			return; 
		}
		
		usuarioActual.get().addVideoToRecientes(video);
		
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.updateUsuario(usuarioActual.get());

		
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.update(usuarioActual.get());
		
		
		fireUsuarioUpdatedEvent(usuarioActual.get());
		
	}
	
	public Video sumarReproduccion(Video video) {
		video.agregarReproduccion();
		
		VideoDAO videoDAO = FactoriaDAO.getInstance().getVideoDAO();
		videoDAO.update(video);
		
		CatalogoVideos catalogoVideos = CatalogoVideos.getInstance();
		catalogoVideos.updateVideo(video);
		return video;
	}
	
	public void setPremium(boolean result) {
		if(usuarioActual.isEmpty()){
			return; 
		}
		
		usuarioActual.get().setPremium(result);
		
		CatalogoUsuarios catalogoUsuario = CatalogoUsuarios.getInstance();
		catalogoUsuario.updateUsuario(usuarioActual.get());

		
		UsuarioDAO usuarioDAO = FactoriaDAO.getInstance().getUsuarioDAO();
		usuarioDAO.update(usuarioActual.get());
		
		
		fireUsuarioUpdatedEvent(usuarioActual.get());
	}
	
	public List<Video> getVideosMasVistos(){
		return CatalogoVideos.getInstance().getVideosMasVistos();
	}
	
	public List<Video> getVideosRecientes(){
		if(usuarioActual.isEmpty()){
			return null; 
		}
		
		return usuarioActual
				.get()
				.getRecientes()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}
	
	public void generarPDF(){
		if(usuarioActual.isEmpty()){
			return; 
		}
		
		
		FactoriaPDF.getInstance().getListVideoPDF().create(usuarioActual.get());
	}
}


