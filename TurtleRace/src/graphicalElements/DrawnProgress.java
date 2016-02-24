package graphicalElements;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Entity.Player;

public class DrawnProgress extends GraphicalElement{
	private JPanel progBar;
	private JProgressBar jbar;
	private Player p1;
	
	public DrawnProgress(){
		jbar = new JProgressBar(0, 100);
		jbar.setStringPainted(true);
		jbar.setVisible(true);
	}
	
	public void update(){
		
	}
}
