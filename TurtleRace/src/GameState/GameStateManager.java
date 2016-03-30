package GameState;

import java.awt.Graphics2D;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GameStateManager{
	
	private ArrayList<GameState> gameStates;
	private int curState;
	
	public boolean isHost;
	public ServerSocket hostSocket;
	public Socket clientSocket;
	public boolean modeMultiplayer = false;
	public ObjectOutputStream outToServer;
	public ObjectInputStream inFromServer;
	
	private InputStream audioFileIn;
	private AudioStream audioStream;
	private AudioPlayer audioPlayer;

	//different game states
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;
	public static final int OPTIONSTATE = 2;
	public static final int ENDSTATE = 3;
	public static final int LEVELSTATE2 = 4;
	public static final int JUMPPUZZLE1 = 5;
	public static final int SETUPSTATE = 6;
	
	
	public GameStateManager(){
		gameStates = new ArrayList<GameState>();
		//starts game at menu
		curState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new LevelState(this));
		gameStates.add(new OptionState(this));
		gameStates.add(new EndState(this));
		gameStates.add(new LevelState2(this));
		gameStates.add(new JumpPuzzle1(this));
		gameStates.add(new SetupState(this));
		//String filename = "C:/Users/steve/git/Paddy-Steven/TurtleRace/resources/Audio/Test.wav";
		String filename = "/Audio/Test.wav";
		startAudio(filename);
	}
	
	public void setState(int state){
		curState = state;
		gameStates.get(curState).init();
	}
	
	public int getState(){return curState;}
	
	//calls the current gamestates update method
	public void update(){
		try{
			gameStates.get(curState).update();
		} catch(Throwable e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//calls the current gamestates draw method
	public void draw(Graphics2D g){
		gameStates.get(curState).draw(g);
	}
	//calls the current gamestates keyPressed method
	public void keyPressed(int k){
		gameStates.get(curState).keyPressed(k);
		
	}
	
	public void keyReleased(int k){
		gameStates.get(curState).keyReleased(k);
	}
	public void startAudio(String filename){
		
		try {
			 
			
			audioFileIn = this.getClass().getResourceAsStream(filename);
			audioStream = new AudioStream(audioFileIn);
			AudioPlayer.player.start(audioStream);
			
			 
	        }
		 catch(Throwable e){
			 e.printStackTrace();
		 }
		
	}
	
	public void stopAudio(){
		AudioPlayer.player.stop(audioStream);
	}
	
	public void updateAudio(String file){
		
	}
}
