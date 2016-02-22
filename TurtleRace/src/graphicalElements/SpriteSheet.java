package graphicalElements;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class SpriteSheet{
	
	private String path;
	private int width;
	private int height;
	private int[] pixels;
	
	public SpriteSheet(String path){
		BufferedImage image = null;
		
		try{
			image = ImageIO.read(new FileImageInputStream(new File(path)));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		if(image == null){
			return;
		}
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = (pixels[i] & 0xff)/64; //Removes alpha channel
		}
	}
}
