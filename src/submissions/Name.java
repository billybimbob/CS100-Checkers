package submissions;
import checkers.AbstractEvaluator;

/**
 * student skeleton
 * change Class name to first initial, last name
 * of one of the students in the team
 * e.g. student Bob Smith -> BSmith
 */
public class Name extends AbstractEvaluator {

    /*
     * Helper methods:
     * Each method listed here take in one spot on the board (a char), and
     * returns a boolean (true or false) based on if the info checking is
     * valid for that specific tile
     * 
     * methodName (parameters):  information checking
     * ownChecker (char tile):   own regular checker pieces
     * ownKing    (char tile):   own king checker pieces
     * oppChecker (char tile):   opponent's regular checker pieces
     * oppKing    (char tile):   opponent's king checker pieces
     */

    public Name() { //also change constructor name

        //change theses values
        super.name = "Name"; //set equal to your team name
        super.section = -1;  //set equal to lab section
    }

    /*
     * Board layout
     * Rows close to row 0 is Evaluator's
     * side of the board, while close to row 9 is the opponenent's
     */

    @Override
    public int evaluateBoard(char [][] board) {

        //example of helper function use
        if (ownChecker(board[0][0]) == true) {
             //spot (0, 0) of the board has evaluator's color if true
             //will want to modify the score based on boolean result
        }

        return 1; //return will be your final board score 
    }

}