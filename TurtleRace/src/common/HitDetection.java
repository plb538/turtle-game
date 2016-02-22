package common;
import weapons.Weapon;

public class HitDetection {

	//To determine if something is a hit:
	
	/*	1. Get y-axis of weapon during attack
			For players this will typically be at 128 pixels, or half their height
		2. Check for other characters in the range of the weapon
		3. If any character is within range and within the vertical attack distance of a particular weapon, do damage
			For example, the Quarterstaff has a vertical attack height of 1 character (256 pixels)
	*/
	
	public boolean validHit(Weapon attackingWeapon, Character attacker){
		
		Character potentialHit = null;
		
		for(int i = attacker.xpos; i < (attacker.xpos + attackingWeapon.range); i++){
			//Check each distance for presence of character
			
			potentialHit = checkPresence(i);
			
			if(potentialHit != null){
				
			}
			
			//If there is a character,
		}
		
		return true;
		
	}
	
	private Character checkPresence(int xpos){
		
		return null;
	}
	
}
