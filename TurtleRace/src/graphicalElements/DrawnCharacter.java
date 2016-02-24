package graphicalElements;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class DrawnCharacter extends JPanel {
 
	public DrawnCharacter(String path, Object o){
		referenceObject = o;
		try{
			myImage = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(Throwable e){
			System.out.println(e.getMessage());
		}
	}
 
	public int xpos;
	public int ypos;
	public Image myImage;
	private Object referenceObject;
 
	public void update(){
		if(referenceObject instanceof common.Character){
			common.Character myChar = (common.Character)(referenceObject);
			xpos = myChar.xpos;
			ypos = myChar.ypos;
		}
	}
}