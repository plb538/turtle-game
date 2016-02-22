package weapons;

public class BinaryRifle implements Weapon{

	//Damage per hit
	static final int damage = 15;
	
	//Range to hit another character
	static final int range = 20*common.Character.width;
	
	//Seconds between attacks
	static final int attackspeed = 5;
	
	//Vertical attack height for hit detection
	static final int attackheight = (int)(0.1*common.Character.height);
	
	//Weapon name
	static final String name = "Binary Rifle";
	
}
