package graphicalElements;

public class PlayerGUI extends GraphicalElement{

	private DrawnHealth myHealth = null;
	private DrawnProgress myProgress = null;
	private DrawnWeapon myWeapon = null;
	private playerElements.Player myPlayer = null;
	
	public PlayerGUI(playerElements.Player _myPlayer){
		myPlayer = _myPlayer;		
	}
	
}
