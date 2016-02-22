package enemyElements;

import weapons.Weapon;

public class Monkey implements Enemy{
	
	int health = 100;
	int armor;
	int xpos;
	int ypos;
	
	public Monkey(Weapon chosenWeapon, int _xpos, int _ypos, int _armor){
		myWeapon = chosenWeapon;
		armor = _armor;
		xpos = _xpos;
		ypos = _ypos;
	}

}
