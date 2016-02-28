package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Portal extends MapObject{
	
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2};
		
	//animation actions		
	private static final int IDLE = 0;
	
	public Portal(TileMap tm){
		super(tm);
		
		width = 96;
		height = 96; 
		cwidth = 82;
		cheight = 90;
		
		fallSpeed = 10;
		
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Other/LargerPortal.png"));
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < numFrames.length; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++){
					bi[j] = spriteSheet.getSubimage(j*width, i*height, width, height);
				}
				sprites.add(bi);
			}
			animation = new Animation();
			curAction = IDLE;
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(50);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(){
		dy += fallSpeed; //to start on ground
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		animation.update();
	}
	
	public void draw(Graphics2D g, TileMap tm){
		g.drawImage(animation.getImage(), (int)(x + tm.getx() - width / 2), (int)(y + tm.gety() - height / 2), width, height, null);
	}
}
