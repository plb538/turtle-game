package graphicalElements;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Entity.Player;

public class DrawnProgress extends GraphicalElement{
	private JPanel progBar;
	private JProgressBar jbar;
	private Player p1;
	private BufferedImage image;
	
	public DrawnProgress(){
		jbar = new JProgressBar(0, 100);
		jbar.setStringPainted(true);
		jbar.setVisible(true);
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/sprites/temp-turtle.PNG"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(){
		
	}
}
