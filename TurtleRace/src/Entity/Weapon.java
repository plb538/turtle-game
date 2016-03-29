package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.Game;

public class Weapon extends MapObject{

	//animations
	private static final int ATTACKING = 0;
	private static final int IDLE = 1;
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {4, 1};
	
	private Player player;
	
	private int xOffset = 16;
	private int yOffset = 16;
	
	public Weapon(Player p){
		
		player = p;
		//sprite width and height
		width = 96;
		height = 96;
		cwidth = 36;
		cheight = 60;
		
		//load sprites
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Weapons/weaponSpriteSheet.png"));
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
		animation = new Animation();
		curAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(-1);
	}
	
	public void update(){
		animation.update();
	}
	
	public void draw(Graphics2D g, boolean facingRight){
		if(facingRight){
			g.drawImage(animation.getImage(), (int)(player.getx() + player.xmap - width / 2), (int)(player.gety() - yOffset + player.ymap - height / 2), width, height, null);
		}
		else{
			g.drawImage(animation.getImage(), (int)(player.getx() + player.xmap - width / 2 + width), (int)(player.gety() - yOffset + player.ymap - height / 2), -width, height, null);
		}
	}
	
	public void attack(){
		if(curAction != ATTACKING){
			curAction = ATTACKING;
			animation.setFrames(sprites.get(ATTACKING));
			animation.setDelay(100);
		}
	}
	
	public void stopAttack(){
		if(curAction != IDLE){
			curAction = IDLE;
			animation.setFrames(sprites.get(IDLE));
			animation.setDelay(-1);
		}
	}
}
