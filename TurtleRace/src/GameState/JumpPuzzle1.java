package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.MonkeyEnemy;
import Entity.Player;
import Entity.Portal;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;

public class JumpPuzzle1 extends GameState{

	//map stuff
	private TileMap tileMap;
	private Background bg;
	
	//Entity stuff
	private Player player;
	//private MonkeyEnemy monkey;
	private Portal portal;
	
	//graphical element stuff
	private DrawnHealth healthBar;
	private DrawnProgress progress;
	
	public JumpPuzzle1(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init(){
		tileMap = new TileMap(64);
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("CrapSky");
		list.add("CrapBlock");
		list.add("CrapDirtBlock");
		
		tileMap.loadTiles("/TileSets/Terrain", list);
		tileMap.loadMap("/Maps/levelTwoMap.map");
		tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
		player = new Player(tileMap);
		player.setPosition(100, tileMap.getHeight() - 100);
		
		//monkey = new MonkeyEnemy(tileMap);
		//monkey.setPosition(600, 450);
		
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth() - 96, 100);
		
		healthBar = new DrawnHealth(player);
		progress = new DrawnProgress(player, tileMap);
	}

	@Override
	public void update(){	
		player.update();
		//monkey.update();
		portal.update();
		
		//allows map to move
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

		if(player.getx() == portal.getx() && player.gety() == portal.gety() + 15){
			gsm.setState(gsm.ENDSTATE);
		}
		if(player.notOnScreen()){
			player.setPosition(100, tileMap.getHeight() - 100);
			player.takeDamage();
		}
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw tile map
		tileMap.draw(g);
		
		//draw player
		player.draw(g);
		
		//draw enemy
		//monkey.draw(g);
		
		//draw portal
		portal.draw(g, tileMap);
		
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
