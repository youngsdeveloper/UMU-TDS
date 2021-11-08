package umu.tds.AppVideo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.models.Usuario;

public class TDSUsuarioDAO implements UsuarioDAO{

	
	// Constantes Atributos
	
	private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String EMAIL = "email";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
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

		eUsuario.setPropiedades(propiedades);
		
		return eUsuario;
	}

	
}
