package submissions;
import checkers.AbstractEvaluator;

public class RandomB extends AbstractEvaluator {
    
    public RandomB() {
        super.name = "Random B";
        super.section = 1;
    }

    @Override
    public int evaluateBoard(char [][] board) { 
        return (int)(Math.random()*100-50);
    }

}