package umu.tds.AppVideo;

import java.awt.EventQueue;

import umu.tds.AppVideo.gui.VentanaPrincipal;

public class Lanzador {
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}