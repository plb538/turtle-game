package GameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.TileMap;

public class LevelState extends GameState{

	private TileMap tileMap;
	
	public LevelState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init(){
		tileMap = new TileMap(30);
		//tileMap.loadTiles("/TileSets/testTS.jpg");
		tileMap.loadMap("/Maps/testM.jpg");
		//tileMap.setPosition(0, 0);
	}

	@Override
	public void update(){}

	@Override
	public void draw(Graphics2D g){
		//clear screen
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw tile map
		tileMap.draw(g);
		
	}

	@Override
	public void keyPressed(int k){}

	@Override
	public void keyReleased(int k){}
	
}
