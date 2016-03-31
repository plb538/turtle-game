package networking;

import GameState.GameStateManager;
import Main.Game;

public class MultiplayerThread implements Runnable{

	private GameStateManager gsm;
	private Thread thread;
	private boolean running;
	private int tickrate = 30;
	private long targetTime = 1000/tickrate;
	
	public MultiplayerThread(GameStateManager _gsm){
		super();
		gsm = _gsm;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	

		@Override
		public void run(){		
			long start, elapsed, wait;
			
			while(running){
				start = System.nanoTime();
				
				update();
				
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
		//System.out.println("Multiplayer Thread updating");
		if(gsm.modeMultiplayer){
			//System.out.println("Game is in multiplayer mode");
			InformationPacket myPacket = new InformationPacket(Game.p1, gsm.getState());
			
			try{
				gsm.outToServer.writeObject(myPacket);
			} catch(Throwable e1){
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			InformationPacket test = null;
			try{
			test = (InformationPacket)gsm.inFromServer.readObject();
			System.out.println(test.getx() + " " + test.gety());
			
			} catch(Throwable e){
			// TODO Auto-generated catch block
				e.printStackTrace();

			
			try{
				Game.p2.updateP2(test);
			}catch(Throwable e2){
				e2.printStackTrace();
				}
			}
		
		
		}
	}

}


