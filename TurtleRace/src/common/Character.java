package common;

import weapons.Weapon;

public interface Character {
	
	
	int health = 100;
	int armor = 0;
	
	//Distances measured in pixels
	int xpos = 0;
	int ypos = 0;
	
	//Weapon
	Weapon myWeapon = null;
	
	//characters 128 pixels wide by 256 pixels tall
	static final int width = 128;
	static final int height = 256;
	
	//Velocity in pixels per second
	int velocity = 0;
	
	//Jump height in pixels
	int jumpHeight = 256;
	
	public void Jump();
	public void Forwards();
	public void Backwards();
}
