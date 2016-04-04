package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Main.GamePanel;
import TileMap.Background;

public class MenuState extends GameState{

	
	//this state's background
	private Background bg;
	
	private String[] options = {"Start Single Player", "Start Multiplayer", "Turtle Designer", "Options",  "Quit"};
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		//load background and set background movement speed
		try{
			bg = new Background("/Backgrounds/turtle.PNG", 1);
			bg.setVector(0, 0); //determines if background is moving
			
			titleColor = new Color(255, 255, 255);
			titleFont = new Font("Century Gothic", Font.PLAIN, 56);
			font = new Font("Arial", Font.PLAIN, 36);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(){
	    //moves background
		bg.update();
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		
		g.drawString(title, 400, 150);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == curOption){
				g.setColor(Color.CYAN);
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 500, 250 + i*30);
		}
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			gsm.overlayAudio("/Audio/menu/menu-select-loud.wav");
			select();
		}
		if(k == KeyEvent.VK_UP){
			gsm.overlayAudio("/Audio/menu/menu-move-loud.wav");
			curOption--;
			if(curOption == -1){
				curOption = options.length -1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			gsm.overlayAudio("/Audio/menu/menu-move-loud.wav");
			curOption++;
			if(curOption == options.length){
				curOption = 0;
			}
		}
		if(k == KeyEvent.VK_ESCAPE){
			gsm.updateAudio(null);
		}
	}

	@Override
	public void keyReleased(int k){}
	
	private void select(){

		switch(curOption){
			case 0 : 
				gsm.setState(GameStateManager.LEVELSTATE);
				break;
			case 1 : gsm.setState(GameStateManager.SETUPSTATE);	
				break;
			case 2: gsm.setState(GameStateManager.TurtleDesigner);
				break;
			case 3 : gsm.setState(GameStateManager.OPTIONSTATE);
				break;
			case 4 : 
				System.exit(0);
				break;
		}
	}

	@Override
	public void init(){
		// TODO Auto-generated method stub
		
		
		
	}
	
}
