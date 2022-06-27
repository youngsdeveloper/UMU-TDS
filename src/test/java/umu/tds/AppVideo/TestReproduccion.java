package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TestReproduccion {

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
		
		Video v = Controlador.getInstance().getVideos().get(0);

		int numBefore = v.getNumReproducciones();
		Controlador.getInstance().sumarReproduccion(v);
		int numNow = v.getNumReproducciones();
		assertTrue((numNow-numBefore)==1);
		
		// Sumamos otra segunda reproduccion
		Controlador.getInstance().sumarReproduccion(v);
		numNow = v.getNumReproducciones();
		assertTrue((numNow-numBefore)==2);
	}

}
