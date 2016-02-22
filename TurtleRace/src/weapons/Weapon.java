package weapons;

public interface Weapon {
		
	//Damage per hit
	static final int damage = 5000;
	
	//Range to hit another character
	static final int range = 1000000;
	
	//Seconds between attacks
	static final int attackspeed = 1;
	
	//Vertical attack height for hit detection
	static final int attackheight = 1000000;
	
	//Weapon name
	static final String name = "dev-weapon";
	

}
