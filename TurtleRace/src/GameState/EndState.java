package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class EndState extends GameState{
	
	private Background bg;
	
	private String[] options = {"Apply Changes", "Return"};
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public EndState(GameStateManager gsm){
		this.gsm = gsm;
		
		try{
			//bg = new Background("/Backgrounds/testBG.png", 1);
			bg = new Background("/Backgrounds/gears.png", 1);
			bg.setVector(0, 0); //determines if background is moving
			
			titleColor = new Color(255, 255, 255);
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
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Thanks for Playing!", GamePanel.WIDTH/4, 100);
		
		
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		System.exit(0);
	}

	@Override
	public void keyReleased(int k){}

}

