package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.dao.EtiquetaDAO;
import umu.tds.AppVideo.dao.FactoriaDAO;
import umu.tds.AppVideo.dao.UsuarioDAO;
import umu.tds.AppVideo.models.CatalogoEtiquetas;
import umu.tds.AppVideo.models.Etiqueta;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.AbstractListModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;

public class VentanaExplorar extends JPanel {

	
	public final static String TAG = "VentanaExplorar";
	private JTextField textField;
	
	private Set<String> etiquetasSeleccionadas;

	
	private JList listEtiquetasSeleccionadas;
	private JTable tableVideos;
	/**
	 * Create the panel.
	 */
	public VentanaExplorar() {
		
		etiquetasSeleccionadas = new HashSet<String>();
		
		setForeground(Color.WHITE);
		setLayout(new MigLayout("", "[4.00px][70px,grow][113.00][][][26.00][grow][]", "[15px][15px][][grow][][][grow][][][grow][][][]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 1 4 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Buscar titulo:");
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		panel.add(btnBuscar);
		
		JLabel lblNewLabel = new JLabel("Etiquetas disponibles");
		add(lblNewLabel, "cell 6 1");
		
		
		CatalogoEtiquetas catalogoEtiquetas = CatalogoEtiquetas.getInstance();
		

		String[] valuesEtiquetas = catalogoEtiquetas
			.getEtiquetas()
			.stream()
			.map(etiqueta -> etiqueta.getNombre())
			.toArray(String[]::new);
		
		
		final JList listEtiquetasDisponibles = new JList();
		listEtiquetasDisponibles.setModel(new AbstractListModel() {
			String[] values = valuesEtiquetas;
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
		
		JButton btnNuevaBusqueda = new JButton("Nueva busqueda");
		add(btnNuevaBusqueda, "cell 1 2 4 1,alignx center,aligny center");
		add(listEtiquetasDisponibles, "cell 6 2 1 3,grow");
		
		tableVideos = new JTable(new TableModelVideo());
		tableVideos.setRowHeight(150);
		tableVideos.setRowSelectionAllowed(false);

		tableVideos.setDefaultRenderer(Object.class, new VideoTableCellRenderer());
		add(tableVideos, "cell 1 3 4 7,grow");
		
		JLabel lblBuscarEtiquetas = new JLabel("Buscar etiquetas");
		add(lblBuscarEtiquetas, "cell 6 5");
		
		add(listEtiquetasSeleccionadas, "cell 6 6 1 4,grow");

	}
	
	

	/**
	 * 
	 * 
	 * Este metodo crea el renderizador de lista para los videos.
	 * 
	 * @author Enrique Rodriguez
	 * 
	 * */
	private DefaultListCellRenderer createListRenderer() {
		return new DefaultListCellRenderer() {
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color)
			UIManager.get("List.background");
			
			@Override
			public Component getListCellRendererComponent(JList<?> list,Object value, int index, boolean isSelected,boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(
				list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/images/logo-100.png")));
				}
				return c;
			}
		};
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
