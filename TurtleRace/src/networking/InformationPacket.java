package networking;
import Entity.Player;
public class InformationPacket{

	
	private int state;
	private int xpos;
	private int ypos;
	private int health;
	
	/*
	 * Constructor
	 * Initializes the values to the player
	 */
	public InformationPacket(Player player, int _state){
		
		state = _state;
		xpos = player.getx();
		ypos = player.gety();
		health = player.getHealth();
		
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
	}
	
	/*
	 * Returns most recent x position
	 */
	public int getx(){
		return xpos;
	}
	
	/*
	 * Returns most recent y position
	 */
	public int gety(){
		return ypos;
	}
	
	/*
	 * Returns most recent health
	 */
	
	public int gethealth(){
		return health;
	}
	/*
	 * returns the state the player is currently in
	 */
	public int getstate(){
		return state;
	}
	
	
}
