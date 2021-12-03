package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import umu.tds.AppVideo.models.Video;

public class VideoTableCellRenderer extends DefaultTableCellRenderer{
	public VideoTableCellRenderer() {
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        JLabel label = ((JLabel)cellComponent);

        ImageIcon thumbnail = null;

        if(value instanceof Video) {
        	
        	Video video = (Video)value;
        	
        	label.setText(video.getTitulo());
        	
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


        
        

        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);

        return cellComponent;
    }
	
}
