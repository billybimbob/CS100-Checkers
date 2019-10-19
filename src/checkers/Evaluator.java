package checkers;

interface Evaluator {

    /**
     * changes the color the Evaluator is playing as
     * @param color the new color
     */
    void setColor(int color); //should only be called in Game class

    String getName();

    int getSection();

    /**
     * analyzes the board and gives a score to the board;
     * a higher value means the board is more in favor
     * for the Evaluator's color; a lower value means
     * the board is not in favor for the Evaluator's color
     * @param board the board being analyzed
     * @return the score of the board
     */
    int evaluateBoard (char[][] board);

}