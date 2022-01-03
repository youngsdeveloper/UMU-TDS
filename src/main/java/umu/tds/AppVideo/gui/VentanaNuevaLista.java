package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.CatalogoVideos;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Video;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaNuevaLista extends JPanel {
	
	public final static String TAG = "VentanaNuevaLista";

	private JTextField text_search;
	private JTextField text_search_video;
	private JTable tableVideos;

	/**
	 * Create the panel.
	 */
	public VentanaNuevaLista() {
		setLayout(new MigLayout("", "[::250px,grow][grow]", "[::100px,grow][grow][]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		JLabel lblIntroduceNombreLista = new JLabel("Introduce nombre lista:");
		panel_2.add(lblIntroduceNombreLista);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		text_search = new JTextField();
		panel_3.add(text_search);
		text_search.setColumns(10);
		
		JButton btnSearch = new JButton("Buscar");
		btnSearch.addActionListener((e) -> {
			searchList();
		});
		panel_3.add(btnSearch);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JButton btnEliminar = new JButton("Eliminar");
		panel_4.add(btnEliminar);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		panel_1.add(panel_5);
		
		JLabel lblBuscarTitulo = new JLabel("Buscar titulo");
		panel_5.add(lblBuscarTitulo);
		
		text_search_video = new JTextField();
		panel_5.add(text_search_video);
		text_search_video.setColumns(10);
		
		JButton btn_search_video = new JButton("Buscar");
		btn_search_video.addActionListener((e) -> searchVideo());
		panel_5.add(btn_search_video);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JButton btnNuevaBusqueda = new JButton("Nueva busqueda");
		btnNuevaBusqueda.addActionListener((e) -> nuevaBusquedaVideos());
		panel_6.add(btnNuevaBusqueda);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 1,grow");
		
		List<Video> videos = Controlador.getInstance().getVideos();

		tableVideos = new JTable(new TableModelVideo(videos));

		tableVideos.setTableHeader(null);
		tableVideos.setRowHeight(150);
		tableVideos.setRowSelectionAllowed(false);
		tableVideos.setDefaultRenderer(Object.class, new VideoTableCellRenderer());

		scrollPane.setViewportView(tableVideos);
		

	}
	
	private void searchList() {
		String q = text_search.getText();
		Optional<ListaVideos> result = Controlador.getInstance().getLista(q);
		
		if(result.isPresent()) {
			// Hay una lista, mostrarla
		}else {
			// No exista esa lista, preguntar si desea crearla.
			
			
			Object[] options = {"Aceptar",
					"Cancelar"};
			
			int option = JOptionPane.showOptionDialog(this,
					"¿Desea crear la lista: '" + q + "'? ",
					"Lista inexistente",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
			if(option==0) {
				// Crear nueva lista
				ListaVideos listaCreada = Controlador.getInstance().registrarLista(q);
			}
		}
		
	}
	
	private void nuevaBusquedaVideos(){
		text_search_video.setText(""); // Limpiamos el campo de texto de busqueda
		searchVideo();
	}
	
	private void searchVideo() {
		updateVideos();
	}
	
	private void updateVideos(){
		List<Video> searchResult = Controlador.getInstance().searchVideos(text_search_video.getText(), Optional.empty());
		System.out.println("Result: " + searchResult.toString());
		tableVideos.setModel(new TableModelVideo(searchResult));
	}

}
