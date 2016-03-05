package Main;

import javax.swing.JFrame;

import Entity.Player;

public class Game{
	
	public static Player p1;
	
	public static void main(String[] args){
		
		JFrame window = new JFrame("A Tale of Two Turtles");
		window.setContentPane(new GamePanel());
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack(); window.setLocationRelativeTo(null);
		
		
	}
	
}

