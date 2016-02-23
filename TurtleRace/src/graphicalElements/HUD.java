package graphicalElements;

import common.Character;

public class HUD extends GraphicalElement{

	private PlayerGUI p1Graphics = null;
	private PlayerGUI p2Graphics = null;

	
	private playerElements.Player p1 = null;
	private playerElements.Player p2 = null;

	
	public HUD(Character player, Character player2){
		p1Graphics = new PlayerGUI(p1);
		p2Graphics = new PlayerGUI(p2);
	}

}
