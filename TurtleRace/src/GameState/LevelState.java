package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class LevelState extends GameState{

	//private TileMap tileMap;
	private Background bg;
	
	public LevelState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init(){
		//tileMap = new TileMap(30); //value passed to constructor will depend on the tile map
		//tileMap.loadTiles("/TileSets/grasstileset.gif");
		//tileMap.loadMap("/Maps/level1-1.map");
		//tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
	}

	@Override
	public void update(){		
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw tile map
		//tileMap.draw(g);
		
	}

	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(4);
		}
	}

	@Override
	public void keyReleased(int k){}
	
}
