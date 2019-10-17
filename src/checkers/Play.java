package checkers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Play {
	
	public static String [] classTeams = {"RandomA","RandomB"}; // List your class names here
	public static List<String> teams = new ArrayList<>(Arrays.asList(classTeams));
	
	public static void main(String[] args) {
	    // change these first three ints if you want
	    // depth must be an even number
	    // totalMoves is how long it plays until a tie is declared
	    // totalGames must be divisible by 4
	    int depth=8, totalMoves=150, totalGames=4, win1=0, win2=0;
	    boolean display = false;  // display the board after every move
		try {
			String t1=teams.get(0);
			String t2=teams.get(1);

			Evaluator[] evals = getEvaluators(t1, t2);
			
			Evaluator t1eval = evals[0], t2eval = evals[1];
	        String player1=t1eval.getName();
	        String player2=t2eval.getName();
			
			int[] wins;
			int numRounds = totalGames/4;
			
	        /* play the game */
			System.out.println(player1 + " Black Moves First");
			wins = runGame(numRounds, totalMoves, depth, display, true, t1eval, t2eval);
			win1 += wins[0]; win2 += wins[1];

			System.out.println("\n"+player2 + " Black Moves First");
			wins = runGame(numRounds, totalMoves, depth, display, true, t2eval, t1eval);
			win1 += wins[1]; win2 += wins[0];
       		
			System.out.println("\nHALFTIME: "+player1+"="+win1+"   "+player2+"="+win2);

			System.out.println(player1 + " White Moves First");
			wins = runGame(numRounds, totalMoves, depth, display, false, t2eval, t1eval);
			win1 += wins[0]; win2 += wins[1];
			
			System.out.println("\n"+player2 + " White Moves First");
			wins = runGame(numRounds, totalMoves, depth, display, false, t1eval, t2eval);
			win1 += wins[1]; win2 += wins[0];

			System.out.println("\nFINAL SCORE: "+player1+"="+win1+"   "+player2+"="+win2);
			   
		} catch (ReflectiveOperationException e) {
	            e.printStackTrace();
		}
	}

	static Evaluator[] getEvaluators (String team1, String team2) throws ReflectiveOperationException {
		Evaluator t1eval = (Evaluator) Class.forName("submissions." + team1)
			.getConstructor().newInstance();
		Evaluator t2eval = (Evaluator) Class.forName("submissions." + team2)
			.getConstructor().newInstance();

		return new Evaluator[] {t1eval, t2eval};

	}

	/**
	 * @return first idx is player to go first
	 */
	static int[] runGame(int numGames, int maxMoves, int depth, boolean display, boolean blackFirst, Evaluator black, Evaluator white)
	{
		int win1 = 0, win2 = 0;
		int fstClr, sndClr;
		String player1, player2; //player1 is one who goes first

		if (blackFirst) {
			fstClr = CheckersConstants.BLACK;
			sndClr = CheckersConstants.WHITE;
			player1 = black.getName();
			player2 = white.getName();
		} else {
			fstClr = CheckersConstants.WHITE;
			sndClr = CheckersConstants.BLACK;
			player1 = white.getName();
			player2 = black.getName();
		}

		for (int i=0; i < numGames; i++) {
			Board b = new Board();
			Game g = new Game(b, depth, depth, display, black, white);
			int counter=0;
			
			while(counter < maxMoves) {
				if (b.end_game(fstClr)) {
					System.out.print(player1 + " Win  ");	
					win1++;
					break;
				}
				g.comp_move(fstClr);		
				counter++;
				if (display) System.out.println(b);		
				
				if (b.end_game(sndClr)) {
					System.out.print(player2 + " Win  ");	
					win2++;
					break;
				}
				g.comp_move(sndClr);
				counter++;
				if (display) System.out.println(b);	
			}
			if (counter==maxMoves) 
				System.out.print("Tie:" 
					+ player2 + "(" + b.checkerCount(sndClr) + ") " 
					+ player1 + "(" + b.checkerCount(fstClr) + ")  "); 				
		}

		return new int[] {win1, win2};
	}
}