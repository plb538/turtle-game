package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import TileMap.TileMap;

public class Player extends MapObject{

	//player stuff
	private int health;
	private int maxHealth;
	private boolean dead = false;
	private boolean flinching;
	private long flinchTimer;
	private boolean facingRight;
	
	//gliding
	private boolean gliding;
	
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 3};
	
	//animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	//private static final int JUMPING = 2;
	//private static final int FALLING = 3;
	//private static final int GLIDING = 4;
	
	public Player(TileMap tm){
		super(tm);
		
		//sprite width and height
		width = 64;
		height = 64;
		cwidth = 36;
		cheight = 60;
		
		//physics modifiers
		moveSpeed = 0.3;
		maxSpeed = 2.0;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 100;
		
		//load sprites
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/sprites/LargerTurtle.png"));
			sprites = new ArrayList<BufferedImage[]>();
	        
	        //break up sprite sheet
			for(int i = 0; i < numFrames.length; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++){
					bi[j] = spriteSheet.getSubimage(j*width, i*height, width, height);			
				}
				sprites.add(bi);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//set default animation
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
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
		}
	}
	
	public void update(){
		//update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		checkResetConditions();
		
		//set animation
		if(left || right){
			if(curAction != WALKING){
				curAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
				//width = 64;
			}
		}
		else{
			if(curAction != IDLE){
				curAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(-1);
				//width = 64;
			}
		}
		animation.update();
		
		if(right) facingRight = true;
		if(left) facingRight = false;
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		
		//draw player
		if(flinching){
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0){
				return;
			}
		}
		if(facingRight){
		    //draws player facing the right
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), width, height, null);
		}
		else{
		    //draws player facing the left
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
		}
	}
	
	public int getHealth(){return health;}
	
	public void takeDamage(int amount){health -= amount;}
	
	public boolean checkDead(){return dead;}
	
	public int getMaxHealth(){return maxHealth;}
	
	public void setGliding(boolean b){gliding = b;}
	
	public void checkResetConditions(){
		if(getHealth() < 0){
			dead = true;
		}
		
		int resetx = tileMap.getTileSize()+25;
		int resety = tileMap.getHeight() - 200;
		
		if(checkDead()){
			setPosition(resetx,resety);
			dead = false;
			health = 100;
		}
	}
}
