package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Game;
import TileMap.TileMap;

public class MonkeyEnemy extends MapObject{
	
	//character information
	private int health;
	private int maxHealth;
	private boolean dead = false;
	private boolean flinching;
	private long flinchTimer;
	private long startTime;
	private boolean facingRight;
	
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 3};
	
	//animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	
	public MonkeyEnemy(TileMap tm){
		super(tm);
		
		//monkey's size and collision size
		width = 64;
		height = 64;
		cwidth = 36;
		cheight = 60;
		
		//physics modifiers
		moveSpeed = 0.3 ;
		maxSpeed = 1.6 ;
		stopSpeed = 0.4 ;
		fallSpeed = 0.15 ;
		maxFallSpeed = 4.0;
		
		health = maxHealth = 100;
		
		//load sprite
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/sprites/monkeySpriteSheet.png"));
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < 2; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++){
					bi[j] = spriteSheet.getSubimage(j*width, i*height, width, height); //breaks down the sprite sheet					
				}
				sprites.add(bi);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//default animation
		animation = new Animation();
		curAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
	}
	
	public void getNextPosition(){
		//movement
		if(left){
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}
		else if(right){
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed;
			}
		}
		else{
			if(dx > 0){
				dx -= stopSpeed;
				if(dx < 0){
					dx = 0;
				}
			}
			else if(dx < 0){
				dx += stopSpeed;
				if(dx > 0){
					dx = 0;
				}
			}
		}
		if(jumping && !falling){
			dy = jumpStart;
			falling = true;
		}
		if(falling){
			dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
		}
	}
	
	public void update(){
		getNextPosition(); 
		checkTileMapCollision();//check for collision
		setPosition(xtemp, ytemp);
		
		startTime = System.nanoTime();
		curAction = WALKING;
		animation.setFrames(sprites.get(WALKING));
		animation.setDelay(100);
		walkLeft();
	}
	
	public void draw(Graphics2D g){
	    //positions object on map
		setMapPosition();
		g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), width, height, null);
	}
	
	public void walkLeft(){
		facingRight = false;
		long elapsed = (Math.abs(System.nanoTime() - startTime) / 1000000);
		//while(elapsed < animation.getDelay()){
		//	left = true;
		//}
	}
	
	public void walkRight(){
		
	}
}
