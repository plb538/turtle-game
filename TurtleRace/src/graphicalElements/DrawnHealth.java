package graphicalElements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Entity.Player;

public class DrawnHealth extends GraphicalElement{

	private int health;
	
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	
	private BufferedImage hpBar;
	private JPanel panel;
	
	public DrawnHealth(Player player){
		health = player.getHealth();
		xpos = 1060; 
		ypos = 10;
		width = 200; 
		height = 40;	
	}
	
	public void draw(Graphics2D g, Player player){
		g.setColor(Color.GRAY);
		g.fillRect(xpos, ypos, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xpos, ypos, player.getHealth()*2, height);
		g.drawRect(xpos, ypos, width, height);
	}

}
