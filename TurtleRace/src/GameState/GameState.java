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
import graphicalElements.PlayerTip;
import networking.InformationPacket;

public abstract class GameState{
	
	//shared gsm by the game states
	protected GameStateManager gsm;
	
	//map info
	protected TileMap tileMap;
	protected Background bg;
		
	//Entity objects
	protected ArrayList<MonkeyEnemy> monkeys;
	protected Portal portal;
	
	//graphical element objects
	protected DrawnHealth healthBar;
	protected DrawnProgress progress;
		
	protected DrawnHealth healthBar2;
	protected DrawnProgress progress2;
	protected PlayerTip pt;

	public abstract void init();
	
	public void update(){
		try{
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch(UnknownHostException e3){
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		Game.p1.update();
		
		if(monkeys.size() > 0){
			for(MonkeyEnemy mes : monkeys){
			mes.update();
			}
		}
		
		portal.update();
		
		//allows map to move
		tileMap.setPosition(GamePanel.WIDTH / 2 - Game.p1.getx(), GamePanel.HEIGHT / 2 - Game.p1.gety());
        
		checkIfHit(Game.p1, monkeys);
		checkIfHit(Game.p2, monkeys);
		
		if(((Game.p1.getx() >= (portal.getx() - 20)) && (Game.p1.getx() <= (portal.getx() + 20))) && 
			((Game.p1.gety() >= (portal.gety() - 5)) && (Game.p1.gety() <= (portal.gety() + 35)))){
			//System.out.println("InPotal");
			gsm.setState(gsm.getState() + 1);
		}
		
		//if player falls of map
		if(Game.p1.notOnScreen()){
			Game.p1.setPosition(100, tileMap.getHeight() - 100);
		}
		
		if(gsm.modeMultiplayer){
			gsm.updateP2Thread();
		}
		
		/*
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
		*/
		
	}
	
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
		progress.draw(g, Game.p1, tileMap);
		
		if(pt != null){
			pt.draw(g);
		}
				
		if(gsm.modeMultiplayer){
					
			//if(Game.p2.state == gsm.getState()){
				Game.p2.draw(g);
			//}
		}
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(gsm.getState() + 1);
		}
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(true);
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(true);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(true);
		if(k == KeyEvent.VK_W){
			Game.p1.setAttacking(true);
			gsm.overlayAudio("/Audio/player/swing-stick.wav");
		}
	}
	
	public void keyReleased(int k){
		if(k == KeyEvent.VK_LEFT) Game.p1.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) Game.p1.setRight(false);
		if(k == KeyEvent.VK_UP) Game.p1.setJumping(false);
		if(k == KeyEvent.VK_Q) Game.p1.setGliding(false);
	}
	
	public void checkIfHit(Player p, ArrayList<MonkeyEnemy> mes){

		for(MonkeyEnemy me : mes){
			if((p.getx() + 60 >= me.getx() && p.getx() <= me.getx() + me.getWidth()) && p.checkIfAttacking()){
				me.health -= p.weapon.damage;
				if(p.weapon.getAnimation().getFrame() == 2){
					me.setVector(4, 0);
					gsm.overlayAudio("/Audio/monkey/flinch.wav");
				}
			}
			if((p.getx() - 30 <= me.getx() + me.getCWidth() && p.getx() >= me.getx()) && p.checkIfAttacking()){
				me.health -= p.weapon.damage;
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
			if(me.getHealth() <= 0){
				mes.remove(me);
			}
		}
	}
}
