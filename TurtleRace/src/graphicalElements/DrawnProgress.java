package graphicalElements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Entity.Player;
import TileMap.TileMap;

public class DrawnProgress extends GraphicalElement{
	
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	
	public DrawnProgress(Player player, TileMap tm){
		xpos = 10; 
		ypos = 10;
		width = 300; 
		height = 40;	
	}
	
	public void draw(Graphics2D g, Player player, TileMap tm){
		double playerProg = (double)player.getx()/(double)tm.getWidth() * 100;
		System.out.println(player.getx());
		int intPlayerProg = (int)playerProg;
		g.setColor(Color.WHITE);
		g.fillRect(xpos, ypos, width, height);
		g.setColor(Color.BLACK);
		g.fillRect(xpos, ypos, intPlayerProg*3, height);
		g.drawRect(xpos, ypos, width, height);
	}
}
