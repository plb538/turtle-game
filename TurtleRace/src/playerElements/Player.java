package playerElements;

import weapons.Weapon;
import common.Character;
import graphicalElements.PlayerGUI;

public class Player implements Character{

	Weapon myWeapon = null;
	int armor = 0;
	int xpos = 0;
	int ypos = 0;
	int velocity = 0;
	
	public PlayerGUI myGUI = new PlayerGUI(this);
	
	public Player(Weapon chosenWeapon, int _xpos, int _ypos, int _armor){
		myWeapon = chosenWeapon;
		xpos = _xpos;
		ypos = _ypos;
		armor = _armor;		
	}
	
	public void Jump(){
		if(this.ypos == 720*2/3){
			this.ypos += this.jumpHeight;
		}
	}
	
	public void Forwards(){
		this.xpos += 25;
	}
	
	public void Backwards(){
		this.xpos -= 25;
	}

	@Override
	public void Attack(){
		// TODO Auto-generated method stub
		
	}
	
}
