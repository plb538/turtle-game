package enemyElements;

public class Monkey implements Enemy{
	
	int health = 100;
	int armor;
	int xpos;
	int ypos;
	
	public Monkey(int _armor, int _xpos, int _ypos){
		armor = _armor;
		xpos = _xpos;
		ypos = _ypos;
	}

}
