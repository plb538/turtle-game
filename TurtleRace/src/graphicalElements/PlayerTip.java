package graphicalElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


import Main.Game;

public class PlayerTip{

	private int xpos;
	private int ypos;
	private String string;
	
	public PlayerTip(String string, int x, int y){
		this.string = string;
		xpos = x;
		ypos = y;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		if(Game.p1.getx() < 350){
			g.drawString(string, xpos, ypos);
		}
		
	}
	
}
