package networking;
import Entity.Player;
public class InformationPacket{

	
	private int state;
	private int xpos;
	private int ypos;
	private int health;
	
	public InformationPacket(Player player, int _state){
		
		state = _state;
		xpos = player.getx();
		ypos = player.gety();
		health = player.getHealth();
		
	}
	
	public void update(Player player, int _state){
		state = _state;
		xpos = player.getx();
		ypos = player.gety();
		health = player.getHealth();
	}
	
	public int getx(){
		return xpos;
	}
	
	public int gety(){
		return ypos;
	}
	
	public int gethealth(){
		return health;
	}
	
	public int getstate(){
		return state;
	}
	
	
}
