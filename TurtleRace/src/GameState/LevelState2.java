package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

public class LevelState2 extends GameState{
	
	public LevelState2(GameStateManager gsm){
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
		
		Game.p1.setObjectOnTileMap(tileMap);
		Game.p1.setPosition(100, tileMap.getHeight() - 100);
		
		Game.p2.setObjectOnTileMap(tileMap);
		Game.p2.setPosition(100, tileMap.getHeight() - 100);
		
		monkeys = new ArrayList<MonkeyEnemy>();
		//monkey = new MonkeyEnemy(tileMap);
		//monkey.setPosition(600, 450);
		
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth() - 96, 100);
		
		healthBar = new DrawnHealth(Game.p1);
		progress = new DrawnProgress(Game.p1, tileMap);
	}
}
