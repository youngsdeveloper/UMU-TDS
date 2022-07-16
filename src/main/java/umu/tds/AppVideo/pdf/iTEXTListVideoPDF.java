package umu.tds.AppVideo.pdf;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.AppVideo.gui.VentanaPrincipal;
import umu.tds.AppVideo.models.ListaVideos;
import umu.tds.AppVideo.models.Usuario;
import umu.tds.AppVideo.models.Video;

public class iTEXTListVideoPDF implements ListVideoPDF{

	@Override
	public void create(Usuario usuario) {
		
		FileOutputStream archivo;
		try {
			archivo = new FileOutputStream("videos.pdf");
		    Document documento = new Document();

			PdfWriter.getInstance(documento, archivo);
			
			documento.open();
			
			documento.add(new Paragraph("-------------- App Video --------"));
			documento.add(new Paragraph("-------------- Listas de videos de \"" + usuario.getUsername()  + "\" --------"));

			int j=0;
			for(ListaVideos lv : usuario.getListasVideos()) {
				j++;
				documento.add(new Paragraph("----------------------"));
				documento.add(new Paragraph("Lista #"+j));
				documento.add(new Paragraph(lv.getNombre()));
				documento.add(new Paragraph("Videos"));
				for(Video v:lv.getVideos()) {
					documento.add(new Paragraph("---"));
					documento.add(new Paragraph("Titulo: " + v.getTitulo()));
					documento.add(new Paragraph("Reproducciones: " + v.getNumReproducciones()));

				}
				documento.add(new Paragraph("----------------------"));

			}
			
			documento.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
	}

}
