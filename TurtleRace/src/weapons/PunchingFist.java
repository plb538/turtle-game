package weapons;

public class PunchingFist implements Weapon{

	//Damage per hit
	static final int damage = 34;
	
	//Range to hit another character
	static final int range = 64;
	
	//Seconds between attacks
	static final int attackspeed = 2;
	
	//Vertical attack height for hit detection
	static final int attackheight = (int)(0.5*64);
	
	//Weapon name
	static final String name = "Fist";
	
}
