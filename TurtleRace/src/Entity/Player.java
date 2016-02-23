package Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import TileMap.TileMap;

public class Player extends MapObject{

	//player stuff
	private int health;
	private int maxHealth;
	private int damage;
	private boolean dead = false;

	//animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2, 8, 1, 2, 4, 2, 5};
	
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
	}
}
