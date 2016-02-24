package GameState;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;
import graphicalElements.*;

public class ChallengeState extends GameState{
	
	private Background bg;
	
	private String[] options = {};
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private levelElements.Challenges currentChallenge = null;
	private int stateNumber = 3;
	private DrawnCharacter charImage = null;
	
	public ChallengeState(GameStateManager gsm, levelElements.Challenges currChallenge, int _stateNumber){
		this.gsm = gsm;
		this.currentChallenge = currChallenge;
		stateNumber = _stateNumber;
		try{
			bg = new Background("/Backgrounds/default-background.png", 1);
			bg.setVector(0, 0); //determines if background is moving
			
			titleColor = new Color(255, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 56);
			font = new Font("Arial", Font.PLAIN, 26);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		charImage = new DrawnCharacter("/sprites/temp-turtle.PNG", gsm.player1);
				
	}

	@Override
	public void init(){
		
		
	}

	@Override
	public void update(){
		bg.update();
		if(currentChallenge.isFinished()){
			gsm.setState(stateNumber);
		}
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);		
		g.drawImage(charImage.myImage, null, gsm.player1.getX(), gsm.player1.getY());
		
		
		
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_W){
			gsm.player1.Jump();
		}
		if(k == KeyEvent.VK_D){
			gsm.player1.Forwards();
		}
		if(k == KeyEvent.VK_A){
			gsm.player1.Backwards();
		}
		if(k == KeyEvent.VK_SPACE){
			gsm.player1.Attack();
		}
	}

	@Override
	public void keyReleased(int k){}
	
	
}