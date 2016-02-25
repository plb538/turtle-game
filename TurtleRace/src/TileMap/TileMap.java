package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap{

	//position
	private double x, y;
	
	//bounds
	private int xmin, ymin, xmax, ymax;
	
	//helps smoothen sprite movements
	private double tween;
	
	//map
	private int[][] map;
	private int tileSize;
	private int numRows, numCols;
	private int width, height;
	
	//tile set
	//private ArrayList<BufferedImage> tileset = new ArrayList<BufferedImage>();
	private BufferedImage[] tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	private int totalNumTiles;
	
	//drawing
	private int rowOffset, colOffset;
	private int numRowsToDraw, numColsToDraw;
	
	public TileMap(int tileSize){
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 5;
		numColsToDraw = GamePanel.WIDTH / tileSize + 5;
		tween = 0.7;
		//System.out.println(tileSize + " " + numRowsToDraw +" " + numColsToDraw);
	}
	
	public void loadTiles(String directory, ArrayList<String> listTiles){
		try{
			tileset = new BufferedImage[listTiles.size()];
			String path;
			for(int i = 0; i < listTiles.size() ; i++){
				try{
					path = directory + "/" + listTiles.get(i) +".png";
					//System.out.println(path);
					//tileset.add(ImageIO.read(getClass().getResourceAsStream(path)));
					tileset[i] = ImageIO.read(getClass().getResourceAsStream(path));
				}catch(Throwable e){
					System.out.println(e);
				}
			}
			//Number of different kinds of tiles
			totalNumTiles = tileset.length;
			
			//System.out.println(totalNumTiles);
			numTilesAcross = totalNumTiles;
			
			tiles = new Tile[1][numTilesAcross];
			
			for(int col = 0; col < numTilesAcross; col++){
				if(col == 0){
					tiles[0][0] = new Tile(tileset[col], Tile.NORMAL); 
				}
				else{
					tiles[0][col] = new Tile(tileset[col], Tile.BLOCKED);
				}	
			}
			
			/*
			numTilesAcross = tileset.get(0).getWidth() / tileSize;
			System.out.println(numTilesAcross);
			BufferedImage subImage;
			tiles = new Tile[2][numTilesAcross];
			
			for(int col = 0; col < numTilesAcross; col++){
				subImage = tileset.getSubimage(col*tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subImage, Tile.NORMAL);
				subImage = tileset.getSubimage(col*tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
			}
			*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String path){
		try{
			InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols*tileSize;
			height = numRows*tileSize;
			
			//System.out.println(width + " " + height);
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+"; //white space
			
			for(int row = 0; row < numRows; row++){
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++){
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getTileSize(){return tileSize;}
	
	public int getx(){return (int)x;}
	
	public int gety(){return (int)y;}
	
	public int getWidth(){return width;}
	
	public int getHeight(){return height;}
	
	public int getNumRows(){return numRows;}
	
	public int getNumCols(){return numCols;}
	
	public int getType(int row, int col){
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y){
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
	}
	
	private void fixBounds(){
		if(x < xmin) x = xmin;
		if(x > xmax) x = xmax;
		if(y < ymin) y = ymin;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g){
		
		for(int row = 0; row < numRowsToDraw; row++){
			if(row >= numRows) break;
			for(int col = 0; col < numColsToDraw; col++){
				if(col >= numCols) break;
				if(map[row][col] == 0) continue;
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col*tileSize, (int)y + row*tileSize, tileSize, tileSize, null);

			}
			
		}
		
		
		/*
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++){
			if(row >= numRows) break;
			for(int col = colOffset; col < colOffset + numColsToDraw; col++){
				if(col >= numCols) break;
				if(map[row][col] == 0) continue;
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col*tileSize, (int)y + row*tileSize, null);
			}
		}
		*/
	}
}
