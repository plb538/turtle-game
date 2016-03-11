package Entity;

import java.awt.Rectangle;

import Main.GamePanel;
import TileMap.Tile;
import TileMap.TileMap;

public abstract class MapObject{
	
	//tile information
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap, ymap;
	
	//position and vector information
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
	protected boolean falling = true;
	
	//movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	public MapObject(){
		
	}
	
	public MapObject(TileMap tm){
	    //init tile size
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public void checkTileMapCollision(){
		curCol = (int)x / tileSize;
		curRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calcCorners(x, ydest);
		if(dy < 0){
			if(topLeft || topRight){
				dy = 0;
				ytemp = curRow * tileSize + cheight / 2;
			}
			else{
				ytemp += dy;
			}
		}
		if(dy > 0){
			if(botLeft || botRight){
				dy = 0;
				falling = false;
				ytemp = (curRow + 1) * tileSize - cheight / 2;
			}
			else{
				 ytemp += dy;
			}
		}
		calcCorners(xdest, y);
		if(dx < 0){
			if(topLeft || botLeft){
				dx = 0;
				xtemp = curCol * tileSize + cwidth / 2;
			}
			else{
				xtemp += dx;
			}
		}
		if(dx > 0){
			if(topRight || botRight){
				dx = 0;
				xtemp = (curCol + 1) * tileSize - cwidth / 2;
			}
			else{
				xtemp += dx;
			}
		}
		if(!falling){
			calcCorners(x, ydest + 1);
			if(!botLeft && ! botRight){
				falling = true;
			}
		}
	}
	
	
	public void calcCorners(double x, double y){
	    //collision box
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int botTile = (int)(y + cheight / 2 - 1) / tileSize;
		
		if(topTile < 0 || botTile >= tileMap.getNumRows() || leftTile < 0 || rightTile >= tileMap.getNumCols()){
			topLeft = topRight = botLeft = botRight = false;
			return;
		}
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(botTile, leftTile);
		int br = tileMap.getType(botTile, rightTile);
		
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		botLeft = bl == Tile.BLOCKED;
		botRight = br == Tile.BLOCKED;
	}
	
	public int getx(){return (int)x;}
	
	public int gety(){return (int)y;} 
	
	public int getWidth(){return width;}
	
	public int getHeight(){return height;}
	
	public int getCWidth(){return cwidth;}
	
	public int getCHeight(){return cheight;}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
    //movement speed
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition(){
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b){left = b;}
	
	public void setRight(boolean b){right = b;}
	
	public void setUp(boolean b){up = b;}
	
	public void setDown(boolean b){down = b;}
	
	public void setJumping(boolean b){jumping = b;}
	
	public boolean notOnScreen(){
		return x + xmap + width < 0 || 
				x + xmap - width > GamePanel.WIDTH ||
				y + ymap + height < 0 ||
				y + ymap -height > GamePanel.HEIGHT;
	}
	//sets the player's tilemap to the new tilemap. Used when changing levels
	public void setObjectOnTileMap(TileMap tm){
		tileMap = tm;
		tileSize = tm.getTileSize();}
}
