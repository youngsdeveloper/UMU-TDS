package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import umu.tds.AppVideo.controlador.Controlador;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VentanaLogin extends JPanel {

	
	public final static String TAG = "VentanaLogin";
	private JLabel lblEmail_1;
	private JTextField txtFieldUsername;
	private JLabel lblEmail_2;
	private JButton btnIniciarSesion;
	private JPasswordField txtFieldPassword;

	/**
	 * Create the panel.
	 */
	public VentanaLogin() {
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
				String password = txtFieldPassword.getPassword().toString();

				Controlador.getInstance().loginUsuario(username, password);
				
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

}
