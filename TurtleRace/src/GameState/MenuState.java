package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.GamePanel;
import TileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private String[] options = {"Start", "Options", "Quit"};
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		
		try{
			bg = new Background("/Backgrounds/testBG.png", 1);
			bg.setVector(0, 0); //determines if background is moving
			
			titleColor = new Color(255, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 56);
			font = new Font("Arial", Font.PLAIN, 36);
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
		g.drawString(title, 400, 200);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == curOption){
				g.setColor(Color.BLACK);
			}
			else{
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 600, 320 + i*30);
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
				gsm.setState(GameStateManager.LEVELSTATE);
				break;
			case 1 : //help
				gsm.setState(GameStateManager.OPTIONSTATE);
				break;
			case 2 : 
				System.exit(0);
				break;
		}
	}
	
}
