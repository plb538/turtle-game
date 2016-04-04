package graphicalElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import Entity.MonkeyEnemy;
import Entity.Player;

import TileMap.TileMap;

public class DrawnProgress{
	
	//Parameters
	private int xpos;
	private int ypos;

	
	/*
	 * Constructor
	 * Sets up the parameters for the bar
	 */

	public DrawnProgress(Player player, TileMap tm){
		xpos = 20; 
		ypos = 20;
	
	}

	/*
	 * Draws the progress bar to the image
	 */
	/*
	public void draw(Graphics2D g, Player player, TileMap tm){
		double playerProg = (double)player.getx()/(double)tm.getWidth() * 100;
		int intPlayerProg = (int)playerProg;
		g.setColor(Color.WHITE);
		g.fillRect(xpos, ypos, width, height);
		g.setColor(Color.BLACK);
		g.fillRect(xpos, ypos, intPlayerProg*3, height);
		g.drawRect(xpos, ypos, width, height);
	}
	*/
	
	public void draw(Graphics2D g, ArrayList<MonkeyEnemy> monkeys){
		// TODO Auto-generated method stub
		
		int aliveCount = 0;
		for(int i = 0; i < monkeys.size(); i++){
			if(!(monkeys.get(i).checkIfDead())){
				aliveCount++;
			}
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Monkeys Remaining: " + aliveCount + "/" + monkeys.size(), xpos, ypos);		
		
		
	}
}
