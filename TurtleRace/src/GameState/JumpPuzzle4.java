package GameState;

import java.util.ArrayList;

import Entity.MonkeyEnemy;
import Entity.Portal;
import Main.Game;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;

//basically the same as LevelState but with different map
public class JumpPuzzle4 extends GameState{
	
	private MonkeyEnemy m1;
	private MonkeyEnemy m2;
	private MonkeyEnemy m3;
	private MonkeyEnemy m4;
	
	public JumpPuzzle4(GameStateManager gsm){
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
		tileMap.loadMap("/Maps/JumpPuzzle4.map");
		tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/default-background.png", 1);	
		
		Game.p1.setObjectOnTileMap(tileMap);
		Game.p1.setPosition(100, tileMap.getHeight() - 300);
		
		monkeys = new ArrayList<MonkeyEnemy>();
		m1 = new MonkeyEnemy(tileMap);
		m2 = new MonkeyEnemy(tileMap);
		m3 = new MonkeyEnemy(tileMap);
		m4 = new MonkeyEnemy(tileMap);
		monkeys.add(m1);
		monkeys.add(m2);
		monkeys.add(m3);
		monkeys.add(m4);
		m1.setPosition(200, 600);
		m2.setPosition(1500, 350);
		m3.setPosition(700, 500);
		m4.setPosition(900, 500);
		
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth() - 1.5*tileMap.getTileSize(), 100);
		
		healthBar = new DrawnHealth(Game.p1);
		progress = new DrawnProgress(Game.p1, tileMap);
	}
}
