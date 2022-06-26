package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import umu.tds.AppVideo.controlador.Controlador;

public class VentanaLogin extends JPanel {

	
	public final static String TAG = "VentanaLogin";
	private JLabel lblEmail_1;
	private JTextField txtFieldUsername;
	private JLabel lblEmail_2;
	private JButton btnIniciarSesion;
	private JPasswordField txtFieldPassword;
	
	private JPanel ctx;

	/**
	 * Create the panel.
	 */
	public VentanaLogin() {
		
		ctx = this;
		
		setForeground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 192, 65, 100, 0};
		gridBagLayout.rowHeights = new int[]{63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblEmail_1 = new JLabel("Usuario:");
		GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
		gbc_lblEmail_1.anchor = GridBagConstraints.WEST;
		gbc_lblEmail_1.insets = new Insets(20, 0, 5, 5);
		gbc_lblEmail_1.gridx = 1;
		gbc_lblEmail_1.gridy = 4;
		add(lblEmail_1, gbc_lblEmail_1);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setColumns(10);
		GridBagConstraints gbc_txtFieldUsername = new GridBagConstraints();
		gbc_txtFieldUsername.insets = new Insets(20, 0, 5, 5);
		gbc_txtFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldUsername.gridx = 2;
		gbc_txtFieldUsername.gridy = 4;
		add(txtFieldUsername, gbc_txtFieldUsername);
		
		lblEmail_2 = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblEmail_2 = new GridBagConstraints();
		gbc_lblEmail_2.anchor = GridBagConstraints.WEST;
		gbc_lblEmail_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_2.gridx = 1;
		gbc_lblEmail_2.gridy = 5;
		add(lblEmail_2, gbc_lblEmail_2);
		
		txtFieldPassword = new JPasswordField();
		GridBagConstraints gbc_txtFieldPassword = new GridBagConstraints();
		gbc_txtFieldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPassword.gridx = 2;
		gbc_txtFieldPassword.gridy = 5;
		add(txtFieldPassword, gbc_txtFieldPassword);
		
		btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = txtFieldUsername.getText().toString();
				String password = String.valueOf(txtFieldPassword.getPassword());
				
				if(Controlador.getInstance().loginUsuario(username, password)) {
					// DONE: Login correcto
				}else {
					// DONE: Login incorrecto
					JOptionPane.showMessageDialog(ctx, "El usuario/contraseña introducido es incorrecto", "Ups...", JOptionPane.ERROR_MESSAGE);

				}

				
				
			}
		});
		GridBagConstraints gbc_btnIniciarSesion = new GridBagConstraints();
		gbc_btnIniciarSesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciarSesion.gridwidth = 2;
		gbc_btnIniciarSesion.insets = new Insets(20, 0, 5, 5);
		gbc_btnIniciarSesion.gridx = 1;
		gbc_btnIniciarSesion.gridy = 6;
		add(btnIniciarSesion, gbc_btnIniciarSesion);
		
		
		txtFieldUsername.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				checkButtonLoginEnabled();

			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				checkButtonLoginEnabled();

			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				checkButtonLoginEnabled();

			}
		});
		
		txtFieldPassword.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				checkButtonLoginEnabled();

			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				checkButtonLoginEnabled();

			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				checkButtonLoginEnabled();

			}
		});
		

		checkButtonLoginEnabled();
		// autoLoginTest(); Autologin, solo testing.

	}
	
	/**
	 * 
	 * Esta funcion comprueba si el formulario es valido para poder hacer login y si lo es activa el boton
	 * de login.
	 * 
	 * @author Enrique
	 * 
	 */
	 
	private void checkButtonLoginEnabled() {
		String username = txtFieldUsername.getText().toString();
		String password = txtFieldPassword.getText().toString();
		
		if(username.isBlank() || password.isBlank()) {
			// Formulario vacio, boton deshabilitaod.
			btnIniciarSesion.setEnabled(false);

		}else {
			// El formulario esta lleno, habilitar boton.
			btnIniciarSesion.setEnabled(true);

		}
	 }
	
	
	/**
	 *
	 * Metodo para agilizar el login. Solo usar con motivo de TESTING.
	 * 
	 */
	private void autoLoginTest() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				if(Controlador.getInstance().loginUsuario("test", "testtest")) {
        			// DONE: Login correcto
	            	System.out.println("(1) Iniciando sesion");

        		}else {
        			// DONE: Login incorrecto
        			JOptionPane.showMessageDialog(ctx, "El usuario/contraseña introducido es incorrecto", "Ups...", JOptionPane.ERROR_MESSAGE);

        		}
				
			}});


		
	}

}
