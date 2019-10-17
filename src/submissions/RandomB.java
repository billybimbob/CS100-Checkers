package submissions;
import checkers.*;
public class RandomB implements Evaluator {
	@Override
	public String getName() {
		return "Random B";
	}

	@Override
	public int evaluateWhite(char [][] position) { 
		return (int)(Math.random()*100)-50;
	}

	@Override
	public int evaluateBlack(char [][] position){
		return (int)(Math.random()*100-50);
	}}