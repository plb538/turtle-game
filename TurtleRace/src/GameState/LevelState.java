package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;

public class LevelState extends GameState{

	private TileMap tileMap;
	private Background bg;
	private Player player;
	
	public LevelState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init(){
		tileMap = new TileMap(30); //value passed to constructor will depend on the tile map
		tileMap.loadTiles("/TileSets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/default-background.png", 1);	
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
	}

	@Override
	public void update(){	
		player.update();
	
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw tile map
		tileMap.draw(g);
		//draw player
		player.draw(g);
		
	}

	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(4);
		}
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_Q) player.setGliding(true);
		if(k == KeyEvent.VK_E) player.setScratching();
		if(k == KeyEvent.VK_R) player.setFiring();
	}

	@Override
	public void keyReleased(int k){
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_Q) player.setGliding(false);
	}
	
}
