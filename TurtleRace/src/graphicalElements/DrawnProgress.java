package graphicalElements;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class DrawnProgress extends GraphicalElement{
	private JPanel progBar;
	
	public DrawnProgress(){
		progBar = new JPanel();
		progBar.setLayout(new FlowLayout());
		progBar.setLocation(0, 0);
		progBar.setVisible(true);
		progBar.setPreferredSize(new Dimension(100, 30));
	}
}
