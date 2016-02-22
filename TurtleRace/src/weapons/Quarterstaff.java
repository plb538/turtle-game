package weapons;

public class Quarterstaff implements Weapon{
	
	//Damage per hit
	static final int damage = 26;
	
	//Range to hit another character
	static final int range = 2 * common.Character.width;
	
	//Seconds between attacks
	static final int attackspeed = 3;
	
	//Vertical attack height for hit detection
	static final int attackheight = common.Character.height;
	
	//Weapon name
	static final String name = "Quarterstaff";
}
