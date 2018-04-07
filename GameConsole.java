import java.util.Scanner;

/**
 * GameConsole
 * Handles methods related specifically to console parts of the TicTacToe game
 * @author Randy Layne
 */
public class GameConsole {
  /* ============== fields ================ */
  private static final String SYMBOL1 = "X";
  private static final String SYMBOL2 = "O";

  /* ============= methods ================ */
  /** 
   * handle a user input turn 
   * @param board the current game board
   * @param currentPlayer the player whose turn it is
   * @return whether the user quit or not
   */
  public static boolean takeTurn(Board board, Players players, Scanner input) {
    // Prompt user to enter a location
    System.out.printf("Player %s, enter a row number(1-3) and a column number(1-3)%n", players.getCurrentName());
    System.out.print("Or \"Q\" to quit: ");

    boolean turnComplete = false;

    // Loop until a valid entry has been made
    do {
      // Wrap in a try block in case something other than a number is entered
      try {
        String inputString = input.next();
        if (inputString.toLowerCase().equals("q"))
          return true;

        // Convert first entry to row number
        int row = Integer.parseInt(inputString) - 1;

        inputString = input.next();
        if (inputString.toLowerCase().equals("q"))
          return true;

        // convert second entry to column number
        int col = Integer.parseInt(inputString) - 1;

        if (board.isEmptyAt(row, col)) {
          board.setOwner(players.currentPlayer() , row, col);
          // exit the loop
          turnComplete = true;
        } else 
          System.out.println("Sorry, that square is taken, please choose an empty square");



      } catch (java.lang.NumberFormatException ex) {
        System.out.println("Please enter only numbers or \"Q\"");
      } catch (ArrayIndexOutOfBoundsException ex) {
        System.out.println("Please keep numbers between 1 and 3");
      }
    } while (!turnComplete);
    return false;
  }

  /** display a representation of the board to the console */
  public static void printBoard(Board board) {
    System.out.print("\033[H\033[2J");    
    printRow(board, 0);
    System.out.println("-----------");
    printRow(board, 1);
    System.out.println("-----------");
    printRow(board, 2);
  }

  /** display a single row of the board in the console */
  private static void printRow(Board board, int row) {
    System.out.printf(" %s | %s | %s %n",
    getSymbol(board, row, 0),
    getSymbol(board, row, 1),
    getSymbol(board, row, 2) );
  }

  /** @return the Symbol at a given board square */
  private static String getSymbol(Board board, int row, int col) {
    if (!board.isEmptyAt(row, col))
    return (board.getOwner(row, col) == 0) ? SYMBOL1 : SYMBOL2;
  else
    return " ";
  }
}