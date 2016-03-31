package networking;
import GameState.GameStateManager;

public abstract class MultiplayerThread implements Runnable{

	protected GameStateManager gsm;
	protected Thread thread;
	protected boolean running;
	protected int tickrate = 60;
	protected long targetTime = 1000/tickrate;
	
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
	
	protected abstract void update();
}




