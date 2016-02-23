package Entity;

import java.awt.Rectangle;

import TileMap.TileMap;

public abstract class MapObject{
	
	//tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap, ymap;
	
	//position and vector
	protected double x, y, dx, dy;
	
	//dimensions
	protected int width;
	protected int height;
	
	//collision box
	protected int cwidth;
	protected int cheight;
	
	//collision
	protected int curRow, curCol;
	protected double xdest, ydest;
	protected double xtemp, ytemp;
	protected boolean topLeft, topRight;
	protected boolean botLeft, botRight;
	
	//animation
	protected Animation animation;
	protected int curAction;
	protected int prevAction;
	
	//movement
	protected boolean left, right, up, down;
	protected boolean jumping;
	protected boolean falling;
	
	//movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	public MapObject(TileMap tm){
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersects(MapObject o){
		Rectangle r1 = getRect();
		Rectangle r2 = o.getRect();
		return r1.intersects(r2);
	}
	
	private Rectangle getRect(){
		return new Rectangle((int)x - cwidth, (int)y - cheight, cwidth, cheight);
	}
	
	public void checkTileMapCollision(){
		curCol = (int)x / tileSize;
		curRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calcCorners(x, ydest);
	}
	
	public void calcCorners(double x, double y){
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int botTile = (int)(y + cheight / 2 - 1) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(botTile, leftTile);
		int br = tileMap.getType(botTile, rightTile);
	}
}
