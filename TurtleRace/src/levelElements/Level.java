package levelElements;

import java.util.ArrayList;

public class Level {

	ArrayList<Challenges> level = new ArrayList<Challenges>();
	
	public Level(int numEnemyChallenges, int enemiesPerChallenge, int numPuzzleChallenges, int numObstacleChallenges){
		level.add(new StartSection());
		for(int i = 0; i < numEnemyChallenges; i++){
			level.add(new Enemies(enemiesPerChallenge, 1280*1));
		}
		for(int i = 0; i < numPuzzleChallenges; i++){
			level.add(new Puzzles());
		}
		for(int i = 0; i < numObstacleChallenges; i++){
			level.add(new Obstacles());
		}
	}
	
	public ArrayList<common.Character> getCharacters(int startingPos, int range){
		
		
		return null;
		
	}
	
}
