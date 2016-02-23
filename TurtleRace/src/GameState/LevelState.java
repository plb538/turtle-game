package GameState;

import java.awt.Graphics2D;

import TileMap.TileMap;

public class LevelState extends GameState{

	private TileMap tileMap;
	
	public LevelState(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	@Override
	public void init(){
		tileMap = new TileMap(30);
		tileMap.loadTiles(path);
		tileMap.loadMap(path);
	}

	@Override
	public void update(){}

	@Override
	public void draw(Graphics2D g){}

	@Override
	public void keyPressed(int k){}

	@Override
	public void keyReleased(int k){}
	
}
