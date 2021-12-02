package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import umu.tds.AppVideo.controlador.Controlador;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.AbstractListModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;

public class VentanaExplorar extends JPanel {

	
	public final static String TAG = "VentanaExplorar";
	private JTextField textField;
	
	private Set<String> etiquetasSeleccionadas;

	
	private JList listEtiquetasSeleccionadas;
	/**
	 * Create the panel.
	 */
	public VentanaExplorar() {
		
		etiquetasSeleccionadas = new HashSet<String>();
		
		setForeground(Color.WHITE);
		setLayout(new MigLayout("", "[33.00px][70px][113.00][][][26.00][grow][]", "[15px][15px][][grow][][][grow][][][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Buscar titulo:");
		add(lblNewLabel_1, "cell 1 1,alignx trailing,aligny center");
		
		textField = new JTextField();
		add(textField, "cell 2 1,growx,aligny center");
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		add(btnBuscar, "cell 3 1,aligny center");
		
		JLabel lblNewLabel = new JLabel("Etiquetas disponibles");
		add(lblNewLabel, "cell 6 1");
		
		JButton btnNuevaBusqueda = new JButton("Nueva busqueda");
		add(btnNuevaBusqueda, "cell 2 2");
		
		final JList listEtiquetasDisponibles = new JList();
		listEtiquetasDisponibles.setModel(new AbstractListModel() {
			String[] values = new String[] {"Dibujos animados", "Peliculas"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		listEtiquetasSeleccionadas = new JList();

		
		listEtiquetasDisponibles.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				etiquetasSeleccionadas.add(String.valueOf(listEtiquetasDisponibles.getSelectedValue()));
				updateListSeleccionadas();

			}
		});
		
		listEtiquetasSeleccionadas.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				etiquetasSeleccionadas.remove(String.valueOf(listEtiquetasSeleccionadas.getSelectedValue()));
				updateListSeleccionadas();
			}
		});
		add(listEtiquetasDisponibles, "cell 6 2 1 2,grow");
		
		JLabel lblBuscarEtiquetas = new JLabel("Buscar etiquetas");
		add(lblBuscarEtiquetas, "cell 6 4");
		
		add(listEtiquetasSeleccionadas, "cell 6 5 1 2,grow");

	}
	
	private void updateListSeleccionadas() {
		listEtiquetasSeleccionadas.setModel(new AbstractListModel() {
			String[] values = etiquetasSeleccionadas.toArray(String[]::new);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

}