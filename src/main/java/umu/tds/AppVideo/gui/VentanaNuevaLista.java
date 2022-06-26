package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

public class VentanaNuevaLista extends JPanel {
	
	public final static String TAG = "VentanaNuevaLista";

	private JTextField text_search;
	private JTextField text_search_video;
	private JTable tableVideos;
	private JList<Video> listVideos;
	private DefaultListModel<Video> listVideosModel;
	private JScrollPane scrollPane_list_videos;

	private JButton btnAñadir;
	private JButton btnQuitar;
	private JButton btnAceptar;
	private JButton btnEliminar;
	
	private Optional<ListaVideos> listaSeleccionada = Optional.empty();
	/**
	 * Create the panel.
	 */
	public VentanaNuevaLista() {
		setLayout(new MigLayout("", "[::250px,grow][grow]", "[::100px,grow][::300px,grow][grow][]"));
		
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
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener((action) ->{
			deleteList();
		});
		
		
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
		
		
	
		List<Video> videos = Controlador.getInstance().getVideos();
		
		scrollPane_list_videos = new JScrollPane();
		add(scrollPane_list_videos, "cell 0 1,grow");
		
		
		listVideosModel = new DefaultListModel<Video>();
		listVideos = new JList<Video>(listVideosModel);


		Video[] arrayVideos = new Video[0];;
		if(videos.size()>0) {
			//arrayVideos = videos.toArray(new Video[videos.size()]);;
			listVideos.setCellRenderer(new VideoListCellRenderer());
			add(listVideos, "cell 0 1,grow");
		}
		
		scrollPane_list_videos.setViewportView(listVideos);
		
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 1 1 2,grow");

		tableVideos = new JTable(new TableModelVideo(videos));

		tableVideos.setTableHeader(null);
		tableVideos.setRowHeight(150);
		tableVideos.setRowSelectionAllowed(false);
		tableVideos.setCellSelectionEnabled(true);

		tableVideos.setDefaultRenderer(Object.class, new VideoTableCellRenderer());

		scrollPane.setViewportView(tableVideos);
		
		JPanel panel_8 = new JPanel();
		add(panel_8, "cell 0 2,grow");
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		btnAñadir = new JButton("Añadir");
		btnAñadir.setEnabled(false);
		btnAñadir.addActionListener((ev) -> {
			addSelectedVideo();
		});
		panel_9.add(btnAñadir);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.setEnabled(false);
		btnQuitar.addActionListener((ev) -> {
			removeSelectedVideo();
		});
		panel_9.add(btnQuitar);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener((ev) -> {
			storeList();
		});
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_10.add(btnAceptar);
		
	
		

	}
	
	private void searchList() {
		String q = text_search.getText();
		Optional<ListaVideos> result = Controlador.getInstance().getLista(q);
		
		

		if(result.isPresent()) {
			// Hay una lista, mostrarla
			
			listaSeleccionada = result;
			btnAceptar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnAñadir.setEnabled(true);
			btnQuitar.setEnabled(true);
			
			
			listVideosModel.clear();
			
			listVideosModel.addAll(result.get().getVideos());
			
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
				
				listaSeleccionada = Optional.of(listaCreada);
				btnAceptar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnAñadir.setEnabled(true);
				btnQuitar.setEnabled(true);
				
				listVideosModel.clear();
			}else {
				btnEliminar.setEnabled(true);
				btnEliminar.setEnabled(false);
				btnAñadir.setEnabled(false);
				btnQuitar.setEnabled(false);
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
	
	private void addSelectedVideo() {
		try {
			Video selectedVideo = (Video)tableVideos.getValueAt(tableVideos.getSelectedRow(), tableVideos.getSelectedColumn());
			
			if(!listVideosModel.contains(selectedVideo)) {
				listVideosModel.addElement(selectedVideo);
				
				EventQueue.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	// Scroll to end
		        		JScrollBar vertical = scrollPane_list_videos.getVerticalScrollBar();
		        		vertical.setValue(vertical.getMaximum());
		            }            
		        });
			}else {
				// El elemento ya ha sido insertado
				JOptionPane.showMessageDialog(this, "El video ya ha sido insertado en la lista", "Ups...", JOptionPane.INFORMATION_MESSAGE);

			}
		}catch(IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(this, "Selecciona primero el video a añadir.", "Ups...", JOptionPane.ERROR_MESSAGE);

		}
		
		
	}
	
	private void removeSelectedVideo() {
		Video selectedVideo = listVideos.getSelectedValue();
		listVideosModel.removeElement(selectedVideo);
	}
	
	private void storeList() {
		if(listaSeleccionada.isPresent()) {
			
			ListaVideos l = listaSeleccionada.get();

			List<Video> videos = new ArrayList<Video>(listVideosModel.size());
			
			for(int j=0;j<listVideosModel.size();j++) {
				videos.add(listVideosModel.getElementAt(j));
			}
			
			l.setVideos(videos);
			
			Controlador.getInstance().updateLista(l);
			
			JOptionPane.showMessageDialog(this, "La lista ha sido actualizada correctamente.", "Lista creada", JOptionPane.INFORMATION_MESSAGE);

			
		}
	}
	
	private void resetSearch() {
		btnAceptar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnAñadir.setEnabled(false);
		btnQuitar.setEnabled(false);
		text_search.setText("");
		listVideosModel.clear();
		
		listaSeleccionada = Optional.empty();

	}
	
	private void deleteList() {
		
		
		if(listaSeleccionada.isEmpty())
			return;
		
		Object[] options = {"Aceptar",
		"Cancelar"};

		int option = JOptionPane.showOptionDialog(this,
				"¿Desea eliminar la lista: '" + listaSeleccionada.get().getNombre() + "'? ",
				"Eliminar lista",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		
		if(option==0) {
			// Eliminar lista
			
			Controlador.getInstance().deleteLista(listaSeleccionada.get());
			
			resetSearch();
			
			
		}
		
	}
	

}
