package graphicalElements;

import java.awt.Image;
import common.Character;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class DrawnCharacter extends JPanel {
 
	public DrawnCharacter(String path, Character _myChar;){
		myChar = _myChar;
		try{
			myImage = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(Throwable e){
			System.out.println(e.getMessage());
		}
	}
 
	public int xpos;
	public int ypos;
	public Image myImage;
	private Character myChar;
 
	public void update(){
		xpos = myChar.xpos;
		ypos = myChar.ypos;
		
	}
}