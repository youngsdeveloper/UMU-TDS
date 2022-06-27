package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import umu.tds.AppVideo.controlador.Controlador;
import umu.tds.AppVideo.models.Usuario;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class VentanaPremium extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public final static String TAG = "VentanaPremium";
	

	JButton btnPremium;
	JLabel label_title;
	private JPanel panel;
	private JPanel panel_premium_options;
	private JButton btnGenerarPdfListas;
	private JPanel panel_1;
	
	/**
	 * Create the panel.
	 */
	public VentanaPremium() {
		setLayout(new MigLayout("", "[400px:n,grow]", "[::50,grow][][40px:n][grow]"));
		
		label_title = new JLabel("¿Aún no eres Premium?");
		label_title.setFont(new Font("Lato Black", Font.BOLD, 16));
		add(label_title, "cell 0 0,alignx center");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPremium.class.getResource("/umu/tds/AppVideo/images/ventajas.png")));
		add(lblNewLabel, "cell 0 1,alignx center");
		
		btnPremium = new JButton("Convertirse en Premium");
		btnPremium.setForeground(new Color(255, 255, 255));
		btnPremium.setBackground(new Color(204, 51, 51));
		add(btnPremium, "cell 0 2,alignx center");
		
		panel_premium_options = new JPanel();
		panel_premium_options.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones premium", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		add(panel_premium_options, "cell 0 3,grow");
		panel_premium_options.setLayout(new MigLayout("", "[400px:n,grow]", "[grow][50px:n,grow]"));
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_premium_options.add(panel, "cell 0 0,grow");
		
		
		btnGenerarPdfListas = new JButton("Generar PDF Listas Video");
		btnGenerarPdfListas.setHorizontalAlignment(SwingConstants.LEFT);
		btnGenerarPdfListas.setForeground(Color.WHITE);
		btnGenerarPdfListas.setBackground(new Color(204, 51, 51));
		btnGenerarPdfListas.addActionListener(action -> {
			generatePDF();
		});
		panel.add(btnGenerarPdfListas);
		
		panel_1 = new JPanel();
		panel_premium_options.add(panel_1, "cell 0 1,grow");
		
		
		btnPremium.addActionListener((a)->{
			Usuario usuarioActual = Controlador.getInstance().getUsuarioActual().get();
			if(usuarioActual.isPremium()) {
				cancelPremium();
			}else {
				convertToPremium();
			}
		});
		setupUI();

		

	}
	
	private void setupUI() {
		Usuario usuarioActual = Controlador.getInstance().getUsuarioActual().get();

		
		if(usuarioActual.isPremium()) {
			btnPremium.setText("Cancelar premium");
			label_title.setText("¡Ya eres Premium!");
			label_title.setForeground(Color.GREEN);
			panel_premium_options.setVisible(true);

			
		}else {
			btnPremium.setText("Convertirse en Premium");
			label_title.setText("¿Aún no eres Premium?");
			label_title.setForeground(Color.DARK_GRAY);
			panel_premium_options.setVisible(false);

		}
	}
	
	// Simular cancelacion (2,5s)
	private void cancelPremium() {
		btnPremium.setText("Cancelando...");
		
		new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
            	Controlador.getInstance().setPremium(false);

        		new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                    	setupUI();

                    }
                }, 400);

            }
        }, 2000);
		
		


	}
	
	private void convertToPremium() {

		realizarPago();
	}
	
	// Simular pago (2s)
	private void realizarPago() {
		
		btnPremium.setText("Realizando pago...");

		
		new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
        		btnPremium.setText("Pago realizado correctamente");
        		
        		Controlador.getInstance().setPremium(true);


        		new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                    	setupUI();

                    }
                }, 800);

            }
        }, 2000);


	}
	
	private void generatePDF() {
		Controlador.getInstance().generarPDF();
	}
	
	

}
