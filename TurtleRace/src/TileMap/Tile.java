package TileMap;

import java.awt.image.BufferedImage;

public class Tile{

	private BufferedImage image; //tile sprite
	private int type; 
	
	public static final int NORMAL = 0; //player moves through
	public static final int BLOCKED = 1; //player collides with
	
	//each tile gets an Image object and is set a type
	public Tile(BufferedImage image, int type){
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public int getType(){
		return type;
	}
}
