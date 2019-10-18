package submissions;
import checkers.AbstractEvaluator;

// Student skeleton
public class Name extends AbstractEvaluator {
	
    /**
     * have values
     * ownChecker: own regular checker pieces
     * ownKing:    own king checker pieces
     * oppChecker: opponent's regular checker pieces
     * oppKing:    opponent's king checker pieces
     */

    public Name() {
        //change the values
        super.name = "Name"; //set equal to your name, first and last
        super.section = -1; //set equal to lab section
	}

	@Override
	public int evaluateBoard(char [][] position) { 
        
        //change the code here
        
        return 1;
	}

}