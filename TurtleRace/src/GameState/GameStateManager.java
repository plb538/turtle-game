package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import common.Character;
import levelElements.Level;

public class GameStateManager{
	
	private ArrayList<GameState> gameStates;
	private int curState;
	
	//different game states
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;
	public static final int OPTIONSTATE = 2;
	public static final int CHALLENGESTATE = 4;
	public static final int ENDSTATE = 3;
	
	private Level myLevel = null;
	public Character player1 = null;
	
	public GameStateManager(Level _myLevel, Character _player1){
		gameStates = new ArrayList<GameState>();
		curState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new LevelState(this));
		gameStates.add(new OptionState(this));
		gameStates.add(new EndState(this));

		myLevel = _myLevel;
		player1 = _player1;
	}
	
	public void setState(int state){

		if(state > 3){
			try{
				gameStates.add(new ChallengeState(this, myLevel.level.get(state-4), state+1));
			}catch(Throwable e){
				state = 3;
			}
		}
		curState = state;
		gameStates.get(curState).init();
	}
	
	public int getState(){return curState;}
	
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
