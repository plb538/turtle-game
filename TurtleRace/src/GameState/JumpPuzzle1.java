package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.Player;
import Entity.Portal;
import Main.Game;
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
		tileMap.loadMap("/Maps/JumpPuzzle1.map");
		tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
		Game.p1.setObjectOnTileMap(tileMap);
		Game.p1.setPosition(100, tileMap.getHeight() - 200);
		
		//monkey = new MonkeyEnemy(tileMap);
		//monkey.setPosition(600, 450);
		
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth() - 1.5*tileMap.getTileSize(), 100);
		
		healthBar = new DrawnHealth(Game.p1);
		progress = new DrawnProgress(Game.p1, tileMap);
	}

	@Override
	public void update(){	
		Game.p1.update();
		//monkey.update();
		portal.update();
		
		//allows map to move
		tileMap.setPosition(GamePanel.WIDTH / 2 - Game.p1.getx(), GamePanel.HEIGHT / 2 - Game.p1.gety());

		if(Game.p1.getx() == portal.getx() && Game.p1.gety() == portal.gety() + 15){
			gsm.setState(gsm.ENDSTATE);
		}
		if(Game.p1.notOnScreen()){
			Game.p1.setPosition(100, tileMap.getHeight() - 100);
			Game.p1.takeDamage(20);
		}
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw tile map
		tileMap.draw(g);
		
		//draw player
		Game.p1.draw(g);
		
		//draw enemy
		//monkey.draw(g);
		
		//draw portal
		portal.draw(g, tileMap);
		
		//draw health
		healthBar.draw(g, Game.p1);
		
		//draw progress
		progress.draw(g, Game.p1, tileMap);
		
	}

	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(4);
		}
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(true);
		//if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN){Game.p1.setDown(true); Game.p1.takeDamage(20);} //Remember to remove this for obvious reasons
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(true);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(true);
	}

	@Override
	public void keyReleased(int k){
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(false);
		//if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) Game.p1.setDown(false);
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(false);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(false);
	}
	
}
