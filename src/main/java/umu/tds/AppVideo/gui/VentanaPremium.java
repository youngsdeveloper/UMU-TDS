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

public class VentanaPremium extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public final static String TAG = "VentanaPremium";
	

	JButton btnPremium;
	JLabel label_title;
	
	/**
	 * Create the panel.
	 */
	public VentanaPremium() {
		setLayout(new MigLayout("", "[::10px,grow][400px:n,grow][]", "[::14.00px,grow][::50,grow][][][][]"));
		
		label_title = new JLabel("¿Aún no eres Premium?");
		label_title.setFont(new Font("Lato Black", Font.BOLD, 16));
		add(label_title, "cell 1 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaPremium.class.getResource("/umu/tds/AppVideo/images/ventajas.png")));
		add(lblNewLabel, "cell 1 2,alignx center");
		
		btnPremium = new JButton("Convertirse en Premium");
		btnPremium.setForeground(new Color(255, 255, 255));
		btnPremium.setBackground(new Color(204, 51, 51));
		add(btnPremium, "cell 1 4,alignx center");
		
		
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
			
			
		}else {
			btnPremium.setText("Convertirse en Premium");
			label_title.setText("¿Aún no eres Premium?");
			label_title.setForeground(Color.DARK_GRAY);
			
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
	
	

}
