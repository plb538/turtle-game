package levelElements;

import java.util.ArrayList;

import enemyElements.Monkey;
import weapons.PunchingFist;

public class Enemies extends Challenges{

	int startDistance;
	
	ArrayList<enemyElements.Monkey> myEnemies = new ArrayList<enemyElements.Monkey>();
	
	public Enemies(int numberMonkeys, int startingDistance){
		for(int i = 0; i < numberMonkeys; i++){
			myEnemies.add(new Monkey(new PunchingFist(), startingDistance+128*i, 1280/3, 0));
		}
	}
	
}
