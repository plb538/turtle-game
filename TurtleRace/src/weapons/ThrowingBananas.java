package weapons;

public class ThrowingBananas implements Weapon{
	
	//Damage per hit
	static final int damage = 15;
	
	//Range to hit another character
	static final int range = 8 * common.Character.width;
	
	//Seconds between attacks
	static final int attackspeed = 3;
	
	//Weapon name
	static final String name = "Bananas";
}
