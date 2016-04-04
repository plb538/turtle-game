package networking;

import GameState.GameStateManager;
import Main.Game;

public class ReadThread extends MultiplayerThread{

	public ReadThread(GameStateManager _gsm){
		super(_gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(){
		InformationPacket test = null;
		
		if(gsm != null){
			if(gsm.modeMultiplayer && gsm.connected){
				try{
					test = (InformationPacket)gsm.inFromServer.readObject();
					//System.out.println("PACKET||" + " OtherX:" + test.getx() + "|OtherY: " + test.gety() + "|OtherState:" + test.getstate());
					//System.out.println("UpdatingP2");
					Game.p2.updateP2(test);
					
					//System.out.println("MyStart:" + Game.p1.getStartTime() + "|MyCurrent" + System.nanoTime()+ "|PacketStart:" + test.getStartTime() + "|PacketCurrent:" + test.getTimeStamp());
					
					if(gsm.getState() == test.getstate()){
						gsm.gameStates.get(gsm.getState()).updateMonkeyPosP2(test); //.updateMonkeyPosP2(test);
					}

				} catch(Throwable e){
					// TODO Auto-generated catch block
					e.printStackTrace();		
				}
			}
		}
	}
	

	
}
