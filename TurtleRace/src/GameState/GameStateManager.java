package GameState;

import java.awt.Graphics2D;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Main.Game;
import audioplayer.AudioManager;
import networking.ReadThread;
import networking.SendThread;

public class GameStateManager{
	
	private ArrayList<GameState> gameStates;
	private int curState;
	
	public boolean isHost = false;
	public ServerSocket hostSocket = null;
	public Socket clientSocket = null;
	public boolean modeMultiplayer = false;
	public ObjectOutputStream outToServer = null;
	public ObjectInputStream inFromServer = null;
	
	private ReadThread readThread = null;
	private SendThread sendThread = null;
	public boolean connected = false;
	
	private AudioManager audioManager;

	//different game states
	public static final int MENUSTATE = 0;
	public static final int OPTIONSTATE = 1;
	public static final int SETUPSTATE = 2;
	public static final int LEVELSTATE = 3;
	public static final int LEVELSTATE2 = 4;
	public static final int JUMPPUZZLE1 = 5;
	public static final int ENDSTATE = 6;
	
	
	public GameStateManager(){
		gameStates = new ArrayList<GameState>();
		//starts game at menu
		curState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new OptionState(this));
		gameStates.add(new SetupState(this));
		gameStates.add(new LevelState(this));
		gameStates.add(new LevelState2(this));
		gameStates.add(new JumpPuzzle1(this));
		gameStates.add(new EndState(this));
		
		String filename = "/Audio/music/track1.wav";
		audioManager = new AudioManager();
		audioManager.updateAudio(filename);
		
		connected = false;
		modeMultiplayer = false;
	}
	
	public void setState(int state){
		if(state == 3){
			audioManager.updateAudio("/Audio/music/track2.wav");
		}
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
	
	
	public void updateP2Thread(){
		
	}
	
	public void overlayAudio(String file){
		audioManager.overlayAudio(file);
	}

	public void updateAudio(String file){
		audioManager.updateAudio(file);
		
	}
	
	public void startMultiplayer(){
		readThread = new ReadThread(this);
		sendThread = new SendThread(this);
		
	}
}
