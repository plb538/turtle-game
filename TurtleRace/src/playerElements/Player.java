package playerElements;

import weapons.Weapon;
import common.Character;
import graphicalElements.PlayerGUI;

public class Player implements Character{

	Weapon myWeapon = null;
	int armor = 0;
	private int xpos = 0;
	private int ypos = 0;
	int velocity = 0;
	
	public PlayerGUI myGUI = new PlayerGUI(this);
	
	public Player(Weapon chosenWeapon, int _xpos, int _ypos, int _armor){
		myWeapon = chosenWeapon;
		xpos = _xpos;
		ypos = _ypos;
		armor = _armor;		
	}
	
	public void Jump(){
		ypos -= jumpHeight;
		
	}
	
	public void Forwards(){
		xpos += 5;
	}
	
	public void Backwards(){
		xpos -= 5;
	}

	@Override
	public void Attack(){
		// TODO Auto-generated method stub
		
	}
	
	public int getX(){
		return xpos;
	}
	public int getY(){
		return ypos;
	}
	
	public void Fall(){
		ypos += 1;
	}
}
