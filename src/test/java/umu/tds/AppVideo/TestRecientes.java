package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TestRecientes {

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
		
		// Comprobamos que no tiene ningun video reciente
		assertTrue(Controlador.getInstance().getVideosRecientes().size()==0);
		
		Video v = Controlador.getInstance().getVideos().get(0);

		Controlador.getInstance().addVideoRecientes(v);
		
		// Comprobamos que se ha añadido correctamente
		assertTrue(Controlador.getInstance().getVideosRecientes().size()==1);
		assertTrue(Controlador.getInstance().getVideosRecientes().get(0).getVideoID().equals(v.getVideoID()));

		// Volvemos a añadir el mismo video, no deberia de añadirse de nuevo
		
		Controlador.getInstance().addVideoRecientes(v);
		
		assertTrue(Controlador.getInstance().getVideosRecientes().size()==1);

		// Vamos a añadir ahora un segundo video
		
		Video v2 = Controlador.getInstance().getVideos().get(1);
		Controlador.getInstance().addVideoRecientes(v2);
		
		assertTrue(Controlador.getInstance().getVideosRecientes().size()==2);

		// Comprobamos que se ha insertado en la primera posicion
		assertTrue(Controlador.getInstance().getVideosRecientes().get(0).getVideoID().equals(v2.getVideoID()));

		
	}

}
