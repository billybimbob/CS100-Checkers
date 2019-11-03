package checkers;

import java.util.*;
import static checkers.CheckersConstants.*;

public class Board {
    private char [][] position;

    public Board( ) {
        position = new char[10][10];
        // illegal positions outside edges 
        for (int i=0;i<=9;i++) {
            position[0][i] = OUT;
            position[9][i] = OUT;
            position[i][0] = OUT;
            position[i][9] = OUT;
        }

        // illegal positions within board
        for (int i=1;i<=8;i+=2)
            for (int j=2;j<=8;j+=2) {
                position[i][j-1] = OUT;
                position[i+1][j] = OUT;
            }

        // initial checker positions
        for (int j=2;j<=8;j+=2) {
            position[1][j]   = WCHEC;	// white
            position[2][j-1] = WCHEC;	// white
            position[3][j]   = WCHEC;	// white
            position[4][j-1] = AVAIL;	// legal and empty positons
            position[5][j]   = AVAIL;	// legal and empty postions
            position[6][j-1] = BCHEC;	// black
            position[7][j]   = BCHEC;	// black
            position[8][j-1] = BCHEC;	// black
        }
    }
    
    public char [][] BoardCopy() {
        char [][] positionCopy = new char[10][10];
        for (int i=0;i<=9;i++) 
            for (int j=0;j<=9;j++) 
                positionCopy[i][i]=position[i][j];
        return positionCopy;
    }

    public List<Move> findMoves(int turn) {
        List<Move> allMoves = new ArrayList<Move>();
        boolean jumpExists=false;

        char check = turn==BLACK ? BCHEC : WCHEC;
        char king  = turn==BLACK ? BKING : WKING;
        for (int i=1; i<=8; i++){
            for (int j=1; j<=8; j++){
                if (position[i][j] == check || position[i][j] == king) {
                    List<Move> oneCheckerMoves = findMoves(i,j);
                    for (Move item: oneCheckerMoves) {
                        if (item.getJump()) jumpExists=true; 
                        allMoves.add(item);
                    }
                }
            }
        }
        // FORCED JUMP LOGIC, IF AT LEAST ONE JUMP AVAILABLE ONLY RETURN JUMP MOVES
        if (jumpExists) {
            Iterator<Move> itr = allMoves.iterator();
            while (itr.hasNext()) {
                Move m = (Move)itr.next();
                if (m.getJump()==false) itr.remove();
            }
        }
        return allMoves;
    }

    public List<Move> findMoves(int row, int col) {
        List<Move> oneCheckerMoves = new ArrayList<Move>();
        Move newMove;
        char piece=position[row][col];
        // for single checker moves
        if (piece == WCHEC || piece == BCHEC) {
            int k=0;
            // white up, black down
            if (piece == WCHEC) k = 1;
            else if (piece == BCHEC) k = -1;
            
            for (int i=0;i<2;i++) { // always 2 possible moves for each checker
                int j;
                if (i == 0) j = 1;
                else j = -1;

                // get the position moving to
                char check = position[row+k][col+j];

                // save non-jump move if space available
                if (check == AVAIL) {
                    boolean newMoveKing=false;
                    if (row+k==1 || row+k==8) newMoveKing=true;
                    newMove=new Move(row, col, row+k, col+j, false, newMoveKing, AVAIL, null); 
                    oneCheckerMoves.add(newMove);
                }
                // if jump then save the jump
                else if ( (piece == WCHEC && (check == BCHEC || check == BKING)) || 
                        (piece == BCHEC && (check == WCHEC || check == WKING)) ) {
                    if (row+2*k>=1 && row+2*k<=8 && col+2*j>=1 && col+2*j<=8 ) {
                        if (position[row+2*k][col+2*j] == AVAIL){
                            boolean newMoveKing=false;
                            if (row+2*k==1 || row+2*k==8) {
                                newMoveKing=true;
                            }
                            newMove=new Move(row, col, row+2*k, col+2*j, true, newMoveKing, check, null);
                            makeMove(newMove);  // should make king if applicable
                            List<Move> jumpMoves = findMoves(row+2*k, col+2*j);
                            boolean continuedJump=false;
                            if (!jumpMoves.isEmpty()) { 
                                Move moveToExtend = new Move(newMove);
                                for (Move continuedMove : jumpMoves){
                                    // only extend jumps
                                    if (continuedMove.getJump()) {
                                        moveToExtend.setNext(continuedMove);
                                        oneCheckerMoves.add(moveToExtend);
                                        continuedJump=true;
                                        moveToExtend = new Move(newMove);
                                    }
                                }
                            }
                            if (!continuedJump) {	// no continued jumps
                                oneCheckerMoves.add(newMove);
                            }
                            unmakeMove(newMove);	// should unmake king if applicable
                        }
                    }
                } // end of jump
            }	// end of two possible moves for each non-king checker
        }  // end of non-king checker moves

        // for single king checker moves
        if (piece == WKING || piece == BKING) {
            for (int i=0;i<4;i++) {
                // try all directions
                int j=0, k=0;
                switch(i) {
                    case 0: j=1; k=1; break;
                    case 1: j=1; k=-1; break;
                    case 2: j=-1; k=1; break;
                    case 3: j=-1; k=-1; break;
                }

                // get the position moving to
                char check = position[row+k][col+j];

                // save non-jump move if space available
                if (check == AVAIL) {
                    newMove=new Move(row, col, row+k, col+j, false, false, AVAIL, null); 
                    oneCheckerMoves.add(newMove);
                }
                // if jump then save the jump
                else if ( (piece == WKING && (check == BCHEC || check == BKING)) || 
                        (piece == BKING && (check == WCHEC || check == WKING)) ) {
                    if ((row+2*k)>=1 && (row+2*k)<=8 && (col+2*j)>=1 && (col+2*j)<=8 ) {
                        if (position[(row+2*k)][(col+2*j)] == AVAIL){
                            newMove=new Move(row, col, row+2*k, col+2*j, true, false, check, null);
                            makeMove(newMove);  
                            List<Move> jumpMoves = findMoves(row+2*k, col+2*j);
                            boolean continuedJump=false;
                            if (!jumpMoves.isEmpty()) {
                                Move moveToExtend = new Move(newMove);
                                for (Move continuedMove : jumpMoves){
                                    // only extend jumps
                                    if (continuedMove.getJump()) {
                                        moveToExtend.setNext(continuedMove);
                                        oneCheckerMoves.add(moveToExtend);
                                        moveToExtend = new Move(newMove);
                                        continuedJump=true;
                                    }
                                }
                            }
                            if (!continuedJump) {	// no continued jumps
                                oneCheckerMoves.add(newMove);
                            }
                            unmakeMove(newMove);
                        }
                    }
                } // end of jump
            }	// end of four possible moves for each king checker
        }  // end of king checker moves
        return oneCheckerMoves;
    }
    
    public void makeMove(Move m) {
        do {
            int fx=m.getFromX();
            int fy=m.getFromY();
            char piece = position[fx][fy];
            position[fx][fy] = AVAIL;

            int tx=m.getToX();
            int ty=m.getToY();
            int k=0, l=0;
            // left, right, down or up
            if (tx > fx) k = 1; else k = -1;
            if (ty > fy) l = 1; else l = -1;

            // clear the space if it was a jump
            if (m.getJump()) position[fx+k][fy+l] = AVAIL;

            // change to king
            if (piece == WCHEC && m.getMadeKing()) piece = WKING;
            if (piece == BCHEC && m.getMadeKing()) piece = BKING;
    
            // update the board with moved piece
            position[tx][ty] = piece;
            m=m.getNextMove();
            
        } while (m != null); 
    }	
    
    public void unmakeMove(Move m) {
        List<Move> moveList = new ArrayList<>();
        while (m != null) {
            moveList.add(m);
            m=m.getNextMove();
        }
        Collections.reverse(moveList);
        for (Move mm : moveList) {
            int fx=mm.getToX();
            int fy=mm.getToY();
            char piece = position[fx][fy];
            position[fx][fy] = AVAIL;

            int tx=mm.getFromX();
            int ty=mm.getFromY();
            int k=0, l=0;
            // left, right, down or up
            if (tx > fx) k = 1; else k = -1;
            if (ty > fy) l = 1; else l = -1;

            // if it was a jump put back the jumped piece
            if (mm.getJump()) position[fx+k][fy+l] = mm.getJumpedPiece();
    
            // change back to normal piece if it was a king
            if (piece == WKING && mm.getMadeKing()) piece = WCHEC;
            if (piece == BKING && mm.getMadeKing()) piece = BCHEC;

            // update the board with moved piece
            position[tx][ty] = piece;
        }
    }

    public int checkerCount(int who) { // WHITE=-1, BLACK=1
        int count=0;
        for (int i=1;i<=8;i++){
            for (int j=1;j<=8;j++){
                if (who==WHITE && (position[i][j]==WCHEC || position[i][j]==WKING))
                        count++;
                if (who==BLACK && (position[i][j]==BCHEC || position[i][j]==BKING))
                        count++;
            }
        }
        return count;
    }
    
    public String toString( ) {	
        String temp = "\t1\t2\t3\t4\t5\t6\t7\t8\n";
        for (int i=1;i<=8;i++){
            temp=temp+i+"\t";
            for (int j=1;j<=8;j++){
            // display coresponding figures
                switch (position[i][j]){
                    case WCHEC: temp=temp+"W\t"; break;
                    case WKING: temp=temp+"WK\t"; break;
                    case BCHEC: temp=temp+"B\t"; break;
                    case BKING: temp=temp+"BK\t"; break;
                    case AVAIL: temp=temp+".\t"; break;
                    case OUT:   temp=temp+"\t"; break;
                }
            }
            temp=temp+"\n";
        }
        return temp;
    }

    public boolean endGame(int player) {
        List<Move> data=findMoves(player);
        if (data.isEmpty()) return true;
        else return false;
    }
}