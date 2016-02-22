package playerElements;

import weapons.Weapon;
import common.Character;

public class Player implements Character{

	Weapon myWeapon;
	int armor;
	int xpos;
	int ypos;
	
	public Player(Weapon chosenWeapon, int _xpos, int _ypos, int _armor){
		myWeapon = chosenWeapon;
		xpos = _xpos;
		ypos = _ypos;
		armor = _armor;
		
	}
	
}
