package submissions;
import checkers.AbstractEvaluator;

/**
 * student skeleton
 * change Class name to first initial, last name
 * e.g. student Bob Smith -> BSmith
 */
public class Name extends AbstractEvaluator {

    /*
     * have methods
     * ownChecker(char): own regular checker pieces
     * ownKing(char):    own king checker pieces
     * oppChecker(char): opponent's regular checker pieces
     * oppKing(char):    opponent's king checker pieces
     */

    public Name() {
        //change the values
        super.name = "Name"; //set equal to your name, first and last
        super.section = -1;  //set equal to lab section
    }

    @Override
    public int evaluateBoard(char [][] board) {

        //change the code here

        return 1; //return will be your board score 
    }

}