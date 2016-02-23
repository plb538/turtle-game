package enemyElements;

import weapons.Weapon;

public class Monkey implements Enemy{
	
	Weapon myWeapon=null;
	public int health = 100;
	public int maxHealth = 100;
	int armor=0;
	int xpos=0;
	int ypos=0;
	
	public Monkey(Weapon chosenWeapon, int _xpos, int _ypos, int _armor){
		myWeapon = chosenWeapon;
		armor = _armor;
		xpos = _xpos;
		ypos = _ypos;
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
	
	public void hurt(int dmg){
		health -= dmg;
	}
	
	public void Attack(){
		
	}
}
