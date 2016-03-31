package networking;

import Entity.Player;
import GameState.GameStateManager;
import Main.Game;

public class MultiplayerThread implements Runnable{

	private GameStateManager gsm;
	private Thread thread;
	private boolean running;
	private int tickrate = 30;
	private long targetTime = 1000/tickrate;
	private Player p2;
	
	public MultiplayerThread(GameStateManager _gsm, Player _p2){
		super();
		gsm = _gsm;
		thread = new Thread(this);
		running = true;
		p2 = _p2;
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
			//System.out.println("PACKET||" + " OtherX:" + test.getx() + "|OtherY: " + test.gety() + "|OtherState:" + test.getstate());
			//System.out.println("UpdatingP2");
			Game.p2.updateP2(test);
			
			} catch(Throwable e){
			// TODO Auto-generated catch block
				e.printStackTrace();		
		
			}
		}
	}
}




