package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TestListasUsuario {

	@Test
	public void test() {

		// Generamos un usuario y lo registramos
		String nombre = "name";
		
	    String randomMail = UUID.randomUUID().toString().replace("-", "")+"@um.es";
	    String randomUsername = UUID.randomUUID().toString().replace("-", "");
	    String randomPass = UUID.randomUUID().toString().replace("-", "");


		Optional<Usuario> user = Controlador.getInstance().registrarUsuario(nombre, nombre, new Date(), randomMail, randomUsername, randomPass);
	
		// Comprobamos que devuelve el usuario
		assertTrue(user.isPresent());
		
		// Probamos a hacer login, deberia hacerlo sin problemas
		if(user.isPresent()) {
			boolean result_login = Controlador.getInstance().loginUsuario(randomUsername, randomPass);
			assertTrue(result_login);

		}
		
		// Comprobamos que inicialmente no tiene listas.
		assertTrue(Controlador.getInstance().getListasUsuario().size()==0);
		
		// Registramos una lista
	    String randomList = UUID.randomUUID().toString().replace("-", "");
	    ListaVideos lv1 = Controlador.getInstance().registrarLista(randomList);
		
		assertTrue(Controlador.getInstance().getListasUsuario().size()==1);
		
		assertTrue(Controlador.getInstance().getListasUsuario().get(0).getNombre().equals(randomList));
		assertTrue(Controlador.getInstance().getListasUsuario().get(0).getVideos().isEmpty());


		// Registramos una segunda lista
	    String randomList2 = UUID.randomUUID().toString().replace("-", "");
		ListaVideos lv2 = Controlador.getInstance().registrarLista(randomList2);
		assertTrue(Controlador.getInstance().getListasUsuario().size()==2);

		assertTrue(Controlador.getInstance().getListasUsuario().get(1).getNombre().equals(randomList2));
		assertTrue(Controlador.getInstance().getListasUsuario().get(1).getVideos().isEmpty());

		// Insertamos video a Lista
		
		Video v = Controlador.getInstance().getVideos().get(0);
		Controlador.getInstance().insertarVideoLista(lv1, v);
		
		assertTrue(Controlador.getInstance().getListasUsuario().get(0).getVideos().size()==1);
		assertTrue(Controlador.getInstance().getListasUsuario().get(0).getVideos().get(0).getVideoID().equals(v.getVideoID()));

		
		// Insertamos otro video a Lista
		
		Video v2= Controlador.getInstance().getVideos().get(1);
		Controlador.getInstance().insertarVideoLista(lv1, v2);
		
		assertTrue(Controlador.getInstance().getListasUsuario().get(0).getVideos().size()==2);
		assertTrue(Controlador.getInstance().getListasUsuario().get(0).getVideos().get(1).getVideoID().equals(v2.getVideoID()));

		// Eliminar listas
		Controlador.getInstance().deleteLista(lv2);
		assertTrue(Controlador.getInstance().getListasUsuario().size()==1);
		

		Controlador.getInstance().deleteLista(lv1);
		assertTrue(Controlador.getInstance().getListasUsuario().isEmpty());

	}

}
