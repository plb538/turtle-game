package playerElements;

import weapons.Weapon;

public class Player implements common.Character{

	Weapon myWeapon;
	
	public Player(Weapon chosenWeapon, int _xpos, int _ypos, int _armor){
		myWeapon = chosenWeapon;
		xpos = _xpos;
		ypos = _ypos;
		armor = _armor;
		
	}
	
}
