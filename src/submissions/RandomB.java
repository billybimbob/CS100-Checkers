package submissions;
import checkers.Evaluator;

public class RandomB implements Evaluator {

	@Override
	public String getName() {
		return "Random B";
	}
	@Override
	public int getSection() {
		return 1;
	}

	@Override
	public int evaluateWhite(char [][] position) { 
		return (int)(Math.random()*100)-50;
	}

	@Override
	public int evaluateBlack(char [][] position){
		return (int)(Math.random()*100-50);
	}}