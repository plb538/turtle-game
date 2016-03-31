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
	
	private boolean activated;
	
	public Portal(TileMap tm){
		super(tm);
		//portal size and collision size
		width = 96;
		height = 96; 
		cwidth = 82;
		cheight = 90;
		
		//So portal starts on ground
		fallSpeed = 10; 
		
		activated = false;
		
		//load sprite
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Other/LargerPortal.png"));
			sprites = new ArrayList<BufferedImage[]>();
			
			//split apart sprite sheet
			for(int i = 0; i < numFrames.length; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++){
					bi[j] = spriteSheet.getSubimage(j*width, i*height, width, height);
				}
				sprites.add(bi);
			}
			//default animation
			animation = new Animation();
			curAction = IDLE;
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(-1);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(){
		dy += fallSpeed; //to start on ground
		checkTileMapCollision(); //check for collision
		setPosition(xtemp, ytemp);
		
		animation.update();
		if(!activated){
			animation.setDelay(-1);
		}
		else{
			animation.setDelay(50);
		}
	}
	//draws the portal to the map
	public void draw(Graphics2D g, TileMap tm){
		g.drawImage(animation.getImage(), (int)(x + tm.getx() - width / 2), (int)(y + tm.gety() - height / 2), width, height, null);
	}
	
	public void activate(){
		activated = true;
	}
	
	public boolean checkIfActivated(){return activated;}
}
