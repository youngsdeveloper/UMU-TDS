package umu.tds.AppVideo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.controlador.ControladorUI;
import umu.tds.AppVideo.events.UsuarioLoggedListener;
import umu.tds.AppVideo.models.Usuario;
import pulsador.Luz;

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
	
	
	private JButton btnRegistro, btnLogin;
	
	private JButton btnLogout, btnPremium;
	
	private JButton btnExplorar, btnMisListas, btnRecientes, btnNuevaLista;


	private JLabel txtUserName;
	private Luz pulsador;
	
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
		
		pulsador = new Luz();
		panel.add(pulsador);
		
		panel.add(Box.createRigidArea(new Dimension(30,40)));
		
		txtUserName = new JLabel("Desconectado");
		panel.add(txtUserName);
		
		Component rigidArea = Box.createRigidArea(new Dimension(30, 40));
		panel.add(rigidArea);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener((ev) -> {
			ControladorUI.getInstance().goToRegistro();
		});
		
		panel_2.add(btnRegistro);
		
		
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener((ev) -> {
			ControladorUI.getInstance().goToLogin();
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
		btnPremium.addActionListener((ev)->{
			ControladorUI.getInstance().goToPremium();
		});
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
			ControladorUI.getInstance().goToExplorar();
		});
		panel_3.add(btnExplorar);
		
		btnMisListas = new JButton("Mis listas");
		btnMisListas.addActionListener((ev) -> {
			ControladorUI.getInstance().goToMisListas();
		});
		
		panel_3.add(btnMisListas);
		
		btnRecientes = new JButton("Recientes");
		btnRecientes.addActionListener((ev) -> {
			ControladorUI.getInstance().goToRecientes();
		});
		panel_3.add(btnRecientes);
		
		btnNuevaLista = new JButton("Nueva lista");
		btnNuevaLista.addActionListener((ev) -> {
			ControladorUI.getInstance().goToNuevaLista();
		});
		panel_3.add(btnNuevaLista);
		

		pulsador.addEncendidoListener(el -> {

			cargarVideos();
		});
		
		pulsador.setColor(Color.YELLOW);
		
		Controlador.getInstance().addUsuarioLoggedListener(new UsuarioLoggedListener() {
			@Override
			public void onUsuarioLogged(Usuario u) {
				EventQueue.invokeLater(new Runnable() {
		            @Override
		            public void run() {
						updateUIlogin();
						
						ControladorUI.getInstance().setupLogged();
						ControladorUI.getInstance().goToRecientes();
		            }
		        });
			}

			@Override
			public void onUsuarioLogout(Usuario u) {

				EventQueue.invokeLater(new Runnable() {
		            @Override
		            public void run() {
						updateUIlogin();
						ControladorUI.getInstance().removeLogged();
						ControladorUI.getInstance().goToLogin();
		            }
		        });

			}
		});
		
		Controlador.getInstance().addUsuarioUpdatedListener((u)->{
			updateUIlogin(); // To refresh premium 
		});
		//Create the "cards".

		JPanel panel_cards = ControladorUI.getInstance().getPanel();
		panel_1.add(panel_cards);
		
		
		updateUIlogin();
			
			

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
			
			if(usuarioActual.get().isPremium()) {
				// Si es premium
				btnPremium.setForeground(Color.GREEN);
			}else {
				btnPremium.setForeground(Color.RED);
			}
			
			pulsador.setVisible(true);
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
			
			btnPremium.setForeground(Color.LIGHT_GRAY);
			pulsador.setVisible(false);


		}
	}
	
	private void cargarVideos() {
		
		
		abrirFileChooser();
	}
	
	private void abrirFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    

	    int result = fileChooser.showOpenDialog(fileChooser);

	    if (result != JFileChooser.CANCEL_OPTION) {

	        File file = fileChooser.getSelectedFile();
	        Controlador.getInstance().cargarVideos(file);

	        
	    }
	}
	

}
