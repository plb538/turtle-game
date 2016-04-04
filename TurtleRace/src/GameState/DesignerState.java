package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Entity.Hat;
import Main.Game;
import TileMap.Background;

public class DesignerState extends GameState implements ActionListener{

	private Background bg;
	
	private String[] options = { "Headband", "Helicopter Hat", "Undies", "Strawberry Hat"};
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public DesignerState(GameStateManager gsm){
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
	public void init(){
		
		//int hostInput = JOptionPane.YES_NO_OPTION;
	    //int hostResult = JOptionPane.showConfirmDialog(null, "Are you the host?", "", hostInput);
		
		JButton[] butt = new JButton[4];
		butt[0] = new JButton("HeadBand");
		butt[1] = new JButton("Helicopter Hat");
		butt[2] = new JButton("Undies");
		butt[3] = new JButton("Strawberry Hat");
		int input = JOptionPane.showOptionDialog(null, "Please Select a Hat", "Hat Select", 0, JOptionPane.INFORMATION_MESSAGE, null, butt, butt[0]);
		switch(input){
			
			case 0: Game.p1.setHat(new Hat(Game.p1));
					
				break;
			
		}
	}
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
		//g.drawString("Your ass is:", GamePanel.WIDTH/6, 150);
		
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(gsm.MENUSTATE);
	}

	@Override
	public void keyReleased(int k){}

	@Override
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		
	}
}
