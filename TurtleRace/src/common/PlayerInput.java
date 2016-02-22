package common;

import java.util.Scanner;

public class PlayerInput implements Runnable{

	private boolean running = false;
	private Scanner inputDevice = null;
	private common.Character character = null;
	
	public PlayerInput(Scanner keyboard, common.Character _character) {
		// TODO Auto-generated constructor stub
		inputDevice = keyboard;
		character = _character;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(running){
			
			if(inputDevice.equals("W")){
				//Player wants to jump
				character.Jump();
			}
			else if(inputDevice.equals("A")){
				//Moving backwards
				character.Backwards();
			}
			else if(inputDevice.equals("D")){
				//Moving forwards
				character.Forwards();
			}
		}
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
