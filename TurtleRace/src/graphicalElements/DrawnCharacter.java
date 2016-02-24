package graphicalElements;


import java.awt.image.BufferedImage;

import common.Character;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawnCharacter extends JPanel {
 
	public DrawnCharacter(String path, Character _myChar){
		myChar = _myChar;
		try{
			myImage = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(Throwable e){
			System.out.println(e.getMessage());
		}
	}

	public BufferedImage myImage;
	private Character myChar;
}