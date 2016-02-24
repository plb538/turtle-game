package graphicalElements;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class DrawnProgress extends GraphicalElement{
	private JPanel progBar;
	private JProgressBar jpb;
	
	public DrawnProgress(){
		progBar = new JPanel(new BorderLayout());
		progBar.setVisible(true);
		progBar.setSize(new Dimension(100, 40));
		
		jpb = new JProgressBar();
		jpb.setMinimum(0);
		jpb.setMaximum(100);
		jpb.setVisible(true);
	}
	
	public void update(){
		
	}
}
