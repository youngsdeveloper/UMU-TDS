package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TestEtiquetas {

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

	    String randomEQ = UUID.randomUUID().toString().replace("-", "");

	    int numEtiquetasBef = v.getEtiquetas().size();
	    
		Etiqueta eq = new Etiqueta(randomEQ);
		Controlador.getInstance().insertarEtiquetaVideo(eq,v);
		
	    int numEtiquetasNow = v.getEtiquetas().size();
	    assertTrue((numEtiquetasNow-numEtiquetasBef)==1); //Comprobamos que se ha añadido 1
	    
	    assertTrue(v.getEtiquetas().contains(eq));

		
	}

}
