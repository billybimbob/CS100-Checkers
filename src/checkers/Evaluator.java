package checkers;

interface Evaluator {

    /**
     * changes the color the Evaluator is playing as
     * @param color the new color
     */
    void setColor(int color); //should only be called in Game class

    /**
     * gets the team name of Evaluator implementation
     * @return the team name as a string
     */
    String getName();

    /**
     * the lab section of the student(s)
     * @return the lab number
     */
    int getSection();

    /**
     * Analyzes the board and gives a score to the board;
     * <p> A higher value means the board is more in favor
     * for the Evaluator's color. A lower value means
     * the board is not in favor for the Evaluator's color. </p>
     * @param board the board being analyzed; close to row 0 is Evaluator's
     * side of the board, while close to row 9 is the opponenent's
     * @return the score of the board
     */
    int evaluateBoard (char[][] board);

}