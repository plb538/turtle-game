package weapons;

public class BinaryRifle implements Weapon{

	//Damage per hit
	int damage = 15;
	
	//Range to hit another character
	int range = 20*common.Character.width;
	
	//Seconds between attacks
	int attackspeed = 5;
	
	//Vertical attack height for hit detection
	int attackheight = (int)(0.1*common.Character.height);
	
	//Weapon name
	String name = "Binary Rifle";
	
}
