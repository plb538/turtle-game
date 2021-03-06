package Entity;

import java.awt.image.BufferedImage;

public class Animation{
	
	//array of sprite images
	private BufferedImage[] frames;
	private int curFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animation(){
		playedOnce = false;
	}
	//changes frames for different animation
	public void setFrames(BufferedImage[] frames){
		this.frames = frames;
		curFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	public void setFrame(int i){curFrame = i;}
	
	public int getFrame(){return curFrame;}
	
	public void setDelay(long d){delay = d;}
	
	public long getDelay(){return delay;}
	
	//plays the animation 
	public void update(){
		playedOnce = false;
		if(delay == -1) return;
		
		long elapsed = (Math.abs(System.nanoTime() - startTime) / 1000000);
		if(elapsed > delay){
			curFrame++;
			startTime = System.nanoTime();
		}
		if(curFrame == frames.length){
			curFrame = 0;
			playedOnce = true;
		}
	}
	
	public BufferedImage getImage(){return frames[curFrame];}
	
	public boolean hasPlayedOnce(){return playedOnce;}
}
