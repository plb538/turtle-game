package GameState;

import java.util.ArrayList;

import Entity.MonkeyEnemy;
import Entity.Player;
import Entity.Portal;
import Main.Game;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;
import graphicalElements.PlayerTip;
import networking.InformationPacket;

public class LevelState extends GameState{
		
	private MonkeyEnemy m1;
	private MonkeyEnemy m2;
	
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
		Game.p1.setObjectOnTileMap(tileMap);
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
		Game.p2.setObjectOnTileMap(tileMap);
		Game.p2.setPosition(100, tileMap.getHeight()-100);
			
		healthBar2 = new DrawnHealth(Game.p2);
		progress2 = new DrawnProgress(Game.p2, tileMap);
		pt = new PlayerTip("Press Up to jump and W to attack", 180, 250);
	}
}
