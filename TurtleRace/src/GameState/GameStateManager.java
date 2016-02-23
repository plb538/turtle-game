package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import levelElements.Level;

public class GameStateManager{
	
	private ArrayList<GameState> gameStates;
	private int curState;
	
	//different game states
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;
	public static final int OPTIONSTATE = 2;
	public static final int CHALLENGESTATE = 3;
	
	private Level myLevel = null;
	
	public GameStateManager(Level _myLevel){
		gameStates = new ArrayList<GameState>();
		curState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new LevelState(this));
		gameStates.add(new OptionState(this));
		//gameStates.add(new ChallengeState(this));
		myLevel = _myLevel;
	}
	
	public void setState(int state){
		curState = state;
		gameStates.get(curState).init();
	}
	
	public void update(){
		gameStates.get(curState).update();
	}
	
	public void draw(Graphics2D g){
		gameStates.get(curState).draw(g);
	}
	
	public void keyPressed(int k){
		gameStates.get(curState).keyPressed(k);
	}
	
	public void keyReleased(int k){
		gameStates.get(curState).keyReleased(k);
	}
}
