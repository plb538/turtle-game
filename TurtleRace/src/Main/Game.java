package Main;

import javax.swing.JFrame;

public class Game{
	
	public static void main(String[] args){
		JFrame window = new JFrame("Game");
		window.setContentPane(new GamePanel());
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack();
	}
	
}

