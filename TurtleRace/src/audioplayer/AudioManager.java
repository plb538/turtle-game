package audioplayer;

import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AudioManager{
	
	//Variables
	private AudioStream audioStream = null;
	private InputStream audioFileIn = null;
	
	//StartAudio()
	/*
	 * -Called by public method updateAudio()
	 * -Starts a new audio stream on the player
	 */
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

/*
 * Intended to check if the audio has ended and to restart it
 * Does not work
 * 
 */
	public void checkLoop(){
		if(!(AudioPlayer.player.player.isAlive())){
			AudioPlayer.player.start(audioStream);
		}
	}
	
	/*
	 * Stops the current audio stream
	 */
	private void stopAudio(){
		AudioPlayer.player.stop(audioStream);
	}
	
	//Stops the current stream and then starts a new stream
	public void updateAudio(String file){
		stopAudio();
		startAudio(file);
	}
	
	
	//Overlays a sound effect over the music
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
