package umu.tds.AppVideo;

import static org.junit.Assert.*;

import org.junit.Test;

import umu.tds.AppVideo.controlador.Controlador;

public class TestVideoNotEmpty {

	@Test
	public void test() {
		assertFalse(Controlador.getInstance().getVideos().isEmpty());
	}

}
