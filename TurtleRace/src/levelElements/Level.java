package levelElements;

import java.util.ArrayList;

public class Level {

	ArrayList<Challenges> level = new ArrayList<Challenges>();
	
	public Level(){
		level.add(new StartSection());
	}
	
	public ArrayList<common.Character> getCharacters(int startingPos, int range){
		
		
		return null;
		
	}
	
	public void addChallenge(Challenges e){
		level.add(e);
	}
	
}
