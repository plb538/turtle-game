package enemyElements;

public class BossMonkey implements Enemy{

	int health = 500;
	int armor;
	int xpos;
	int ypos;
	
	public BossMonkey(int _armor, int _xpos, int _ypos){
		armor = _armor;
		xpos = _xpos;
		ypos = _ypos;
	}
	
}
