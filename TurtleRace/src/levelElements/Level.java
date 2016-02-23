package levelElements;

import java.util.ArrayList;

public class Level {

	ArrayList<Challenges> level = new ArrayList<Challenges>();
	
	public Level(){
		level.add(new StartSection());
	}
	
	public void addChallenge(Challenges e){
		level.add(e);
	}
	
}
