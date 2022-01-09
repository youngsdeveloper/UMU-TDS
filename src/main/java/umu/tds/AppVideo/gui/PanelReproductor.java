package umu.tds.AppVideo.gui;

import java.awt.FlowLayout;

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
import javax.swing.AbstractListModel;

public class PanelReproductor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JTextField textField;

	
	private JPanel panel_reproductor;
	private VideoWeb reproductor;
	
	private JLabel label_titulo_video;
	private JLabel lbl_num_reproducciones;
	private DefaultListModel<Etiqueta> modelEtiquetas;
	
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
		panel.add(label_titulo_video);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		
		lbl_num_reproducciones = new JLabel("Número de reproducciones");
		panel_1.add(lbl_num_reproducciones);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		panel_reproductor = new JPanel();
		panel_4.add(panel_reproductor);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JList<Etiqueta> list = new JList<Etiqueta>();
		
		modelEtiquetas = new DefaultListModel<Etiqueta>();
		list.setModel(modelEtiquetas);
		list.setCellRenderer(new EtiquetaListCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(1);

		panel_5.add(list);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Nueva etiqueta: ");
		panel_3.add(lblNewLabel_2);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Añadir");
		panel_3.add(btnNewButton);

		if(Controlador.env != Environment.WBUILDER) {
			configurarReproductor();
		}

	}
	
	public void loadVideo(Video video) {
		reproductor.playVideo(video.getURL());
		
		label_titulo_video.setText(video.getTitulo());
		
		System.out.println(video.getEtiquetas());
		
		modelEtiquetas.clear();
		modelEtiquetas.addAll(video.getEtiquetas());
	}
	

	private void configurarReproductor() {
		reproductor = new VideoWeb();
		panel_reproductor.add(reproductor);
	}

}
