import java.util.Scanner;
/**
 * Tic-Tac-Toe: Two-player console, non-graphics
 * @author relkharboutly
 * @date 1/5/2017
 */
public class TTTConsole  {
                                                     
      public static Scanner in = new Scanner(System.in); // the input Scanner
      
 
   public static TicTacToe TTTboard = new TicTacToe();
   /** The entry main method (the program starts here) */
   public static void main(String[] args) {
      
	   int currentState = TicTacToe.PLAYING;
	   String userInput = "";
	   //game loop
	   do {
         TTTboard.printBoard();
         // Print message if game-over
         currentState = TTTboard.checkForWinner();
         /**
          * get player input here and call setMove(). user should input a number between 0-8
          */
         if (currentState == ITicTacToe.PLAYING) {
         userInput = in.nextLine();
         int playerMove = -1;
         playerMove = Integer.parseInt(userInput);
         while (playerMove > 8 || playerMove < 0 || !TTTboard.isEmpty(playerMove)) {
        	 System.out.println("Please enter a valid number bewteen 0-8, corresponding to the places on the board.");
        	 userInput = in.nextLine();
        	 if (userInput.equals("q"))
        		 break;
        	 playerMove = Integer.parseInt(userInput);
         }
         
         TTTboard.setMove(ITicTacToe.CROSS, playerMove);
         TTTboard.printBoard();
         }
         
         if (currentState == ITicTacToe.PLAYING) {
        	 System.out.println("Computers Turnnn!");
        	 TTTboard.setMove(ITicTacToe.NOUGHT, TTTboard.getComputerMove());
        	
         }
         
         
         if (currentState == ITicTacToe.CROSS_WON) {
            System.out.println("'X' won! Bye!");
         } else if (currentState == ITicTacToe.NOUGHT_WON) {
            System.out.println("'O' won! Bye!");
         } else if (currentState == ITicTacToe.TIE) {
            System.out.println("It's a TIE! Bye!");
         }
         //user can break the loop. remove this line when you finish implementation.         
      } while ((currentState == ITicTacToe.PLAYING) && (!userInput.equals("q"))); // repeat if not game-over
   }
 
     
}