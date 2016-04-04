package Main;

//Debug printer
//When enabled some portions of the game would print to the console
//For development only
public class Printer{

	
	boolean active = false;
	//boolean active = true;
	
	public Printer(){
		
	}
	
	public void print(String str){
		
		if(active){
		System.out.println(str);
			}
		}
	
}
