package weapons;

public interface Weapon {
		
	//Damage per hit
	int damage = 5000;
	
	//Range to hit another character
	int range = 1000000;
	
	//Seconds between attacks
	int attackspeed = 1;
	
	//Vertical attack height for hit detection
	int attackheight = 1000000;
	
	//Weapon name
	String name = "dev-weapon";
	

}
