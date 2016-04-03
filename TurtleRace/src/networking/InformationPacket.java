package networking;
import java.util.List;
import java.util.ArrayList;

import Entity.Animation;
import Entity.MonkeyEnemy;
import Entity.Player;

public class InformationPacket implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int state;
	private int xpos;
	private int ypos;
	private int health;
	private int action;
	private long timestamp;
	private boolean facingRight;
	private List<Integer> monkeyX = new ArrayList<>();
	private List<Integer> monkeyY = new ArrayList<>();
	private List<Integer> monkeyHP = new ArrayList<>();
	//private boolean isAttacking;
	
	/*
	 * Constructor
	 * Initializes the values to the player
	 */
	public InformationPacket(Player player, int _state, ArrayList<MonkeyEnemy> monkeys){
		
		state = _state;
		xpos = player.getx();
		ypos = player.gety();
		health = player.getHealth();
		action = player.getAction();
		timestamp= System.nanoTime();
		facingRight = player.getFacingRight();
		//isAttacking = player.checkIfAttacking();
		
		if(monkeys != null && monkeys.size() > 0){
			for(int i = 0; i < monkeys.size(); i++){
				monkeyX.add(monkeys.get(i).getx());
				monkeyY.add(monkeys.get(i).gety());
				monkeyHP.add(monkeys.get(i).getHealth());
			}
		}
		
	}
	/*
	 * Invoked on each game thread update to update the player's state
	 * To be sent to the other player
	 */
	public void update(Player player, int _state){
		state = _state;
		xpos = player.getx();
		ypos = player.gety();
		health = player.getHealth();
		action = player.getAction();
		timestamp = System.nanoTime();
		facingRight = player.getFacingRight();
		//isAttacking = player.checkIfAttacking();
	}
	/*
	 * Returns most recent x position
	 */
	public int getx(){return xpos;}
	/*
	 * Returns most recent y position
	 */
	public int gety(){return ypos;}
	/*
	 * Returns most recent health
	 */
	public int gethealth(){return health;}
	/*
	 * returns the state the player is currently in
	 */
	public int getstate(){return state;}
	
	public long getTime(){return timestamp;}
	
	public int getaction(){return action;}
	
	public boolean getFacingRight(){return facingRight;}
	
	//public boolean checkIfAttacking(){return isAttacking;}
	
	public int getMonkeyX(int index){
		return this.monkeyX.get(index);
	}
	
	public int getMonkeyY(int index){
		return this.monkeyY.get(index);
	}
	
	public int getMonkeyHP(int index){
		return this.monkeyHP.get(index);
	}
	
	
	
}
