package umu.tds.AppVideo.gui;

import tds.video.VideoWeb;

public class SingletonReproductor {

	private static VideoWeb instance;
	
	public static VideoWeb getInstance() {
		if(instance==null) {
			instance = new VideoWeb();
		}
		instance.cancel();
		return instance;
	}
}
