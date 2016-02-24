package Main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameState.GameStateManager;
import common.Character;
import graphicalElements.DrawnProgress;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	//panel size
	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;
	public static final int SCALE = 2; //dont see need for this in videos yet
	
	//game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/FPS;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game state manager
	private GameStateManager gsm;
	private levelElements.Level myLevel;
	private Character player1;
	
	private DrawnProgress dp;
	
	public GamePanel(levelElements.Level _myLevel, Character _player1){
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); //sizes the window
		setFocusable(true);
		requestFocus();
		myLevel = _myLevel;
		player1 = _player1;
	}

	@Override
	public void run(){		
		init();
		long start, elapsed, wait;
		
		while(running){
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait < 0) wait = 5;
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void update(){
		if(gsm.getState() == gsm.LEVELSTATE){
			add(dp);
		}
		gsm.update();
	}
	
	private void draw(){
		gsm.draw(g);
	}
	
	private void drawToScreen(){
		Graphics2D g2 = (Graphics2D)getGraphics();
		g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}
	
	private void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		running = true;
		gsm = new GameStateManager(myLevel, player1);
		
		dp = new DrawnProgress();
	}
	
	@Override
	public void keyPressed(KeyEvent key){
		gsm.keyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key){
		gsm.keyReleased(key.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent key){}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

}
