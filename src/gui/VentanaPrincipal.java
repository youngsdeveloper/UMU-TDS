package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;

public class VentanaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
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

	
	JPanel cards;
	CardLayout cl;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100,775, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JLabel lblAppvideologo = new JLabel("AppVideoLogo");
			panel.add(lblAppvideologo);
			
			panel.add(Box.createRigidArea(new Dimension(30,40)));
			
			JLabel lblNewLabel = new JLabel("Hola Jose Ramón");
			panel.add(lblNewLabel);
			
			Component rigidArea = Box.createRigidArea(new Dimension(30, 40));
			panel.add(rigidArea);
			
			JPanel panel_2 = new JPanel();
			panel.add(panel_2);
			
			JButton btnRegistro = new JButton("Registro");
			btnRegistro.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(cards, VentanaRegistro.TAG);
				}});
			
			panel_2.add(btnRegistro);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cl.show(cards, VentanaLogin.TAG);
				}
				
			});
			panel_2.add(btnLogin);
			
			Component rigidArea_1 = Box.createRigidArea(new Dimension(30, 40));
			panel.add(rigidArea_1);
			
			JButton btnCerrarSesin = new JButton("Logout");
			panel.add(btnCerrarSesin);
			
			Component rigidArea_1_1 = Box.createRigidArea(new Dimension(30, 40));
			panel.add(rigidArea_1_1);
			
			JButton btnNewButton_2 = new JButton("Premium");
			panel.add(btnNewButton_2);
			
			JPanel panel_1 = new JPanel();
			frame.getContentPane().add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
			
			
			JPanel panel_3 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_1.add(panel_3);
			
			JButton btnNewButton = new JButton("Explorar");
			panel_3.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Mis listas");
			panel_3.add(btnNewButton_1);
			
			JButton btnNewButton_3 = new JButton("Recientes");
			panel_3.add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("Nueva lista");
			panel_3.add(btnNewButton_4);
			
			//Create the "cards".
			JPanel card_login = new VentanaLogin();
			JPanel card_registro = new VentanaRegistro();
			
			//Create the panel that contains the "cards".
			cl = new CardLayout();
			cards = new JPanel(cl);
			panel_1.add(cards);
			cards.add(card_login, VentanaLogin.TAG);
			cards.add(card_registro, VentanaRegistro.TAG);
				
				

	}

}
