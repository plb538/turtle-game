package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import Main.GamePanel;
import TileMap.Background;

import java.net.*;

public class SetupState extends GameState{
	
	private Background bg;
	
	private int port = 43971;
	
	private String[] options = {"Apply Changes", "Return"};
	private int curOption = 0;
	private String title = "A Tale of Two Turtles";
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public SetupState(GameStateManager gsm){
		this.gsm = gsm;
		
		try{
			//bg = new Background("/Backgrounds/testBG.png", 1);
			bg = new Background("/Backgrounds/gears.png", 1);
			bg.setVector(0, 0); //determines if background is moving
			
			titleColor = new Color(255, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 56);
			font = new Font("Arial", Font.PLAIN, 26);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void init(){
		
		gsm.modeMultiplayer = true;
		
		int hostInput = JOptionPane.YES_NO_OPTION;
		
		int hostResult = JOptionPane.showConfirmDialog(null, "Are you the host?", "", hostInput);
		
		if(hostResult == JOptionPane.YES_OPTION){
			gsm.isHost = true;
			//startServerSocket
			try{
				gsm.hostSocket = new ServerSocket(port);
			} catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				gsm.clientSocket = gsm.hostSocket.accept();
				System.out.println("Connected");
			} catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 try{
				PrintWriter out = new PrintWriter(gsm.clientSocket.getOutputStream(), true);
			} catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			gsm.isHost = false;
			String ipAddr = JOptionPane.showInputDialog(this, "Enter host IP (x.x.x.x)");
			try{
				gsm.clientSocket = new Socket(ipAddr,port);
			} catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		

		try{
			gsm.outToServer = new ObjectOutputStream(gsm.clientSocket.getOutputStream());
			gsm.inFromServer = new ObjectInputStream(gsm.clientSocket.getInputStream());
		}catch(Throwable e){
			e.printStackTrace();
		};
		
		gsm.setState(gsm.LEVELSTATE);


	}

	@Override
	public void update(){
		bg.update();
	}

	@Override
	public void draw(Graphics2D g){
		//draw background
		bg.draw(g);
		
		//draw title
		
		
	}
	//key press options for menu screen
	@Override
	public void keyPressed(int k){
		System.exit(0);
	}

	@Override
	public void keyReleased(int k){}

}


