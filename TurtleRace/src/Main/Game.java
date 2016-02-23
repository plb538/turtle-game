package Main;

import javax.swing.JFrame;

import common.Character;
import weapons.BinaryRifle;

public class Game{
	
	public static void main(String[] args){
		
		//Game Elements-----------------------------------------------------------------------------
		
			//Level
			levelElements.Level level = new levelElements.Level();
			levelElements.Challenges c = new levelElements.Enemies(3, 1280);		
			level.addChallenge(c);
			Character player = new playerElements.Player(new BinaryRifle(), 100, 720*2/3, 0);
				
		//------------------------------------------------------------------------------------------

		JFrame window = new JFrame("A Tale of Two Turtles");
		window.setContentPane(new GamePanel());
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack(); window.setLocationRelativeTo(null);
	}
	
}

