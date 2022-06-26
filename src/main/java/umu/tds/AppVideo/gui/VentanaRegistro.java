package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import umu.tds.AppVideo.controlador.Controlador;

public class VentanaRegistro extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String TAG = "VentanaRegistro";
	private JTextField txtFieldNombre;
	private JTextField txtFieldApellidos;
	private JDateChooser txtFieldFechaNacimiento;
	private JLabel lblEmail;
	private JTextField txtFieldEmail;
	private JLabel lblEmail_1;
	private JTextField txtFieldUsername;
	private JLabel lblEmail_2;
	private JLabel lblEmail_3;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JLabel lblCamposObligatorios;
	private JPasswordField txtFieldPassword;
	private JPasswordField txtFieldPasswordConfirmation;

	
	private JPanel ctx;

	/**
	 * Create the panel.
	 */
	public VentanaRegistro() {
		setForeground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 192, 65, 100, 0};
		gridBagLayout.rowHeights = new int[]{63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblLogin = new JLabel("Nombre: ");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 1;
		add(lblLogin, gbc_lblLogin);
		
		txtFieldNombre = new JTextField();
		GridBagConstraints gbc_txtFieldNombre = new GridBagConstraints();
		gbc_txtFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldNombre.gridx = 2;
		gbc_txtFieldNombre.gridy = 1;
		add(txtFieldNombre, gbc_txtFieldNombre);
		txtFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 2;
		add(lblApellidos, gbc_lblApellidos);
		
		txtFieldApellidos = new JTextField();
		txtFieldApellidos.setColumns(10);
		GridBagConstraints gbc_txtFieldApellidos = new GridBagConstraints();
		gbc_txtFieldApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldApellidos.gridx = 2;
		gbc_txtFieldApellidos.gridy = 2;
		add(txtFieldApellidos, gbc_txtFieldApellidos);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento: ");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 1;
		gbc_lblFechaDeNacimiento.gridy = 3;
		add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		txtFieldFechaNacimiento = new JDateChooser();
		GridBagConstraints gbc_txtFieldFechaNacimiento = new GridBagConstraints();
		gbc_txtFieldFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldFechaNacimiento.gridx = 2;
		gbc_txtFieldFechaNacimiento.gridy = 3;
		add(txtFieldFechaNacimiento, gbc_txtFieldFechaNacimiento);
		
		lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 4;
		add(lblEmail, gbc_lblEmail);
		
		txtFieldEmail = new JTextField();
		txtFieldEmail.setColumns(10);
		GridBagConstraints gbc_txtFieldEmail = new GridBagConstraints();
		gbc_txtFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldEmail.gridx = 2;
		gbc_txtFieldEmail.gridy = 4;
		add(txtFieldEmail, gbc_txtFieldEmail);
		
		lblEmail_1 = new JLabel("(*) Usuario:");
		GridBagConstraints gbc_lblEmail_1 = new GridBagConstraints();
		gbc_lblEmail_1.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_1.insets = new Insets(20, 0, 5, 5);
		gbc_lblEmail_1.gridx = 1;
		gbc_lblEmail_1.gridy = 6;
		add(lblEmail_1, gbc_lblEmail_1);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setColumns(10);
		GridBagConstraints gbc_txtFieldUsername = new GridBagConstraints();
		gbc_txtFieldUsername.insets = new Insets(20, 0, 5, 5);
		gbc_txtFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldUsername.gridx = 2;
		gbc_txtFieldUsername.gridy = 6;
		add(txtFieldUsername, gbc_txtFieldUsername);
		
		lblEmail_2 = new JLabel("(*) Contraseña:");
		GridBagConstraints gbc_lblEmail_2 = new GridBagConstraints();
		gbc_lblEmail_2.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_2.gridx = 1;
		gbc_lblEmail_2.gridy = 7;
		add(lblEmail_2, gbc_lblEmail_2);
		
		txtFieldPassword = new JPasswordField();
		GridBagConstraints gbc_txtFieldPassword = new GridBagConstraints();
		gbc_txtFieldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPassword.gridx = 2;
		gbc_txtFieldPassword.gridy = 7;
		add(txtFieldPassword, gbc_txtFieldPassword);
		
		lblEmail_3 = new JLabel("(*) Repetir contraseña:");
		GridBagConstraints gbc_lblEmail_3 = new GridBagConstraints();
		gbc_lblEmail_3.anchor = GridBagConstraints.EAST;
		gbc_lblEmail_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail_3.gridx = 1;
		gbc_lblEmail_3.gridy = 8;
		add(lblEmail_3, gbc_lblEmail_3);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombre = txtFieldNombre.getText();
				String apellidos = txtFieldApellidos.getText();
				Date fechaNacimiento = txtFieldFechaNacimiento.getDate();
				String email = txtFieldEmail.getText();
				String username = txtFieldUsername.getText();
				String password = String.valueOf(txtFieldPassword.getPassword());
				String passwordConfirmation = String.valueOf(txtFieldPasswordConfirmation.getPassword());
				
				if(password.equals(passwordConfirmation)) {
					Controlador.getInstance().registrarUsuario(nombre, apellidos, fechaNacimiento, email, username, passwordConfirmation);
					JOptionPane.showMessageDialog(ctx, "El usuario ha sido registrado correctamente", "Exito.", JOptionPane.INFORMATION_MESSAGE);

					//DONE: Usuario Registrado
				}else {
					//DONE: Confirmacion de contraseña incorrecta
					JOptionPane.showMessageDialog(ctx, "La confirmación de la contraseá es incorrecta", "Ups...", JOptionPane.ERROR_MESSAGE);

				}
				

			}
		});
		
		txtFieldPasswordConfirmation = new JPasswordField();
		GridBagConstraints gbc_txtFieldPasswordConfirmation = new GridBagConstraints();
		gbc_txtFieldPasswordConfirmation.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldPasswordConfirmation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPasswordConfirmation.gridx = 2;
		gbc_txtFieldPasswordConfirmation.gridy = 8;
		add(txtFieldPasswordConfirmation, gbc_txtFieldPasswordConfirmation);
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(20, 0, 5, 5);
		gbc_btnRegistrar.gridx = 1;
		gbc_btnRegistrar.gridy = 10;
		add(btnRegistrar, gbc_btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(20, 0, 5, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 10;
		add(btnCancelar, gbc_btnCancelar);
		
		lblCamposObligatorios = new JLabel("* Campos obligatorios");
		GridBagConstraints gbc_lblCamposObligatorios = new GridBagConstraints();
		gbc_lblCamposObligatorios.insets = new Insets(20, 0, 5, 5);
		gbc_lblCamposObligatorios.gridx = 1;
		gbc_lblCamposObligatorios.gridy = 11;
		add(lblCamposObligatorios, gbc_lblCamposObligatorios);

	}

}
