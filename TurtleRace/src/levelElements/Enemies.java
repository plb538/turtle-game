package levelElements;

import java.util.ArrayList;

import enemyElements.Monkey;
import weapons.PunchingFist;

public class Enemies extends Challenges{

	int startDistance;
	
	ArrayList<enemyElements.Monkey> myEnemies = new ArrayList<enemyElements.Monkey>();
	
	public Enemies(int numberMonkeys){
		for(int i = 0; i < numberMonkeys; i++){
			myEnemies.add(new Monkey(new PunchingFist(), 100*i, 720*(2/3), 0));
		}
	}
	
	@Override
	public boolean isFinished(){
		
		for(int i = 0; i < myEnemies.size(); i ++){
			if(myEnemies.get(i).health != 0){
				return false;
			}
		}
		return true;
		
	}
	
	@Override
	public double progress(){
		
		double progress = 0;
		double total = 0;
		
		for(int i = 0; i < myEnemies.size(); i++){
			progress += myEnemies.get(i).health;
			total += myEnemies.get(i).maxHealth;
		}
		
		//Returns progress as a percent
		return progress/total;
		
	}
	
}
