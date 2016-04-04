package GameState;

//Imports
import java.awt.Graphics2D;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import audioplayer.AudioManager;
import networking.InformationPacket;
import networking.ReadThread;
import networking.SendThread;

//Class
public class GameStateManager{
	
	//State variables
	public ArrayList<GameState> gameStates;
	private int curState;
	
	//Multiplayer variables
	public boolean isHost = false;
	public ServerSocket hostSocket = null;
	public Socket clientSocket = null;
	public boolean modeMultiplayer = false;
	public ObjectOutputStream outToServer = null;
	public ObjectInputStream inFromServer = null;
	private ReadThread readThread = null;
	private SendThread sendThread = null;
	public boolean connected = false;
	
	//Audio variables
	private AudioManager audioManager;

	//different game states
	public static final int MENUSTATE = 0;
	public static final int OPTIONSTATE = 1;
	public static final int SETUPSTATE = 2;
	public static final int LEVELSTATE = 3;
	public static final int LEVELSTATE2 = 4;
	public static final int JUMPPUZZLE1 = 5;
	public static final int JUMPPUZZLE2 = 6;
	public static final int JUMPPUZZLE3 = 7;
	public static final int JUMPPUZZLE4 = 8;
	public static final int ENEMYLEVEL1 = 9;
	public static final int ENEMYLEVEL2 = 10;
	public static final int ENEMYLEVEL3 = 11;	
	public static final int ENEMYLEVEL4 = 12;
	public static final int ENDSTATE = 13;
	public static final int TurtleDesigner = 14;
	
	//Constructor
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
		gameStates.add(new JumpPuzzle2(this));
		gameStates.add(new JumpPuzzle3(this));
		gameStates.add(new JumpPuzzle4(this));
		gameStates.add(new EnemyLevel1(this));
		gameStates.add(new EnemyLevel2(this));
		gameStates.add(new EnemyLevel3(this));
		gameStates.add(new EnemyLevel4(this));
		gameStates.add(new EndState(this));
		gameStates.add(new DesignerState(this));
		
		//Start Audio
		String filename = "/Audio/music/EarthyCrust.wav";
		audioManager = new AudioManager();
		audioManager.updateAudio(filename);
		
		//Initlize multiplayer to false
		connected = false;
		modeMultiplayer = false;
	}
	
	/*
	 * setState(int state)
	 * -Updates the state of the game to the passed in state
	 * -Also starts the in game music if the state is a mutiple of 3
	 */
	public void setState(int state){
		if((state % 3 == 0) && state != 0){
			audioManager.updateAudio("/Audio/music/OrganDonorQuiet.wav");
		}
		curState = state;
		gameStates.get(curState).init();
	}
	
	//Returns the current state
	public int getState(){return curState;}
	
	//calls the current gamestates update method
	public void update(){
		try{
			audioManager.checkLoop();
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
	
	//Passes key event to the current game state
	public void keyReleased(int k){
		gameStates.get(curState).keyReleased(k);
	}
	
	//Passes received packet to the state to update monkeys
	public void updateP2Thread(InformationPacket test){
		gameStates.get(curState).updateMonkeyPosP2(test);
	}
	
	//Passes files to the audio manager to overlay a sound effect over the music
	public void overlayAudio(String file){
		audioManager.overlayAudio(file);
	}

	//Updates the music to the passed in file
	//If null is passed in then the music stops
	public void updateAudio(String file){
		audioManager.updateAudio(file);
		
	}
	
	//Start multiplayer threads
	public void startMultiplayer(){
		readThread = new ReadThread(this);
		sendThread = new SendThread(this);
	}
}
