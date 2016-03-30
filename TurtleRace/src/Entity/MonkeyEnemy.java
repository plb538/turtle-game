package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Game;
import TileMap.TileMap;

public class MonkeyEnemy extends MapObject{
	
	//character information
	public int health;
	private int maxHealth;
	private boolean dead = false;
	private long startTime;
	private boolean facingRight;
	private int targetTime = 2000;
		
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 3};
	
	//animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	
	public MonkeyEnemy(TileMap tm){
		super(tm);
		facingRight = false;
		dead = false;
		//monkey's size and collision size
		width = 64;
		height = 64;
		cwidth = 32;
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
			
			for(int i = 0; i < numFrames.length; i++){
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
	
		int rand = (int)(Math.random()*2);
	
		if(rand % 2 == 0){
			if(animation.getFrame() == 2){
				stopMoving();
			}
			else{
				walkLeft();
			}
		}
		else{
			if(animation.getFrame() == 2){
				stopMoving();
			}
			else{
				//walkRight();
			}
		}
		animation.update();
		if(this.health <= 0){
			this.health = 0;
			this.dead = true;
		}
	}
	
	public void draw(Graphics2D g){
	    //positions object on map
		setMapPosition();
		if((Game.p1.getx() <= this.getx()) && (Game.p2.getx() <= this.getx())){
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
		}
		else{
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), width, height, null);
		}
	}
	
	public void walkLeft(){
		if(curAction != WALKING){
			curAction = WALKING;
			animation.setFrames(sprites.get(WALKING));
			animation.setDelay(100);
			facingRight = false;
			left = true;
			right = false;
		}
	}
	
	public void walkRight(){
		if(curAction != WALKING){
			curAction = WALKING;
			animation.setFrames(sprites.get(WALKING));
			animation.setDelay(100);
			facingRight = true;
			right = true;
			left = false;
		}
	}
	
	public void stopMoving(){
		if(curAction != IDLE){
			curAction = IDLE;
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(400);
			right = false; left = false;
		}
	}
	
	public int getHealth(){return health;}
	
	public void setHealth(int health){this.health = health;}
	
	public boolean checkIfDead(){return dead;}
	
	public boolean checkFacingRight(){return facingRight;}
}
