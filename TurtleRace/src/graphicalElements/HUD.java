package graphicalElements;

public class HUD extends GraphicalElement{

	private PlayerGUI p1Graphics = null;
	private PlayerGUI p2Graphics = null;
	private DrawnLevel levelGraphics = null;
	
	private playerElements.Player p1 = null;
	private playerElements.Player p2 = null;
	private levelElements.Level myLevel = null;
	
	public HUD(playerElements.Player _p1, playerElements.Player _p2, levelElements.Level _myLevel){
		p1 = _p1;
		p2 = _p2;
		myLevel = _myLevel;
	}
	
	
	
}
