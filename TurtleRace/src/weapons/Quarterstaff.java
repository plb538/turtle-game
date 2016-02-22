package weapons;

public class Quarterstaff implements Weapon{
	
	//Damage per hit
	int damage = 26;
	
	//Range to hit another character
	int range = 2 * common.Character.width;
	
	//Seconds between attacks
	int attackspeed = 3;
	
	//Vertical attack height for hit detection
	int attackheight = common.Character.height;
	
	//Weapon name
	String name = "Quarterstaff";
}
