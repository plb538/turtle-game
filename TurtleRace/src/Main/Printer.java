package Main;

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
