package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.Player;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;

public class LevelState extends GameState{

	private TileMap tileMap;
	private Background bg;
	private Player player;
	
	private DrawnHealth healthBar;
	private DrawnProgress progress;
	
	public LevelState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init(){
		//tileMap = new TileMap(64); //value passed to constructor will depend on the tile map
		tileMap = new TileMap(64);
		
		//ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<String> list = new ArrayList<String>();
		//list.add(3);
		//list.add(1);
		//list.add(2);
		
		list.add("CrapSky");
		list.add("CrapBlock");
		list.add("CrapDirtBlock");
		
		tileMap.loadTiles("/TileSets/Terrain", list);
		tileMap.loadMap("/Maps/testMap2.map");
		tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
		player = new Player(tileMap);
		player.setPosition(tileMap.getTileSize(), tileMap.getTileSize());
		
		healthBar = new DrawnHealth(player);
		progress = new DrawnProgress(player, tileMap);
	}

	@Override
	public void update(){	
		player.update();
		//allows map to move
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.WIDTH / 2 - player.gety());
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw tile map
		tileMap.draw(g);
		
		//draw player
		player.draw(g);
		
		//draw health
		healthBar.draw(g, player);
		
		//draw progress
		progress.draw(g, player, tileMap);
		
	}

	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(4);
		}
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		//if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN){player.setDown(true); player.takeDamage();} //Remember to remove this for obvious reasons
		if(k == KeyEvent.VK_UP) player.setJumping(true);
		if(k == KeyEvent.VK_Q) player.setGliding(true);
		if(k == KeyEvent.VK_E) player.setScratching();
		if(k == KeyEvent.VK_R) player.setFiring();
	}

	@Override
	public void keyReleased(int k){
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		//if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_UP) player.setJumping(false);
		if(k == KeyEvent.VK_Q) player.setGliding(false);
	}
	
}
