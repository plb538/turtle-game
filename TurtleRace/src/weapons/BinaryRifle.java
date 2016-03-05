package weapons;

public class BinaryRifle implements Weapon{

	//Damage per hit
	static final int damage = 15;
	
	//Range to hit another character
	static final int range = 20*64;
	
	//Seconds between attacks
	static final int attackspeed = 5;
	
	//Vertical attack height for hit detection
	static final int attackheight = (int)(0.1*64);
	
	//Weapon name
	static final String name = "Binary Rifle";
	
}
