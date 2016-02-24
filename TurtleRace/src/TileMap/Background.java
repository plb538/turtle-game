package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Background{
	
	private BufferedImage image;
	
	private double x, y, dx, dy;
	private double moveScale;
	
	public Background(String path, double ms){
		try{
			image = ImageIO.read(getClass().getResourceAsStream(path));
			moveScale = ms;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y){
		this.x = (x * moveScale) % GamePanel.WIDTH * GamePanel.SCALE;
		this.y = (y * moveScale) % GamePanel.HEIGHT* GamePanel.SCALE;
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g){
		g.drawImage(image, (int)x, (int)y, GamePanel.WIDTH* GamePanel.SCALE, GamePanel.HEIGHT* GamePanel.SCALE, null);
		if(x < 0){
			g.drawImage(image, (int)x + GamePanel.WIDTH* GamePanel.SCALE, (int)y, GamePanel.WIDTH* GamePanel.SCALE, GamePanel.HEIGHT* GamePanel.SCALE, null);
		}
		if(x > 0){
			g.drawImage(image, (int)x - GamePanel.WIDTH* GamePanel.SCALE, (int)y, GamePanel.WIDTH* GamePanel.SCALE, GamePanel.HEIGHT* GamePanel.SCALE, null);
		}
	}
}
