package networking;

import GameState.GameStateManager;
import Main.Game;

public class SendThread extends MultiplayerThread{
	
	public SendThread(GameStateManager _gsm){
			super(_gsm);
	}
	
	@Override
	public void update(){
		//System.out.println("Game is in multiplayer mode");
		if(gsm != null){
			//System.out.println("1");
			if(gsm.modeMultiplayer && gsm.connected){
				//System.out.println("2");
				//System.out.println("Game is in multiplayer mode");
				InformationPacket myPacket = new InformationPacket(Game.p1, gsm.getState(), gsm.gameStates.get(gsm.getState()).monkeys);
				try{
					gsm.outToServer.writeObject(myPacket);
				} catch(Throwable e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		}
	}
}
