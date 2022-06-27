package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Usuario;

public class TestLoginSignup {

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
		
		// Probamos a obtener el usuario Actual
		Optional<Usuario> usActual = Controlador.getInstance().getUsuarioActual();
		assertTrue(usActual.isPresent());
		
		// Verificamos que es el mismo
		if(usActual.isPresent()) {
			assertTrue(usActual.get().getNombre().equals(user.get().getNombre()));
			assertTrue(usActual.get().getApellidos().equals(user.get().getApellidos()));
			assertTrue(usActual.get().getEmail().equals(user.get().getEmail()));
			assertTrue(usActual.get().getUsername().equals(user.get().getUsername()));
			assertTrue(usActual.get().getPassword().equals(user.get().getPassword()));
			assertTrue(usActual.get().getId()==user.get().getId());
		}
		
		// Hacemos logout
		boolean result_logout = Controlador.getInstance().logout();
		assertTrue(result_logout);
		
		// Probamos a obtener el usuario Actual (No deberia existir)
		Optional<Usuario> usActual2 = Controlador.getInstance().getUsuarioActual();
		assertFalse(usActual2.isPresent());


		


		
	}

}
