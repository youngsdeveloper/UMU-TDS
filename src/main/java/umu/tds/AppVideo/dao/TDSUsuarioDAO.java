package umu.tds.AppVideo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TDSUsuarioDAO implements UsuarioDAO{

	
	// Constantes Atributos
	
	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String EMAIL = "email";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String LISTAS_VIDEOS = "listasVideos";
	private static final String VIDEOS_RECIENTES = "videosRecientes";
	private static final String PREMIUM = "premium";

	// Constructor
	private ServicioPersistencia servPersistencia;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public void create(Usuario usuario) {
		Entidad entidadUsuario = usuarioToEntidad(usuario);
		entidadUsuario = servPersistencia.registrarEntidad(entidadUsuario);
		usuario.setId(entidadUsuario.getId());
	}
	
	// Metodos auxiliares
	
	private String obtenerCodigosListas(List<ListaVideos> listasVideos){
		if(listasVideos.isEmpty()) {
			return "";
		}
		String lineas = "";
		for (ListaVideos listaVideos: listasVideos) {
		lineas += listaVideos.getId() + " ";
		}
		return lineas.substring(0, lineas.length()-1);
	}
	
	private String obtenerCodigosVideos(List<Video> videos){
		if(videos.isEmpty()) {
			return "";
		}
		String lineas = "";
		for (Video video: videos) {
		lineas += video.getId() + " ";
		}
		return lineas.substring(0, lineas.length()-1);
	}
	
	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		List<Propiedad> propiedades = new ArrayList<Propiedad>(6);
		
		propiedades.add(new Propiedad(NOMBRE, usuario.getNombre()));
		propiedades.add(new Propiedad(APELLIDOS, usuario.getApellidos()));
		propiedades.add(new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().toString()));
		propiedades.add(new Propiedad(EMAIL, usuario.getEmail().toString()));
		propiedades.add(new Propiedad(USERNAME, usuario.getUsername().toString()));
		propiedades.add(new Propiedad(PASSWORD, usuario.getPassword().toString()));
		propiedades.add(new Propiedad(LISTAS_VIDEOS, obtenerCodigosListas(usuario.getListasVideos())));
		propiedades.add(new Propiedad(VIDEOS_RECIENTES, obtenerCodigosVideos(usuario.getRecientes())));
		propiedades.add(new Propiedad(PREMIUM, usuario.isPremium() ? "true" : "false"));

		eUsuario.setPropiedades(propiedades);
		
		return eUsuario;
	}
	
	private List<ListaVideos> obtenerListasVideosCodigos(String listas_videos_str){
		

		
		List<ListaVideos> listasVideos = new LinkedList<ListaVideos>();
		if(listas_videos_str!=null && !listas_videos_str.isEmpty()) {
			StringTokenizer strTok = new StringTokenizer(listas_videos_str, " ");
			while (strTok.hasMoreTokens()) {
				
				
				
				int id = Integer.valueOf((String)strTok.nextToken());
				ListaVideos lista = FactoriaDAO.getInstance().getListasDAO().get(id);
				if(lista!=null)
					listasVideos.add(lista);
			}
		}
		
		return listasVideos;
	}
	
	private List<Video> obtenerVideos(String videos_str){
		List<Video> videos = new ArrayList<Video>();
		if(videos_str==null) {
			return videos;
		}
		StringTokenizer strTok = new StringTokenizer(videos_str, " ");
		while (strTok.hasMoreTokens()) {
			
			int id = Integer.valueOf((String)strTok.nextToken());
			Video video = FactoriaDAO.getInstance().getVideoDAO().getVideo(id);
			if(video!=null)
				videos.add(video);
		}
		return videos;
	}
	
	private Usuario entidadToUsuario(Entidad eUsuario) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		Date fechaNacimiento = new Date();
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String username = servPersistencia.recuperarPropiedadEntidad(eUsuario, USERNAME);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);	

		
		boolean premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM).equals("true")?true:false;
		List<ListaVideos> listasVideos = obtenerListasVideosCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAS_VIDEOS));

		List<Video> recientes = obtenerVideos(servPersistencia.recuperarPropiedadEntidad(eUsuario, VIDEOS_RECIENTES));

				
		Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, email, username, password,premium);
		usuario.setListasVideos(listasVideos);
		usuario.setRecientes(recientes);
		usuario.setId(eUsuario.getId());

		return usuario;
	}

	
	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}
	@Override
	public List<Usuario> getUsuarios() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}

	@Override
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		
		for(Propiedad prop:eUsuario.getPropiedades()){

			if(prop.getNombre().equals(NOMBRE)){
				prop.setValor(usuario.getNombre());
			}else if(prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			}else if(prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(usuario.getFechaNacimiento().toString());
			}else if(prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			}else if(prop.getNombre().equals(USERNAME)) {
				prop.setValor(usuario.getUsername());
			}else if(prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			}else if(prop.getNombre().equals(LISTAS_VIDEOS)) {
				prop.setValor(obtenerCodigosListas(usuario.getListasVideos()));
			}else if(prop.getNombre().equals(VIDEOS_RECIENTES)) {
				prop.setValor(obtenerCodigosVideos(usuario.getRecientes()));
			}else if(prop.getNombre().equals(PREMIUM)) {
				prop.setValor(usuario.isPremium()?"true":"false");
			}
			

			
			servPersistencia.modificarPropiedad(prop);
			
		}

	}

	
}
