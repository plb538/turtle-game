package GameState;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import Entity.Hat;
import Main.Game;
import TileMap.Background;

public class DesignerState extends GameState implements ActionListener{

	private Background bg;
	
	private String[] options = {"Headband", "Helicopter Hat", "Undies", "Strawberry Hat"};
	private Color titleColor;
	private Font titleFont;
	
	private JFrame frame;
	
	private Hat hat;
	
	public DesignerState(GameStateManager gsm){
		this.gsm = gsm;
		frame = new JFrame();
	}
	
	@Override
	public void init(){
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(200, 180);
		frame.setLocationRelativeTo(null);
		
		JButton butt0 = new JButton("Headband");
		butt0.addActionListener(this);
		frame.add(butt0);
		
		JButton butt1 = new JButton("Helicopter Hat");
		butt1.addActionListener(this);
		frame.add(butt1);
		
		JButton butt2 = new JButton("Undies");
		butt2.addActionListener(this);
		frame.add(butt2);
		
		JButton butt3 = new JButton("Strawberry Hat");
		butt3.addActionListener(this);
		frame.add(butt3);
	}
	@Override
	public void update(){
		//bg.update();
		if(!frame.isShowing()){
			gsm.setState(gsm.MENUSTATE);
		}
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		//bg.draw(g);
		
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
		if(e.getActionCommand() == options[0]){
			hat = new Hat(Game.p1);
			hat.initHat("headbandNoTurtle.png");
			Game.p1.setHat(hat);
		}
		else if(e.getActionCommand() == options[1]){
			hat = new Hat(Game.p1);
			hat.initHat("heliHatNoTurtle.png");
			Game.p1.setHat(hat);
		}
		else if(e.getActionCommand() == options[2]){
			hat = new Hat(Game.p1);
			hat.initHat("femaleUndiesNoTurtle.png");
			Game.p1.setHat(hat);
		}
		else if(e.getActionCommand() == options[3]){
			hat = new Hat(Game.p1);
			hat.initHat("strawberryHatNoTurtle.png");
			Game.p1.setHat(hat);
		}
	}
}
