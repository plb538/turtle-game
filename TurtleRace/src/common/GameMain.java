package common;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import graphics.SpriteSheet;

public class GameMain implements Runnable{

	private static final long serialVersionUID = 1L;
	private static int width = 160;
	private static int height = width / 12 * 9;
	private static int scale = 5;
	private static String title = "The Adventures of Theodore and Norvell";
	private boolean running = false;
	private int tickCount = 0;
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	//private SpriteSheet spriteSheet = new SpriteSheet("DefaultSpriteSheet.png");
	
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}
	public Game(){
		frame = new JFrame(title);
		canvas = new Canvas();
		canvas.setMinimumSize(new Dimension(width*scale, height*scale));
		canvas.setMaximumSize(new Dimension(width*scale, height*scale));
		canvas.setPreferredSize(new Dimension(width*scale, height*scale));
		frame.setSize(new Dimension(width*scale, height*scale));
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		//frame.pack();
	}
	@Override
	public void run(){
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nsPerTick = 1000000000/60; //Nano-seconds per update
		double delta = 0; //How many nano-seconds have gone by
		int ticks = 0; //number of updates
		int frames = 0; //number of frames
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			boolean framesRendered = false;
			
			while(delta >= 1){
				ticks++;
				tick();
				delta--;
				framesRendered = true;
			}
			if(framesRendered){
				frames++;
				render();
			}
			//if one second has gone by, reset frames and ticks
			if(System.currentTimeMillis() - timer >= 1000){
				timer += 1000;
				System.out.println("frame" + frames + " " + "ticks" + ticks);
				frames = 0;
				ticks = 0;
			}
			
		}
	}
	public void tick(){
		tickCount++;
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = i + tickCount;
		}
	}
	public void render(){
		BufferStrategy bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		//g.setColor(Color.BLUE);
		//g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	public synchronized void start(){
		running = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		running = false;
	}
}
