package umu.tds.AppVideo.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import umu.tds.AppVideo.models.Video;

public class VideoListCellRenderer extends DefaultListCellRenderer{
	public VideoListCellRenderer() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		
		
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        JLabel label = (JLabel)c;

        ImageIcon thumbnail = null;

        if(value instanceof Video) {
        	
        	Video video = (Video)value;
        	
        	int MAX_CHAR = 40;
        	String titulo = video.getTitulo();
        	if(titulo.length()>MAX_CHAR) {
        		titulo = titulo.substring(0,MAX_CHAR-3).concat("...");
        	}
        	label.setText(titulo);
        	
            try {
                URL url = new URL(video.getThumbnailURL());
				BufferedImage img = ImageIO.read(url);

				// Resize image
				int label_w = 90;
				int label_h = 90;

				Image dimg = img.getScaledInstance(label_w, label_h,Image.SCALE_SMOOTH);
				
				thumbnail = new ImageIcon(dimg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }else {
        	// Load logo as thumbnail
        	thumbnail = new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/AppVideo/images/logo-100.png"));
        }
        
		label.setIcon(thumbnail);
		label.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(CENTER_ALIGNMENT);        
        

        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        
		return label;
	}
	
}
