package weapons;

public class PunchingFist implements Weapon{

	//Damage per hit
	int damage = 34;
	
	//Range to hit another character
	int range = common.Character.width;
	
	//Seconds between attacks
	int attackspeed = 2;
	
	//Vertical attack height for hit detection
	int attackheight = (int)(0.5*common.Character.height);
	
	//Weapon name
	String name = "Fist";
	
}
