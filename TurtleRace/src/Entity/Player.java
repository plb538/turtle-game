package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import TileMap.TileMap;
import networking.InformationPacket;

@SuppressWarnings("serial")
public class Player extends MapObject implements java.io.Serializable{

	//player stuff
	private int health;
	private int maxHealth;
	private boolean dead = false;
	private boolean facingRight;
	public int state;
	private long startTime;
	private long finishTime;
	private int deathCounter;
	private boolean wearingHat; 
	
	//attacking stuff
	private boolean attacking;
	public Weapon weapon;
	
	//player hat
	private Hat hat = null;
	
	//gliding
	private boolean gliding;
	
	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1, 3, 2, 4};
	
	//animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int GLIDING = 2;
	private static final int ATTACKING = 3;
	
	
	public Player(TileMap tm){
		//super(tm);
		
		startTime = System.nanoTime();
		
		weapon = new Weapon(this);
		
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
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/sprites/turtleSpriteSheet.png"));
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
		if((left || right) && !gliding && !attacking){
			if(curAction != WALKING){
				curAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
			}
		}
		else if((left || right) && gliding){
			if(curAction != GLIDING){
				curAction = GLIDING;
				animation.setFrames(sprites.get(GLIDING));
				animation.setDelay(100);
			}
		}
		else if(gliding){
			if(curAction != GLIDING){
				curAction = GLIDING;
				animation.setFrames(sprites.get(GLIDING));
				animation.setDelay(100);
			}
		}
		else if((left || right) && attacking){
			if(curAction != ATTACKING){
				curAction = ATTACKING;
				animation.setFrames(sprites.get(ATTACKING));
				animation.setDelay(40);
				
				weapon.attack();
			}
		}
		else if(attacking){
			if(curAction != ATTACKING){
				curAction = ATTACKING;
				animation.setFrames(sprites.get(ATTACKING));
				animation.setDelay(40);
				
				weapon.attack();
			}
		}
		else{
			if(curAction != IDLE){
				curAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(100);
			}
		}
		animation.update();
		weapon.update();
		//stops attack animation
		if(animation.getFrame() == 3){
			attacking = false;
			weapon.stopAttack();
		}
		
		if(right) facingRight = true;
		if(left) facingRight = false;
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		
		//draw player
		if(facingRight){
			if(attacking){
				g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), width, height, null);
				weapon.draw(g, facingRight);
			}
			else{
				g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), width, height, null);
			}
			
		}
		else{
		    if(attacking){
		    	g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
		    	weapon.draw(g, facingRight);		    }
		    else{
		    	g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
		    }
		}
		if(checkWearingHat()){
			hat.draw(g, facingRight);
		}
	}
	
	public int getHealth(){return health;}
	
	public void takeDamage(int amount){health -= amount;}
	
	public boolean checkDead(){return dead;}
	
	public int getMaxHealth(){return maxHealth;}
	
	public void setGliding(boolean b){gliding = b;}
	
	public void setAttacking(boolean b){attacking = b;}
	
	public boolean checkIfAttacking(){return attacking;}
	
	public boolean getFacingRight(){return facingRight;}
	
	public void setFacingRight(boolean fr){this.facingRight = fr;}
	
	public Animation getAnimation(){return animation;}
	
	public void setAnimation(Animation a){this.animation = a;}
	
	public Weapon getWeapon(){return weapon;}
	
	public void setHat(Hat h){
		if(h!=null){
			hat = h;
			wearingHat = true;
		}
	}
	
	public Hat getHat(){return hat;}
	
	public boolean checkWearingHat(){return wearingHat;}
	
	public void setWearingHat(boolean b){wearingHat = b;}
	
	public void checkResetConditions(){
		if(getHealth() <= 0){
			dead = true;
			deathCounter++;
		}
		
		int resetx = tileMap.getTileSize()+100;
		int resety = tileMap.getHeight() - 200;
		
		if(checkDead()){
			setPosition(resetx,resety);
			dead = false;
			health = 100;
		}
	}
	
	public void updateP2(InformationPacket packet){
		this.setPosition(packet.getx(), packet.gety());
		this.health = packet.gethealth();
		//this.setHat(packet.gethat());
		this.state = packet.getstate();
		if(packet.getstate() < 6){
			this.finishTime = packet.getTime();
		}
		//System.out.print(packet.getaction());
		curAction = packet.getaction();
		animation.setFrames(sprites.get(packet.getaction()));
		animation.setDelay(100);
		this.setFacingRight(packet.getFacingRight());
		//this.setAttacking(packet.checkIfAttacking());
	}

	public int getState(){
		// TODO Auto-generated method stub
		return state;
	}
	
	public void setStart(){
		startTime = System.nanoTime();
	}
	
	public long getStartTime(){
		return startTime;
	}

	public int getDeathCount(){
		return deathCounter;
	}

	public long getFinishTime(){
		// TODO Auto-generated method stub
		return finishTime;
	}

	public void setFinishTime(long nanoTime){
		// TODO Auto-generated method stub
		finishTime = nanoTime;
	}

}
