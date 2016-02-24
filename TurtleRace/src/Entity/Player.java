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
	private int fire;
	private int maxFire;
	private boolean dead = false;
	private boolean flinching;
	private long flinchTimer;
	private boolean facingRight;
	
	//fireball
	private boolean firing;
	private int fireCost;
	private int fireBallDamage;
	//private ArrayLsit<FireBall> fireballs;
	
	//scratch
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;
	
	//gliding
	private boolean gliding;
	
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1};
	
	//animation actions
	private static final int IDLE = 0;
	//private static final int WALKING = 1;
	//private static final int JUMPING = 2;
	//private static final int FALLING = 3;
	//private static final int GLIDING = 4;
	//private static final int FIREBALL = 5;
	//private static final int SCRATCHING = 6;
	
	public Player(TileMap tm){
		super(tm);
		
		//sprite width and height
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 15;
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		fire = maxFire = 2500;
		fireCost = 200;
		fireBallDamage = 5;
		//fireBalls = new ArrayList<FireBalls>();
		scratchDamage = 8;
		scratchRange = 40;
		
		//load sprites
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/sprites/DerpTurtle1.gif"));
			sprites = new ArrayList<BufferedImage[]>();
	
			//Padyy remember to change this back
			//---------------------------------------------------------------------------
			
			for(int i = 0; i < 1; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++){
					if(i != 6){
						bi[j] = spriteSheet.getSubimage(j*width, i*height, width, height);
					}
					else{
						bi[j] = spriteSheet.getSubimage(j*width*2, i*height, width, height); //these sprites are 60x30
					}
				}
				sprites.add(bi);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//------------------------------------------------------------------------------------------------------------
		
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
		//cant move while attacking unless in air
//		if((curAction == SCRATCHING || curAction == FIREBALL) && !(jumping || falling)){
//			dx = 0;
//		}
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
		
		//set animation
//		if(scratching){
//			if(curAction != SCRATCHING){
//				curAction = SCRATCHING;
//				animation.setFrames(sprites.get(SCRATCHING));
//				animation.setDelay(50);
//				width = 60;
//			}
//		}
//		else if(firing){
//			if(curAction != FIREBALL){
//				curAction = FIREBALL;
//				animation.setFrames(sprites.get(FIREBALL));
//				animation.setDelay(100);
//				width = 30;
//			}
//		}
//		else if(dy > 0){
//			if(gliding){
//				if(curAction != GLIDING){
//					curAction = GLIDING;
//					animation.setFrames(sprites.get(GLIDING));
//					animation.setDelay(100);
//					width = 30;
//				}
//			}
//			else if(curAction != FALLING){
//				curAction = FALLING;
//				animation.setFrames(sprites.get(FALLING));
//				animation.setDelay(100);
//				width = 30;
//			}
//		}
//		else if(dy < 0){
//			if(curAction != JUMPING){
//				curAction = JUMPING;
//				animation.setFrames(sprites.get(JUMPING));
//				animation.setDelay(-1);
//				width = 30;
//			}
//		}
//		else if(left || right){
//			if(curAction != WALKING){
//				curAction = WALKING;
//				animation.setFrames(sprites.get(WALKING));
//				animation.setDelay(40);
//				width = 30;
//			}
//		}
//		else{
			if(curAction != IDLE){
				curAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
//		animation.update();
		
		//set direction
//		if(curAction != SCRATCHING && curAction != FIREBALL){
//			if(right) facingRight = true;
//			if(left) facingRight = false;
//		}
//	}
	
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
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), null);
		}
		else{
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
		}
	}
	
	public int getHealth(){return health;}
	
	public int getMaxHealth(){return maxHealth;}
	
	public int getFire(){return fire;}
	
	public int getMaxFire(){return maxFire;}
	
	public void setFiring(){firing = true;}
	
	public void setScratching(){scratching = true;}
	
	public void setGliding(boolean b){gliding = b;}
}
