package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

public class VentanaLogin extends JPanel {

	
	public final static String TAG = "VentanaLogin";
	private JTextField txtContrasea;
	private JTextField textField;
	
	/**
	 * Create the panel.
	 */
	public VentanaLogin() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario: ");
		panel.add(lblNombreDeUsuario);
		
		txtContrasea = new JTextField();
		txtContrasea.setText("Contraseña");

		panel.add(txtContrasea);
		txtContrasea.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña: ");
		panel.add(lblContrasea);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);

	}

}
