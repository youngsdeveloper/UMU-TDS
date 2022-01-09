package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import net.miginfocom.swing.MigLayout;
import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Video;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import tds.video.VideoWeb;

public class VentanaLista extends JPanel {
	
	public final static String TAG = "VentanaLista";

	private JComboBox<String> combo_listas;
	
	private ListaVideos listaSeleccionada;
	private DefaultListModel<Video> listVideosModel;

	private List<ListaVideos> listasVideo = new ArrayList<ListaVideos>(0);
	
	private VideoWeb videoWeb;
	
	private JList<Video> listVideos;
	PanelReproductor panelReproductor;
	/**
	 * Create the panel.
	 */
	public VentanaLista() {
		setLayout(new MigLayout("", "[::250px,grow][400px:n,grow]", "[::97.00px,grow][grow][]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSeleccionar = new JLabel("Seleccionar la lista:");
		lblSeleccionar.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblSeleccionar);

		
		listVideosModel = new DefaultListModel<Video>();

		listVideos = new JList<Video>(listVideosModel);
		
		listVideos.setCellRenderer(new VideoListCellRenderer());

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(listVideos);

		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
        		
        		
            	listasVideo = Controlador.getInstance().getListasUsuario();	
            	
            	
            	System.out.println(listasVideo);
        		String nombresListas[] = listasVideo
        				.stream()
        				.map(lista -> lista.getNombre())
        				.toArray(String[]::new);
        		
        		DefaultComboBoxModel<String> modelListas = new DefaultComboBoxModel<>(nombresListas);
        		combo_listas.setModel(modelListas);
        		
        		
        		if(listasVideo.size()>0) {
            		loadLista(listasVideo.get(0));
        		}
            }            
        });
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		combo_listas  = new JComboBox<String>();
		panel_2.add(combo_listas);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		JButton btnReproducir = new JButton("Reproducir");
		btnReproducir.addActionListener((ev) -> {
			panelReproductor.loadVideo(getSelectedVideo());
		});
		panel_3.add(btnReproducir);
		
		panelReproductor = new PanelReproductor();
		add(panelReproductor, "cell 1 1,grow");
		
		add(scrollPane, "cell 0 1,grow");
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "cell 0 2,alignx center");
		
		combo_listas.addActionListener((ev) -> {
			loadLista(listasVideo.get(combo_listas.getSelectedIndex()));
		});

	}
	
	private void loadLista(ListaVideos lista) {
		this.listaSeleccionada = lista;
		if(listVideosModel!=null) {
			listVideosModel.clear();		
			listVideosModel.addAll(lista.getVideos());
		}
		
	}
	
	private Video getSelectedVideo() {
		return listVideosModel.get(listVideos.getSelectedIndex());
	}
	

}
