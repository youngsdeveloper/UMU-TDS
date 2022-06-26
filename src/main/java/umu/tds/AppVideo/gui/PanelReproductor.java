package umu.tds.AppVideo.gui;

import java.awt.FlowLayout;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import tds.video.VideoWeb;
import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.controlador.Controlador.Environment;
import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Video;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;

public class PanelReproductor extends JPanel {
	/**
	 * 
	 */
	
	public final static String TAG = "VentanaReproductor";

	private static final long serialVersionUID = 1L;



	
	private JPanel panel_reproductor;
	private VideoWeb reproductor;
	
	private JLabel label_titulo_video;
	private JLabel lbl_num_reproducciones;
	private DefaultListModel<Etiqueta> modelEtiquetas;
	
	private JPanel panel_etiquetas;
	private JPanel panel_nueva_etiqueta;
	private JList<Etiqueta> listEtiquetas;
	
	private JButton btn_crear_etiqueta;
	private JTextField text_crear_etiqueta;
	private Optional<Video> video = Optional.empty();
	
	private boolean hasToAddToRecentes = true; // Can set to false

	/**
	 * Create the panel.
	 */
	public PanelReproductor() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_2.add(panel);
		
		label_titulo_video = new JLabel("Titulo del video");
		label_titulo_video.setVisible(false);
		panel.add(label_titulo_video);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		
		lbl_num_reproducciones = new JLabel("Reproducciones");
		lbl_num_reproducciones.setVisible(false);
		panel_1.add(lbl_num_reproducciones);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		panel_reproductor = new JPanel();
		panel_4.add(panel_reproductor);
		
		panel_etiquetas = new JPanel();

		panel_2.add(panel_etiquetas);
		
		listEtiquetas = new JList<Etiqueta>();
		listEtiquetas.setVisible(false);
		
		modelEtiquetas = new DefaultListModel<Etiqueta>();
		listEtiquetas.setModel(modelEtiquetas);
		listEtiquetas.setCellRenderer(new EtiquetaListCellRenderer());
		listEtiquetas.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listEtiquetas.setVisibleRowCount(1);

		panel_etiquetas.add(listEtiquetas);
		
		panel_nueva_etiqueta = new JPanel();
		panel_nueva_etiqueta.setVisible(false);
		panel_2.add(panel_nueva_etiqueta);
		
		JLabel lblNewLabel_2 = new JLabel("Nueva etiqueta: ");
		panel_nueva_etiqueta.add(lblNewLabel_2);
		
		text_crear_etiqueta = new JTextField();
		panel_nueva_etiqueta.add(text_crear_etiqueta);
		text_crear_etiqueta.setColumns(10);
		text_crear_etiqueta.addActionListener((a) -> {
			insertarEtiqueta();
		});
		
		btn_crear_etiqueta = new JButton("Añadir");
		panel_nueva_etiqueta.add(btn_crear_etiqueta);
		
		btn_crear_etiqueta.setEnabled(false);
		
		btn_crear_etiqueta.addActionListener((a)->{
			insertarEtiqueta();
		});
		
		
		
		

		// Necesario para poder editar en WBuilder
		/*if(Controlador.getInstance().env != Environment.WBUILDER) {
			configurarReproductor();

		}else {
			System.out.println("Conf rup");

		}*/
		configurarReproductor();


	}
	
	public void setHasToAddToRecentes(boolean hasToAddToRecentes) {
		this.hasToAddToRecentes = hasToAddToRecentes;
	}
	
	public void loadVideo(Video video) {
		
		video = Controlador.getInstance().sumarReproduccion(video);
		
		lbl_num_reproducciones.setText("Reproducciones: " + video.getNumReproducciones());
		lbl_num_reproducciones.setVisible(true);
		
		reproductor = SingletonReproductor.getInstance();
		panel_reproductor.add(reproductor);
		
		reproductor.playVideo(video.getURL());
		
		label_titulo_video.setText(video.getTitulo());
		lbl_num_reproducciones.setVisible(true);

		
		listEtiquetas.setVisible(true);
		panel_nueva_etiqueta.setVisible(true);

		modelEtiquetas.clear();
		modelEtiquetas.addAll(video.getEtiquetas());
		
		btn_crear_etiqueta.setEnabled(true);
		
		this.video = Optional.of(video);
		
		if(hasToAddToRecentes) {
			Controlador.getInstance().addVideoRecientes(video);
		}
	}
	

	private void configurarReproductor() {
		//System.out.println("Configurando reproductor...");
		
	}
	
	private void insertarEtiqueta() {
		
		if(text_crear_etiqueta.getText().isEmpty())
			return;
		
		if(video.isEmpty())
			return;
		
		Video video = this.video.get();
		
		String name_etiqueta = text_crear_etiqueta.getText();
		Etiqueta etiqueta = new Etiqueta(name_etiqueta);
		
		if(video.getEtiquetas().contains(etiqueta)) {
			// Video contiene etiqueta ya, no se puede.
			JOptionPane.showMessageDialog(this, "El video ya tiene esa etiqueta asignada.", "Ups...", JOptionPane.ERROR_MESSAGE);

			return;
		}
		
		video = Controlador.getInstance().insertarEtiquetaVideo(etiqueta, video);
		this.video = Optional.of(video);
		
		video.insertEtiqueta(etiqueta);

		modelEtiquetas.clear();
		modelEtiquetas.addAll(video.getEtiquetas());
		
		text_crear_etiqueta.setText(""); // Reset text
		
	}

}
