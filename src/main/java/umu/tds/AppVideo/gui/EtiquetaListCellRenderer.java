package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import umu.tds.AppVideo.models.Etiqueta;
import umu.tds.AppVideo.models.Video;

public class EtiquetaListCellRenderer extends DefaultListCellRenderer{
	public EtiquetaListCellRenderer() {
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
        
        if(value instanceof Etiqueta) {
        	
        	Etiqueta etiqueta = (Etiqueta)value;

        	label.setText(etiqueta.getNombre());
        }

		label.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setAlignmentY(CENTER_ALIGNMENT);        
        

        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setBackground(Color.LIGHT_GRAY);
        label.setForeground(Color.BLACK);

        
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        border = new CompoundBorder(border, new EmptyBorder(0,10,0,10));
        
        label.setBorder(border);//top,left,bottom,right

        
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        
		return label;
	}
	
}
