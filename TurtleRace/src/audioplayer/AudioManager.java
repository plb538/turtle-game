package audioplayer;

import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AudioManager{
	
	private AudioStream audioStream;
	private InputStream audioFileIn;
	
private void startAudio(String file){
		
		try {
			 
			if(file != null){
				audioFileIn = this.getClass().getResourceAsStream(file);
				audioStream = new AudioStream(audioFileIn);
				AudioPlayer.player.start(audioStream);
				} 
	        }
		 catch(Throwable e){
			 e.printStackTrace();
		 }
		
	}
	
	private void stopAudio(){
		AudioPlayer.player.stop(audioStream);
	}
	
	public void updateAudio(String file){
		stopAudio();
		startAudio(file);
	}
	
	public void overlayAudio(String file){
		//System.out.println(file);
		try{
			AudioStream overlay = new AudioStream(this.getClass().getResourceAsStream(file));
			AudioPlayer.player.start(overlay);
		} catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
