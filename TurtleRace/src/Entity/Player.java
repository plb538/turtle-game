package Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Player extends MapObject{

	//player stuff
	private int health;
	private int maxHealth;
	private int damage;
	private boolean dead = false;

	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {4, 3};
	
	//animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int ATTACKING = 4;
	
	public Player(TileMap tm){
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.;
		
		health = maxHealth = 100;
		
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(arg0));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
