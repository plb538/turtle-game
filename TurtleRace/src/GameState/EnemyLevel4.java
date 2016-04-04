package GameState;

import java.util.ArrayList;

import Entity.MonkeyEnemy;

import Entity.Portal;
import Main.Game;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;


public class EnemyLevel4 extends GameState{
		
	private MonkeyEnemy m1;
	private MonkeyEnemy m2;
	private MonkeyEnemy m3;
	private MonkeyEnemy m4;
	private MonkeyEnemy m5;
	private MonkeyEnemy m6;
	private MonkeyEnemy m7;
	private MonkeyEnemy m8;
	private MonkeyEnemy m9;
	private MonkeyEnemy m10;
	
	public EnemyLevel4(GameStateManager gsm){
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
		tileMap.loadMap("/Maps/Enemy4.map");
		
		tileMap.setPosition(0, 0);
		
		//load background from resource folder
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
		//creates player and sets player's tilemap to LevelState's tilemap
		Game.p1.setObjectOnTileMap(tileMap);
		Game.p1.setPosition(100, tileMap.getHeight() - 100);
		
		monkeys = new ArrayList<MonkeyEnemy>();
		
		m1 = new MonkeyEnemy(tileMap);
		m2 = new MonkeyEnemy(tileMap);
		m3 = new MonkeyEnemy(tileMap);
		m4 = new MonkeyEnemy(tileMap);
		m5 = new MonkeyEnemy(tileMap);
		m6 = new MonkeyEnemy(tileMap);
		m7 = new MonkeyEnemy(tileMap);
		m8 = new MonkeyEnemy(tileMap);
		m9 = new MonkeyEnemy(tileMap);
		m10 = new MonkeyEnemy(tileMap);
		
		monkeys.add(m1);
		monkeys.add(m2);
		monkeys.add(m3);
		monkeys.add(m4);
		monkeys.add(m5);
		monkeys.add(m6);
		monkeys.add(m7);
		monkeys.add(m8);
		monkeys.add(m9);
		monkeys.add(m10);
		
		m1.setPosition(600, 450);
		m2.setPosition(1200, 150);
		m3.setPosition(300, 450);
		m4.setPosition(1500, 150);
		m5.setPosition(800, 450);
		m6.setPosition(500, 150);
		m7.setPosition(700, 450);
		m8.setPosition(1000, 150);
		m9.setPosition(900, 450);
		m10.setPosition(1100, 150);
		
        //creates portal 
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth()-96*2, 450);
		
		//creates player one's health bar and progress bar
		healthBar = new DrawnHealth(Game.p1);
		progress = new DrawnProgress(Game.p1, tileMap);
		
		//Create player 2 objects
		Game.p2.setObjectOnTileMap(tileMap);
		Game.p2.setPosition(100, tileMap.getHeight()-100);
			
		healthBar2 = new DrawnHealth(Game.p2);
		progress2 = new DrawnProgress(Game.p2, tileMap);
		//pt = new PlayerTip("Press Up to jump and W to attack", 180, 250);
	}
}

