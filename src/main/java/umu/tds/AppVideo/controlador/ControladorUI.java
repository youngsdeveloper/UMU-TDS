package umu.tds.AppVideo.controlador;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JPanel;

import umu.tds.AppVideo.events.UsuarioLoggedListener;
import umu.tds.AppVideo.gui.PanelReproductor;
import umu.tds.AppVideo.gui.SingletonReproductor;
import umu.tds.AppVideo.gui.VentanaExplorar;
import umu.tds.AppVideo.gui.VentanaLista;
import umu.tds.AppVideo.gui.VentanaLogin;
import umu.tds.AppVideo.gui.VentanaNuevaLista;
import umu.tds.AppVideo.gui.VentanaRecientes;
import umu.tds.AppVideo.gui.VentanaRegistro;
import umu.tds.AppVideo.models.Video;

public class ControladorUI {

	private static ControladorUI instance = null;
	
	
	private Map<String,JPanel> panels;
	private List<JPanel> panelsLogged;

	private JPanel currentPanel;
	JPanel cards;
	CardLayout cl;

	private ControladorUI() {
		cl = new CardLayout();
		//Create the panel that contains the "cards".
		cards = new JPanel(cl);
		panels = new HashMap();
		panelsLogged = new ArrayList<JPanel>(10);

		setupLoginRegistro();
	}

	public static ControladorUI getInstance() {
		if(instance==null)
			instance = new ControladorUI();
		
		return instance;
	}
	
	public void setCards(JPanel cards) {
		this.cards = cards;
	}
	public JPanel getPanel() {
		return cards;
	}
	public void setCl(CardLayout cl) {
		this.cl = cl;
	}
	
	private void setupLoginRegistro() {
		JPanel card_login = new VentanaLogin();
		JPanel card_registro = new VentanaRegistro();
		
		panels.put(VentanaLogin.TAG, card_login);
		panels.put(VentanaRegistro.TAG, card_registro);
		
		cards.add(card_login, VentanaLogin.TAG);
		cards.add(card_registro, VentanaRegistro.TAG);
	}
	
	public void setupLogged() {
		JPanel card_explorar = new VentanaExplorar();
		JPanel card_nueva_lista = new VentanaNuevaLista();
		JPanel card_lista = new VentanaLista();
		JPanel card_recientes = new VentanaRecientes();

		panels.put(VentanaExplorar.TAG, card_explorar);
		panels.put(VentanaNuevaLista.TAG, card_nueva_lista);
		panels.put(VentanaLista.TAG, card_lista);
		panels.put(VentanaRecientes.TAG, card_recientes);

		panelsLogged.add(card_explorar);
		panelsLogged.add(card_nueva_lista);
		panelsLogged.add(card_lista);
		panelsLogged.add(card_recientes);

		cards.add(card_explorar, VentanaExplorar.TAG);
		cards.add(card_nueva_lista, VentanaNuevaLista.TAG);
		cards.add(card_lista, VentanaLista.TAG);
		cards.add(card_recientes, VentanaRecientes.TAG);

	}
	
	public void removeLogged() {
		
		for(JPanel panel:panelsLogged) {
			cards.remove(panel);
		}
		cards.revalidate();
		
		panels.remove(VentanaExplorar.TAG);
		panels.remove(VentanaNuevaLista.TAG);
		panels.remove(VentanaLista.TAG);
		panels.remove(VentanaRecientes.TAG);

		panelsLogged.clear();		
	}
	
	public void goToExplorar() {
		JPanel newPanel = panels.get(VentanaExplorar.TAG);
		onTabChanged(currentPanel, newPanel);
		cl.show(cards, VentanaExplorar.TAG);
		currentPanel = newPanel;
	}
	
	public void goToNuevaLista() {
		JPanel newPanel = panels.get(VentanaNuevaLista.TAG);
		onTabChanged(currentPanel, newPanel);
		cl.show(cards, VentanaNuevaLista.TAG);
		currentPanel = panels.get(VentanaNuevaLista.TAG);
	}
	
	public void goToMisListas() {
		JPanel newPanel = panels.get(VentanaLista.TAG);
		onTabChanged(currentPanel, newPanel);
		cl.show(cards, VentanaLista.TAG);
		currentPanel = panels.get(VentanaLista.TAG);
	}

	public void goToRecientes() {
		JPanel newPanel = panels.get(VentanaRecientes.TAG);
		onTabChanged(currentPanel, newPanel);
		cl.show(cards, VentanaRecientes.TAG);
		currentPanel = panels.get(VentanaRecientes.TAG);
	}
	public void goToLogin() {
		JPanel newPanel = panels.get(VentanaLogin.TAG);
		onTabChanged(currentPanel, newPanel);
		cl.show(cards, VentanaLogin.TAG);
		currentPanel = panels.get(VentanaLogin.TAG);
	}
	
	public void goToRegistro() {
		JPanel newPanel = panels.get(VentanaRegistro.TAG);
		onTabChanged(currentPanel, newPanel);
		cl.show(cards, VentanaRegistro.TAG);
		currentPanel = panels.get(VentanaRegistro.TAG);
	}
	
	private void onTabChanged(JPanel prev,JPanel next) {
		if(prev==null) {
			return;
		}
		if(!prev.equals(next)) {
			SingletonReproductor.getInstance().cancel();
		}
	}
	
	public void goToReproductor(Video video) {
		if(video==null) {
			return;
		}
		PanelReproductor card_reproductor = new PanelReproductor();
		panels.put(PanelReproductor.TAG, card_reproductor);
		panelsLogged.add(card_reproductor);
		cards.add(card_reproductor, PanelReproductor.TAG);

		cl.show(cards, PanelReproductor.TAG);
		card_reproductor.loadVideo(video);
		currentPanel = panels.get(PanelReproductor .TAG);
	}
	
}
