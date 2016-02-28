package Main;

import javax.swing.JFrame;

import Entity.Player;
import common.Character;
import weapons.BinaryRifle;

public class Game{
	
	public static Player p1;
	
	public static void main(String[] args){
		
		//Game Elements-----------------------------------------------------------------------------
		
		//Level
			levelElements.Level level = new levelElements.Level();
			levelElements.Challenges c1 = new levelElements.Enemies(3);
			levelElements.Challenges c2 = new levelElements.Enemies(4);	
			levelElements.Challenges c3 = new levelElements.Enemies(5);	
			level.addChallenge(c1);
			level.addChallenge(c2);
			level.addChallenge(c3);
			
			Character player = new playerElements.Player(new BinaryRifle(), 100, 100, 0);
			//HUD myHUD = new HUD(player, player);
		//------------------------------------------------------------------------------------------

		JFrame window = new JFrame("A Tale of Two Turtles");
		window.setContentPane(new GamePanel(level, player));
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack(); window.setLocationRelativeTo(null);
		
		
	}
	
}

