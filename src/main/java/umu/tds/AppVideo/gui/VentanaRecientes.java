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

public class VentanaRecientes extends JPanel {
	
	public final static String TAG = "VentanaRecientes";
	
	private DefaultListModel<Video> listVideosModel;

	
	private VideoWeb videoWeb;
	
	private JList<Video> listVideos;
	PanelReproductor panelReproductor;
	/**
	 * Create the panel.
	 */
	public VentanaRecientes() {
		setLayout(new MigLayout("", "[::250px,grow][400px:n,grow]", "[::97.00px,grow][grow][]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblVideosRecientes = new JLabel("Videos recientes");
		panel_1.add(lblVideosRecientes);

		
		listVideosModel = new DefaultListModel<Video>();

		listVideos = new JList<Video>(listVideosModel);
		
		listVideos.setCellRenderer(new VideoListCellRenderer());

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(listVideos);

		
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
		panelReproductor.setHasToAddToRecentes(false); // Hacemos que no se añadan videos a recientes al reproducir...
		add(panelReproductor, "cell 1 1,grow");
		
		add(scrollPane, "cell 0 1,grow");
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "cell 0 2,alignx center");
		
		loadRecientes();
		
		Controlador.getInstance().addUsuarioUpdatedListener((u) -> {
			loadRecientes();
		});

	}
	
	private void loadRecientes() {
		if(listVideosModel!=null) {
			listVideosModel.clear();		
			listVideosModel.addAll(Controlador.getInstance().getUsuarioActual().get().getRecientes());
		}
	}
	
	private Video getSelectedVideo() {
		return listVideosModel.get(listVideos.getSelectedIndex());
	}
	

}
