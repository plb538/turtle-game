package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import TileMap.Background;

public class MenuState extends GameState{

	
	//this state's background
	private Background bg;
	
	//private String[] options = {"Start Single Player", "Start Multiplayer", "Turtle Designer", "Options",  "Quit"};
	private String[] options = {"Start Single Player", "Start Multiplayer",  "Quit"};

	private int inputcounter = 0;
	
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	private boolean alreadyAsked;
	
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
		alreadyAsked = false;
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
		konami(k);
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
		if(!alreadyAsked){
			switch(curOption){
				case 0 : gsm.setState(GameStateManager.TURTLEDESIGNER);
					break;
				case 1 : gsm.setState(GameStateManager.TURTLEDESIGNER);	
					break;
				case 2 : //gsm.setState(GameStateManager.OPTIONSTATE);
					System.exit(0);
					break;
			}
			alreadyAsked = true;
		}
		else{
			switch(curOption){
			case 0 : gsm.setState(GameStateManager.LEVELSTATE);
				break;
			case 1 : gsm.setState(GameStateManager.SETUPSTATE);	
				break;
			case 2 : //gsm.setState(GameStateManager.OPTIONSTATE);
				System.exit(0);
				break;
			}
		}
	}

	@Override
	public void init(){
	
	}
	
	private void konami(int k){
		//System.out.println(inputcounter);
		switch(inputcounter){
			case 0: if(k == KeyEvent.VK_UP){inputcounter++;} else inputcounter = 0;
				break;
			case 1: if(k == KeyEvent.VK_UP){inputcounter++;} else inputcounter = 0;
				break;
			case 2: if(k == KeyEvent.VK_DOWN){inputcounter++;} else inputcounter = 0;
				break;
			case 3: if(k == KeyEvent.VK_DOWN){inputcounter++;} else inputcounter = 0;
				break;
			case 4: if(k == KeyEvent.VK_LEFT){inputcounter++;} else inputcounter = 0;
				break;
			case 5: if(k == KeyEvent.VK_RIGHT){inputcounter++;} else inputcounter = 0;
				break;
			case 6: if(k == KeyEvent.VK_LEFT){inputcounter++;} else inputcounter = 0;
				break;
			case 7: if(k == KeyEvent.VK_RIGHT){inputcounter++;} else inputcounter = 0;
				break;
			case 8: if(k == KeyEvent.VK_B){inputcounter++;} else inputcounter = 0;
				break;
			case 9: if(k == KeyEvent.VK_A){
						inputcounter = 0;
						gsm.setState(GameStateManager.OPTIONSTATE);
					} else inputcounter = 0;
				break;
		}
		
	}
	
}
