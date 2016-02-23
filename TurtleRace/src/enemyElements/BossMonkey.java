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
	
	public void Jump(){
		if(this.ypos == 720*2/3){
			this.ypos += this.jumpHeight;
		}
	}
	
	public void Forwards(){
		this.xpos += 25;
	}
	
	public void Backwards(){
		
	}
}
