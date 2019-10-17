package checkers;
import java.util.*;

public class Play {
	
	public static String [] classTeams = {"RandomA","RandomB"}; // List your class names here
	public static List<String> teams = new ArrayList<>(Arrays.asList(classTeams));
	
	public static void main(String[] args) {
	    // change these first three ints if you want
	    // depth must be an even number
	    // totalMoves is how long it plays until a tie is declared
	    // totalGames must be divisible by 4
	    int depth=8, totalMoves=150, totalGames=4, win1=0, win2=0;
	    boolean display=false;  // display the board after every move
		try {
			String t1=teams.get(0);
			String t2=teams.get(1);

			Evaluator[] evals = getEvaluators(t1, t2);
			
			Evaluator t1eval = evals[0], t2eval = evals[1];
	        String player1=t1eval.getName();
	        String player2=t2eval.getName();
	                
	        /* play the game */
	        System.out.println(player1+" Black Moves First");	
	        for (int i=0; i<(totalGames/4); i++) {
	        	Board b = new Board();
	        	Game g = new Game(b, depth, depth, display, t1eval, t2eval);
	        	int counter=0;
	        	while(counter<totalMoves) {
	        		if (b.end_game(CheckersConstants.BLACK)) {
	        			System.out.print(player2+" Win  ");	
	        			win2++;
	        			break;
	        		}
	        		g.comp_move(CheckersConstants.BLACK);
	        		counter++;
	        		if (display) System.out.println(b);	
	        
	        		if (b.end_game(CheckersConstants.WHITE)) {
	        			System.out.print(player1+" Win  ");	
	        			win1++;
	        			break;
	        		}
	        		g.comp_move(CheckersConstants.WHITE);		
	        		counter++;
	        		if (display) System.out.println(b);		
	        	}
	        	if (counter==totalMoves) 
	        		System.out.print("Tie:"+player1+"("+b.checkerCount(CheckersConstants.BLACK)+")"+player2+"("+b.checkerCount(CheckersConstants.WHITE)+")  "); 
	        }

       		System.out.println("\n"+player2+" Black Moves First");	
       		for (int i=0; i<(totalGames/4); i++) {
       			Board b = new Board();
       			Game g = new Game(b, depth, depth, display, t2eval, t1eval);
       			int counter=0;
       			while(counter<totalMoves) {
       				if (b.end_game(CheckersConstants.BLACK)) {
       					System.out.print(player1+" Win  ");	
       					win1++;
       					break;
       				}
       				g.comp_move(CheckersConstants.BLACK);
       				counter++;
       				if (display) System.out.println(b);	
       		
       				if (b.end_game(CheckersConstants.WHITE)) {
       					System.out.print(player2+" Win  ");	
       					win2++;
       					break;
       				}
       				g.comp_move(CheckersConstants.WHITE);		
       				counter++;
       				if (display) System.out.println(b);		
       			}
       			if (counter==totalMoves) 
       				System.out.print("Tie:"+player2+"("+b.checkerCount(CheckersConstants.BLACK)+")"+player1+"("+b.checkerCount(CheckersConstants.WHITE)+")  "); 
       		}
       		
       		System.out.println("\nHALFTIME: "+player1+"="+win1+"   "+player2+"="+win2);	
       		System.out.println(player1+" White Moves First");
     		for (int i=0; i<(totalGames/4); i++) {
       			Board b = new Board();
       			Game g = new Game(b, depth, depth, display, t2eval, t1eval);
       			int counter=0;
       			while(counter<totalMoves) {
      				if (b.end_game(CheckersConstants.WHITE)) {
       					System.out.print(player2+" Win  ");	
       					win2++;
       					break;
       				}
       				g.comp_move(CheckersConstants.WHITE);		
       				counter++;
       				if (display) System.out.println(b);		
       				
      				if (b.end_game(CheckersConstants.BLACK)) {
       					System.out.print(player1+" Win  ");	
       					win1++;
       					break;
       				}
       				g.comp_move(CheckersConstants.BLACK);
       				counter++;
       				if (display) System.out.println(b);	
       			}
       			if (counter==totalMoves) 
       				System.out.print("Tie:"+player1+"("+b.checkerCount(CheckersConstants.WHITE)+")"+player2+"("+b.checkerCount(CheckersConstants.BLACK)+")  "); 				
       		}
	        		
       		System.out.println("\n"+player2+" White Moves First");
       		for (int i=0; i<(totalGames/4); i++) {
       			Board b = new Board();
       			Game g = new Game(b, depth, depth, display, t1eval, t2eval);
       			int counter=0;
       			while(counter<totalMoves) {
       				if (b.end_game(CheckersConstants.WHITE)) {
       					System.out.print(player1+" Win  ");	
       					win1++;
       					break;
       				}
       				g.comp_move(CheckersConstants.WHITE);		
       				counter++;
       				if (display) System.out.println(b);		
       				
       				if (b.end_game(CheckersConstants.BLACK)) {
       					System.out.print(player2+" Win  ");	
       					win2++;
       					break;
       				}
       				g.comp_move(CheckersConstants.BLACK);
       				counter++;
       				if (display) System.out.println(b);	
       			}
      			if (counter==totalMoves) 
       				System.out.print("Tie:"+player2+"("+b.checkerCount(CheckersConstants.WHITE)+")"+player1+"("+b.checkerCount(CheckersConstants.BLACK)+")  "); 				
       		}
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
}