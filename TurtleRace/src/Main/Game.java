package Main;

import java.awt.Canvas;

import javax.swing.JFrame;

import Entity.Player;

/*
 * Main for the game
 * Sets up the JFrame window and creates the GamePanel
 * 
 */

public class Game{
	
	public static Player p1;
	public static Player p2;
	public static Printer Printer = new Printer();
	public static JFrame window;
	
	public static void main(String[] args){
		p1 = new Player(null);
		p2 = new Player(null);
		window = new JFrame("A Tale of Two Turtles");
		window.setContentPane(new GamePanel());
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack(); window.setLocationRelativeTo(null);
		
	}
	
}

