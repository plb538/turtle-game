package levelElements;

public abstract class Challenges {
	
	int width = 1280;
	int height = 720;
	int startDistance = 0;
	boolean finished = false;
	public boolean isFinished(){
		return finished;
	}
	public double progress(){
		return 0;
	}
}
