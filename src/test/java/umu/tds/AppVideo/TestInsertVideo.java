package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class TestInsertVideo {

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

		String videoID = "sQ409aKgGXE";
	    String randomName = UUID.randomUUID().toString().replace("-", "");

		
	    Video v = new Video(randomName, videoID);
		Controlador.getInstance().addVideo(v);
		
		List<Video> lv = Controlador.getInstance().searchVideos(randomName, Optional.empty());
		
		
		assertTrue(lv.size()==1);
		if(lv.size()>0) {
			assertTrue(lv.get(0).getTitulo().equals(v.getTitulo()));
		}

	}

}
