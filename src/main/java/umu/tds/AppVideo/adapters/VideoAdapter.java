package umu.tds.AppVideo.adapters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import umu.tds.componente.Video;

public class VideoAdapter {

	private umu.tds.componente.Video v;

	private String videoID;
	
	public VideoAdapter(Video v) {
		super();
		this.v = v;
		

	    String pattern = "watch\\?v=(\\w+)";
	    
	    // Create a Pattern object
	    Pattern r = Pattern.compile(pattern);
	    
	    // Now create matcher object.
	    Matcher m = r.matcher(v.getURL());
	    if (m.find( )) {
	         videoID = m.group();
	    } else {
	         System.out.println("NO MATCH");
	    }

	}
	
	public umu.tds.AppVideo.models.Video getVideo(){
		umu.tds.AppVideo.models.Video v2 = new umu.tds.AppVideo.models.Video(v.getTitulo(), videoID);
		return v2;
	}
	
}
