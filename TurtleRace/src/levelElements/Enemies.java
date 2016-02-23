package levelElements;

import java.util.ArrayList;

import enemyElements.Monkey;
import weapons.PunchingFist;

public class Enemies extends Challenges{

	int startDistance;
	private boolean finished = false;
	
	ArrayList<enemyElements.Monkey> myEnemies = new ArrayList<enemyElements.Monkey>();
	
	public Enemies(int numberMonkeys, int startingDistance){
		for(int i = 0; i < numberMonkeys; i++){
			myEnemies.add(new Monkey(new PunchingFist(), startingDistance+1280*(i+1)/numberMonkeys, 720*(2/3), 0));
		}
	}
	
	@Override
	public boolean isFinished(){
		return finished;
	}
	
}
