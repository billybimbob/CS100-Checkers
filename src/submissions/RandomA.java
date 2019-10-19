package submissions;
import checkers.AbstractEvaluator;

public class RandomA extends AbstractEvaluator {
    
    public RandomA() {
        super.name = "Random A";
        super.section = 1;
    }

    @Override
    public int evaluateBoard(char [][] position) { 
        return (int)(Math.random()*10);
    }

}