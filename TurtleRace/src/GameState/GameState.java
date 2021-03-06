package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import Entity.MonkeyEnemy;
import Entity.Player;
import Entity.Portal;
import Main.Game;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;
import graphicalElements.DrawnHealth;
import graphicalElements.DrawnProgress;
import graphicalElements.PlayerTip;
import networking.InformationPacket;

public abstract class GameState{
	
	//shared gsm by the game states
	protected GameStateManager gsm;
	
	//map info
	protected TileMap tileMap;
	protected Background bg;
		
	//Entity objects
	public static ArrayList<MonkeyEnemy> monkeys;
	protected Portal portal;
	
	//graphical element objects
	protected DrawnHealth healthBar;
	protected DrawnProgress progress;
	protected DrawnHealth healthBar2;
	protected DrawnProgress progress2;
	protected PlayerTip pt;

	public abstract void init();
	
	/*
	 * update()
	 * -Main update loop
	 * Updates the following:
	 * 	-Updates the player
	 *  -Sets flag for monkeys if multiplayer
	 *  -Checks if the portal should be activated
	 *  -Updates the portal
	 *  -Updates the map's scrolling if it should be moved
	 *  -Checks if any player or monkey has been hit
	 *  -Check if the player has fallen off the screen (Not possible on all stages)
	 *  -Check if the player has entered a portal
	 */
	
	public void update(){
		Game.p1.update();
		//Game.p2.update();
		
		if(gsm.modeMultiplayer && !(gsm.isHost)){
			for(MonkeyEnemy mes: monkeys){
				mes.setMultiFlag();
			}
		}
		
		if(!(portal.checkIfActivated())){
		
			boolean allDead = true;
			if(monkeys.size() > 0){
				for(MonkeyEnemy mes : monkeys){
					mes.update();
					if(!(mes.checkIfDead())){
						allDead = false;
					}
				}
			}

			if(allDead){
				portal.activate();
			}
		}
		portal.update();
		
		//allows map to move
		tileMap.setPosition(GamePanel.WIDTH / 2 - Game.p1.getx(), GamePanel.HEIGHT / 2 - Game.p1.gety());
        
		checkIfHit(Game.p1, monkeys);
		checkIfHit(Game.p2, monkeys);
		
		if(portal.checkIfActivated()){
			if(((Game.p1.getx() >= (portal.getx() - 20)) && (Game.p1.getx() <= (portal.getx() + 20))) && 
			((Game.p1.gety() >= (portal.gety() - 5)) && (Game.p1.gety() <= (portal.gety() + 35)))){
			//System.out.println("InPotal");
			gsm.setState(gsm.getState() + 1);
			}
		}
	
		//if player falls of map
		if(Game.p1.notOnScreen()){
			Game.p1.setPosition(100, tileMap.getHeight() - 100);
		}
	}
	
	/*
	 * updateMonkeyPosP2(InformationPacket test)
	 * 	-Updates the non-host player's monkeys in multiplayer mode
	 */
	public void updateMonkeyPosP2(InformationPacket test){
		if(monkeys.size() > 0){
			//for(MonkeyEnemy mes : monkeys){
			for(int i = 0; i < monkeys.size(); i++){
				monkeys.get(i).updateP2(test.getMonkeyX(i), test.getMonkeyY(i), test.getMonkeyHP(i));	
			}
		}	
	}
	
	/*
	 * draw(Graphics 2D g)
	 * 	-Draws successive graphical elements to an image to be drawn to the screen
	 * 	-Draws in following ordeR:
	 * 		-Background
	 * 		-Map
	 * 		-Player
	 * 		-Enemies
	 * 		-Portal
	 * 		-Health Bar
	 * 		-Progress Bar
	 * 		-Player tip (If applicible)
	 * 		-Player 2 (If applicible)
	 */
	
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
				
		//draw tile map
		tileMap.draw(g);
				
		//draw player
		Game.p1.draw(g);
				
		//draw enemy
		if(monkeys.size() > 0){
			for(MonkeyEnemy mes : monkeys){
			mes.draw(g);
			}
		}		
		//draw portal
		portal.draw(g, tileMap);
				
		//draw health
		healthBar.draw(g, Game.p1);
				
		//draw progress
		//progress.draw(g, Game.p1, tileMap);
		progress.draw(g, monkeys);
		
		
		if(pt != null){
			pt.draw(g);
		}
				
		if(gsm.modeMultiplayer){
			//System.out.println("Game is in multiplayer mode. Mystate=" + gsm.getState() + ". OtherState = " + Game.p2.getState());
			if(Game.p2.getState() == gsm.getState()){
				//System.out.println("Drawing P2");
				Game.p2.draw(g);
			}
		}
	}
	
	/*
	 * keyPressed(int k)
	 * 	-Handles keyboard input appropriately
	 */
	
	public void keyPressed(int k){

		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(true);
		if(k == KeyEvent.VK_UP){
			Game.p1.setJumping(true);
			gsm.overlayAudio("/Audio/player/jump-loud.wav");
		}
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(true);
		if(k == KeyEvent.VK_W){
			Game.p1.setAttacking(true);
			gsm.overlayAudio("/Audio/player/swing-stick.wav");
		}
	}
	
	/*
	 * keyReleased(int k)
	 * 	-Handles released keyboard input appropriately
	 */
	
	public void keyReleased(int k){
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(false);
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(false);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(false);
	}
	
	/*
	 * checkIfHit()
	 * -Hit detection
	 * -Also plays audio if hits occur
	 */
	
	public void checkIfHit(Player p, ArrayList<MonkeyEnemy> mes){

		try{
		for(MonkeyEnemy me : mes){
			if(!(me.checkIfDead())){
				if((p.getx() + 60 >= me.getx() && p.getx() <= me.getx() + me.getWidth()) && p.checkIfAttacking() && ((p.gety() + p.getCHeight() <= me.gety() + me.getCHeight()) && p.gety() + p.getCHeight() >= me.gety())){
					me.takeDamage(p.weapon.damage);
					if(p.weapon.getAnimation().getFrame() == 2){
						me.setVector(4, 0);
						gsm.overlayAudio("/Audio/monkey/flinch.wav");
					}
				}
				if((p.getx() - 30 <= me.getx() + me.getCWidth() && p.getx() >= me.getx()) && p.checkIfAttacking() && ((p.gety() + p.getCHeight() <= me.gety() + me.getCHeight()) && p.gety() + p.getCHeight() >= me.gety())){
					me.takeDamage(p.weapon.damage);
					if(p.weapon.getAnimation().getFrame() == 2){
						me.setVector(-4, 0);
						gsm.overlayAudio("/Audio/monkey/flinch.wav");
					}
				}
				if((p.getx() + p.getCWidth() - 4 >=  me.getx()) && (p.getx() + p.getCWidth() -4 <= me.getx() + me.getCWidth()) && (p.gety() + p.getCHeight() >= me.gety()) && (p.gety() + p.getCHeight() <= me.gety() + me.getCHeight())){
					p.takeDamage(20);
					p.setVector(-4, 0);
					p.checkResetConditions();
					gsm.overlayAudio("/Audio/player/flinch.wav");
				}
				if((p.getx() >=  me.getx()) && (p.getx() <= me.getx() + me.getCWidth()) && (p.gety() + p.getCHeight() >= me.gety()) && (p.gety() + p.getCHeight() <= me.gety() + me.getCHeight())){
					p.takeDamage(20);
					p.setVector(4, 0);
					p.checkResetConditions();
					gsm.overlayAudio("/Audio/player/flinch.wav");
				}
			}
		}
		}catch(Throwable e2){
			e2.printStackTrace();
		}
	}
}
