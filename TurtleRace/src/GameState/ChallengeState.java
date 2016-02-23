package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class ChallengeState extends GameState{
	
	private Background bg;
	
	private String[] options = {};
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private levelElements.Challenges currentChallenge = null;
	
	public ChallengeState(GameStateManager gsm, levelElements.Challenges currChallenge){
		this.gsm = gsm;
		this.currentChallenge = currChallenge;
		
		try{
			bg = new Background("/Backgrounds/testBG.png", 1);
			bg.setVector(0, 0); //determines if background is moving
			
			titleColor = new Color(255, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 56);
			font = new Font("Arial", Font.PLAIN, 26);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void init(){}

	@Override
	public void update(){
		bg.update();
		if(currentChallenge.isFinished()){
			//Move to next challenge
		}
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(title, GamePanel.WIDTH/4, 100);
		
		//draw menu options
		g.setFont(font);
		
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		
	}

	@Override
	public void keyReleased(int k){}
	
	
}