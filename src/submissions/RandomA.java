package submissions;
import checkers.*;
public class RandomA implements Evaluator {
	@Override
	public String getName() {
		return "Random A";
	}

	@Override
	public int evaluateWhite(char [][] position) { 
		return (int)(Math.random()*10);
	}

	@Override
	public int evaluateBlack(char [][] position){
		return (int)(Math.random()*10);
	}
}