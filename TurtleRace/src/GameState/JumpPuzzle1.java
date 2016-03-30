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
		
		monkeys = new ArrayList<MonkeyEnemy>();
		//monkey = new MonkeyEnemy(tileMap);
		//monkey.setPosition(600, 450);
		
		portal = new Portal(tileMap);
		portal.setPosition(tileMap.getWidth() - 1.5*tileMap.getTileSize(), 100);
		
		healthBar = new DrawnHealth(Game.p1);
		progress = new DrawnProgress(Game.p1, tileMap);
	}
}
