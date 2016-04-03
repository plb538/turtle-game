package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Hat extends MapObject{

	private Player player;
	
	private static final int IDLE = 0;
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {1};
	
	private int xOffset = 0;
	private int yOffset = 10;
	
	private String hatName;
	
	public String getHatName(){
		return hatName;
	}

	public void setHatName(String hatName){
		this.hatName = hatName;
	}

	public Hat(Player p){
		
		player = p;
		//sprite width and height
		width = 64;
		height = 84;
		
		//load sprites
		try{
			BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/hats/heliHatNoTurtle.png"));
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
}
