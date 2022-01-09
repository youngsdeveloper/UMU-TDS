package umu.tds.AppVideo.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.events.UsuarioLoggedListener;
import umu.tds.AppVideo.models.Usuario;

import java.awt.Component;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

public class VentanaPrincipal {

	private JFrame frmAppvideo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmAppvideo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	

	public void mostrarVentana() {
		frmAppvideo.setLocationRelativeTo(null);
		frmAppvideo.setVisible(true);
	}
	
	JPanel cards;
	CardLayout cl;
	
	private JButton btnRegistro, btnLogin;
	
	private JButton btnLogout, btnPremium;
	
	private JButton btnExplorar, btnMisListas, btnRecientes, btnNuevaLista;


	private JLabel txtUserName;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppvideo = new JFrame();
		frmAppvideo.setTitle("AppVideo");
		frmAppvideo.setBounds(100, 100,775, 800);
		frmAppvideo.setMinimumSize(new Dimension(700,500));
		frmAppvideo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmAppvideo.getContentPane().setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			frmAppvideo.getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JLabel lblAppvideologo = new JLabel("");
			lblAppvideologo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/images/logo-100.png")));
			panel.add(lblAppvideologo);
			
			panel.add(Box.createRigidArea(new Dimension(30,40)));
			
			txtUserName = new JLabel("Desconectado");
			panel.add(txtUserName);
			
			Component rigidArea = Box.createRigidArea(new Dimension(30, 40));
			panel.add(rigidArea);
			
			JPanel panel_2 = new JPanel();
			panel.add(panel_2);
			
			btnRegistro = new JButton("Registro");
			btnRegistro.addActionListener((ev) -> {
				cl.show(cards, VentanaRegistro.TAG);
			});
			
			panel_2.add(btnRegistro);
			
			btnLogin = new JButton("Login");
			btnLogin.addActionListener((ev) -> {
				cl.show(cards, VentanaLogin.TAG);
			});
			panel_2.add(btnLogin);
			
			Component rigidArea_1 = Box.createRigidArea(new Dimension(30, 40));
			panel.add(rigidArea_1);
			
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener((ev) -> {
				Controlador.getInstance().logout();
			});
			
			panel.add(btnLogout);
			
			Component rigidArea_1_1 = Box.createRigidArea(new Dimension(30, 40));
			panel.add(rigidArea_1_1);
			
			btnPremium = new JButton("Premium");
			panel.add(btnPremium);
			
			JPanel panel_1 = new JPanel();
			frmAppvideo.getContentPane().add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
			
			
			JPanel panel_3 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_1.add(panel_3);
			
			btnExplorar = new JButton("Explorar");
			btnExplorar.addActionListener((ev) -> {
				cl.show(cards, VentanaExplorar.TAG);
			});
			panel_3.add(btnExplorar);
			
			btnMisListas = new JButton("Mis listas");
			btnMisListas.addActionListener((ev) -> {
				cl.show(cards, VentanaLista.TAG);
			});
			
			panel_3.add(btnMisListas);
			
			btnRecientes = new JButton("Recientes");
			panel_3.add(btnRecientes);
			
			btnNuevaLista = new JButton("Nueva lista");
			btnNuevaLista.addActionListener((ev) -> {
				cl.show(cards, VentanaNuevaLista.TAG);
			});
			panel_3.add(btnNuevaLista);
			
			//Create the "cards".
			JPanel card_login = new VentanaLogin();
			JPanel card_registro = new VentanaRegistro();
			JPanel card_explorar = new VentanaExplorar();
			JPanel card_nueva_lista = new VentanaNuevaLista();
			JPanel card_lista = new VentanaLista();

			//Create the panel that contains the "cards".
			cl = new CardLayout();
			cards = new JPanel(cl);
			panel_1.add(cards);
			cards.add(card_login, VentanaLogin.TAG);
			cards.add(card_registro, VentanaRegistro.TAG);
			cards.add(card_explorar, VentanaExplorar.TAG);
			cards.add(card_nueva_lista, VentanaNuevaLista.TAG);
			
			cards.add(card_lista, VentanaLista.TAG);

			updateUIlogin();
			
			Controlador.getInstance().addUsuarioLoggedListener(new UsuarioLoggedListener() {
				@Override
				public void onUsuarioLogged(Usuario u) {
					updateUIlogin();
					cl.show(cards, VentanaExplorar.TAG);
				}

				@Override
				public void onUsuarioLogout(Usuario u) {
					updateUIlogin();	
					cl.show(cards, VentanaLogin.TAG);

				}
			});

	}
	
	
	
	/**
	 *
	 * Este metodo actualiza la propiedad enabled de los botones segun el estado del usuario: (autenticado o no)
	 * 
	 * @author Enrique Rodriguez
	 */
	private void updateUIlogin(){
		
		
		
		Optional<Usuario> usuarioActual = Controlador.getInstance().getUsuarioActual();
		
		
		if(usuarioActual.isPresent()) {
			txtUserName.setText("Hola, " + usuarioActual.get().getNombre());

			btnRegistro.setEnabled(false);
			btnLogin.setEnabled(false);
			btnLogout.setEnabled(true);
			btnPremium.setEnabled(true);
			
			btnExplorar.setEnabled(true);
			btnMisListas.setEnabled(true);
			btnRecientes.setEnabled(true);
			btnNuevaLista.setEnabled(true);
		}else {
			
			txtUserName.setText("Desconectado");
			
			btnRegistro.setEnabled(true);
			btnLogin.setEnabled(true);
			btnLogout.setEnabled(false);
			btnPremium.setEnabled(false);
			
			btnExplorar.setEnabled(false);
			btnMisListas.setEnabled(false);
			btnRecientes.setEnabled(false);
			btnNuevaLista.setEnabled(false);

		}
	}
	

}
