package graphicalElements;

import java.awt.Color;
import java.awt.Graphics2D;
import Entity.Player;
import Main.GamePanel;


public class DrawnHealth{

	//Parameters

	private int xpos;
	private int ypos;
	private int width;
	private int height;
	
	/*
	 * Constructor
	 * Sets up the parameters for the bar
	 */
	public DrawnHealth(Player player){

		xpos = GamePanel.WIDTH - 210; 
		ypos = 10;
		width = 200; 
		height = 40;	
	}
	
	/*
	 * Draws the health bar to the image
	 */
	public void draw(Graphics2D g, Player player){
		g.setColor(Color.GRAY);
		g.fillRect(xpos, ypos, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xpos, ypos, player.getHealth()*2, height);
		g.drawRect(xpos, ypos, width, height);
	}

}
