package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Entity.MonkeyEnemy;
import Entity.Player;
import Entity.Portal;
import Main.Game;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;
import networking.InformationPacket;

public class LevelState extends GameState{
	//map info
	private TileMap tileMap;
	private Background bg;
	
	//Entity objects
	private Player player;
	public ArrayList<MonkeyEnemy> monkeys;
	private MonkeyEnemy m1;
	private MonkeyEnemy m2;
	private Portal portal;
	
	private Player player2;
	
	//graphical element objects
	private DrawnHealth healthBar;
	private DrawnProgress progress;
	
	private DrawnHealth healthBar2;
	private DrawnProgress progress2;
	
	public LevelState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init(){
		tileMap = new TileMap(64);
		
		ArrayList<String> list = new ArrayList<String>();
		
		//adds tiles from resource folder
		list.add("CrapSky");
		list.add("CrapBlock");
		list.add("CrapDirtBlock");
		tileMap.loadTiles("/TileSets/Terrain", list);
		
		//load map from resource folder
		tileMap.loadMap("/Maps/testMap2.map");
		
		tileMap.setPosition(0, 0);
		
		//load background from resource folder
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
		//creates player and sets player's tilemap to LevelState's tilemap
		Game.p1 = new Player(tileMap);
		Game.p1.setPosition(100, tileMap.getHeight() - 100);
		
		monkeys = new ArrayList<MonkeyEnemy>();
		m1 = new MonkeyEnemy(tileMap);
		m2 = new MonkeyEnemy(tileMap);
		monkeys.add(m1);
		monkeys.add(m2);
		m1.setPosition(600, 450);
		m2.setPosition(1200, 150);
		
        //creates portal 
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth()-96*2, 450);
		
		//creates player one's health bar and progress bar
		healthBar = new DrawnHealth(Game.p1);
		progress = new DrawnProgress(Game.p1, tileMap);
		

		//Create player 2 objects
		Game.p2 = new Player(tileMap);
		Game.p2.setPosition(100, tileMap.getHeight()-100);
			
		healthBar2 = new DrawnHealth(Game.p2);
		progress2 = new DrawnProgress(Game.p2, tileMap);
			

	}
	
	@Override
	public void update(){	
		
		try{
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch(UnknownHostException e3){
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		Game.p1.update();
		
		for(MonkeyEnemy mes : monkeys){
			mes.update();
		}
		portal.update();
		
		//allows map to move
		tileMap.setPosition(GamePanel.WIDTH / 2 - Game.p1.getx(), GamePanel.HEIGHT / 2 - Game.p1.gety());
        
		checkIfHit(Game.p1, monkeys);
		checkIfHit(Game.p2, monkeys);
	
		
		System.out.println( "Player y pos: " + (Game.p1.gety() + Game.p1.getCHeight()) + " " + "Monkey y pos: " + " " + m1.gety() + " " + (m1.gety() + m1.getCHeight()));
		
        //player reaches portal will cause GameState to change to the next level
		if(Game.p1.getx() == portal.getx() && Game.p1.gety() == portal.gety() + 15){
			gsm.setState(gsm.LEVELSTATE2);
		}
		//if player falls of map
		if(Game.p1.notOnScreen()){
			Game.p1.setPosition(100, tileMap.getHeight() - 100);
			Game.p1.takeDamage(20);
		}

		if(gsm.modeMultiplayer){

			InformationPacket myPacket = new InformationPacket(Game.p1, gsm.getState());
			
			try{
				gsm.outToServer.writeObject(myPacket);
			} catch(IOException e1){
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			InformationPacket test = null;
			try{
			test = (InformationPacket)gsm.inFromServer.readObject();
			//System.out.println(test.getx() + " " + test.gety());
			
			} catch(ClassNotFoundException e){
			// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(IOException e){
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				Game.p2.updateP2(test);
			}catch(Throwable e2){
					e2.printStackTrace();
			}
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
		for(MonkeyEnemy mes : monkeys){
			mes.draw(g);
		}
		
		//draw portal
		portal.draw(g, tileMap);
		
		//draw health
		healthBar.draw(g, Game.p1);
		
		//draw progress
		progress.draw(g, Game.p1, tileMap);
		
		if(gsm.modeMultiplayer){
			
			if(Game.p2.state == gsm.getState()){
				Game.p2.draw(g);
			}
			//healthBar2.draw(g, Game.p2);
			//progress2.draw(g, Game.p2, tileMap);
		}
		
	}

	public void checkIfHit(Player p, ArrayList<MonkeyEnemy> mes){
		ArrayList<MonkeyEnemy> copy = new ArrayList<MonkeyEnemy>(mes);
		for(MonkeyEnemy me : mes){
			if((p.getx() + 54 > me.getx() && p.getx() < me.getx() + me.getWidth()) && p.checkIfAttacking()){
				me.health -= p.weapon.damage;
				//me.setPosition(me.getx() + 5, me.gety());
				if(p.weapon.getAnimation().getFrame() == 1){
					me.setVector(4, 0);
				}
			}
			if((p.getx() + p.getCWidth() - 4 >=  me.getx()) && (p.getx() + p.getCWidth() -4 <= me.getx() + me.getCWidth()) && (p.gety() + p.getCHeight() >= me.gety()) && (p.gety() + p.getCHeight() <= me.gety() + me.getCHeight())){
				p.takeDamage(10);
				p.setVector(-4, 0);
				p.checkResetConditions();
			}
			//if((p.getx() >=  me.getx()) && (p.getx() <= me.getx()) && (p.gety() + p.getCHeight() >= me.gety()) && (p.gety() + p.getCHeight() <= me.gety() + me.getCHeight())){
				//p.takeDamage(2);
				//p.setVector(4, 0);
				//p.checkResetConditions();
			//}				
			if(me.getHealth() <= 0){
				copy.remove(me);
			}
		}
		monkeys = copy;
	}
	
	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(4);
		}
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(true);
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(true);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(true);
		if(k == KeyEvent.VK_W) Game.p1.setAttacking(true);
	}

	@Override
	public void keyReleased(int k){
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(false);
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(false);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(false);
	}
	
}
