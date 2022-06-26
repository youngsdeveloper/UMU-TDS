package umu.tds.AppVideo.gui;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class VentanaRecientes extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String TAG = "VentanaRecientes";
	
	private DefaultListModel<Video> listVideosModel;

		
	private JList<Video> listVideos;
	PanelReproductor panelReproductor;
	
	private JLabel title_panel;
	private JButton btnRecientes, btnMasVistos;
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
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		title_panel = new JLabel("Videos recientes");
		panel_2.add(title_panel);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		btnRecientes = new JButton("Recientes");
		panel_4.add(btnRecientes);
		btnRecientes.addActionListener((ev)->{
			loadRecientes();
		});
		
		btnMasVistos = new JButton("Más vistos");
		panel_4.add(btnMasVistos);
		btnMasVistos.addActionListener((ev)->{
			loadMasVistos();
		});

		
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
	
	private void setupUI(){
		Usuario usuarioActual = Controlador.getInstance().getUsuarioActual().get();
		
		if(usuarioActual.isPremium()) {
			btnMasVistos.setEnabled(true);
		}else {
			btnMasVistos.setEnabled(false);
		}
	}
	
	private void loadMasVistos(){
		btnRecientes.setEnabled(true);
		btnMasVistos.setEnabled(false);
		title_panel.setText("Videos más vistos");

		if(listVideosModel!=null) {
			listVideosModel.clear();		
			listVideosModel.addAll(Controlador.getInstance().getVideosMasVistos());
		}
	}
	
	private void loadRecientes() {
		btnRecientes.setEnabled(false);
		title_panel.setText("Videos recientes");
		if(listVideosModel!=null) {
			listVideosModel.clear();		
			listVideosModel.addAll(Controlador.getInstance().getUsuarioActual().get().getRecientes());
		}
		setupUI();
	}
	
	private Video getSelectedVideo() {
		return listVideosModel.get(listVideos.getSelectedIndex());
	}
	

}
