package submissions;
import checkers.Evaluator;
public class RandomA implements Evaluator {
	
	@Override
	public String getName() {
		return "Random A";
	}

	@Override
	public int getSection() {
		return 1;
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