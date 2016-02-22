package weapons;

public class ThrowingBananas implements Weapon{
	
	//Damage per hit
	int damage = 15;
	
	//Range to hit another character
	int range = 8 * common.Character.width;
	
	//Seconds between attacks
	int attackspeed = 3;
	
	//Weapon name
	String name = "Bananas";
}
