package graphicalElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Entity.Player;

public class PlayerTip{

	private int xpos;
	private int ypos;
	private int width;
	private int height;
	
	public PlayerTip(){
		xpos = 200;
		ypos = 200;
		width = 100;
		height = 20;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Press Up to jump and W to attack", xpos, ypos);
	}
	
}
