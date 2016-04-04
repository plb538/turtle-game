package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class OptionState extends GameState{
	
	private Background bg;
	
	private String[] options = {"Apply Changes", "Return", 
			"Warp 1","Warp 2","Warp 3"};
	
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public OptionState(GameStateManager gsm){
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
		g.drawString(title, GamePanel.WIDTH/4, 100);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == curOption){
				g.setColor(Color.CYAN);
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 520, 280 + i*30);
		}
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP){
			curOption--;
			if(curOption == -1){
				curOption = options.length -1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			curOption++;
			if(curOption == options.length){
				curOption = 0;
			}
		}
	}

	@Override
	public void keyReleased(int k){}
	
	
	private void select(){
		switch(curOption){
			case 0 : 
				//Apply changes
				break;
			case 1 : //Return
				gsm.setState(GameStateManager.MENUSTATE);
				break;
			case 2 : gsm.setState(GameStateManager.ENEMYLEVEL1);
				break;
			case 3 : gsm.setState(GameStateManager.ENEMYLEVEL2);
				break;
			case 4 : gsm.setState(GameStateManager.ENEMYLEVEL3);
			break;
			
		}
	}
	
}

