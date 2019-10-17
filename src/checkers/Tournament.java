package checkers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tournament {
	public static String [] classTeams = {"TeamA","TeamB","TeamC","TeamD"};
	public static List<String> teams = new ArrayList<>(Arrays.asList(classTeams));
	public static int[][] results = new int[teams.size()][teams.size()];
	
	public static void main(String[] args) {
		// depth must be an even number, totalGames must be divisible by 4
		int depth=8, totalMoves=150, totalGames=100, win1=0, win2=0;
		boolean display=false;
		try {
			for (int ii=0; ii<teams.size(); ii++) {
				for (int jj=ii+1; jj<teams.size(); jj++) {
					String t1=teams.get(ii);
					String t2=teams.get(jj);
					win1=0;
					win2=0;

					Evaluator[] evals = Play.getEvaluators(t1, t2);
					
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
	        		results[ii][jj]=win1;
	        		results[jj][ii]=win2;
	        		
				}
			}
			
			for (int ii=0; ii<teams.size(); ii++) {
				int rowTotal=0;
				String t1=teams.get(ii);
				for (int jj=0; jj<teams.size(); jj++) {
					rowTotal=rowTotal+results[ii][jj];
					System.out.print(results[ii][jj]+"\t");
				}
				System.out.print(t1+" totalWins="+rowTotal+"\n");
			}
		} catch (ReflectiveOperationException e) {
	            e.printStackTrace();
		}
	}
}