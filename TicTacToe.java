import java.util.Scanner;

/**
 * TicTacToe
 * A console version of the classic game
 * @author Randy Layne
 */
public class TicTacToe {
  private static String symbol1 = "X";
  private static String symbol2 = "O";

  public static void main(String[] args) {
    // Initialize board and players
    Board board = new Board();
    Players players = new Players(new HumanPlayer(), new ComputerPlayer());

    boolean quit = false;

    Scanner input = new Scanner(System.in);
    do {
      printBoard(board);

      // Prompt user to enter a location
      System.out.printf("Player %s, enter a row number(1-3) and a column number(1-3)%n", players.getCurrentName());
      System.out.print("Or \"Q\" to quit: ");
      String inputString = input.next();
      // Wrap in a try block in case something other than a number is entered
      try {
        if (inputString.toLowerCase().equals("q"))
          break;

        // Convert first entry to row number
        int row = Integer.parseInt(inputString) - 1;

        inputString = input.next();
        if (inputString.toLowerCase().equals("q"))
          break;

        // convert second entry to column number
        int col = Integer.parseInt(inputString) - 1;

        board.setOwner(players.currentPlayer() , row, col);
        players.flip();
      } catch (java.lang.NumberFormatException ex) {
        System.out.println("Please enter only numbers or \"Q\"");
      }
    } while (!quit);

    System.out.println("Goodbye!");

  }

  private static void printBoard(Board board) {
    System.out.print("\033[H\033[2J");    
    printRow(board, 0);
    System.out.println("-----------");
    printRow(board, 1);
    System.out.println("-----------");
    printRow(board, 2);
  }

  private static void printRow(Board board, int row) {
    System.out.printf(" %s | %s | %s %n",
    getSymbol(board, row, 0),
    getSymbol(board, row, 1),
    getSymbol(board, row, 2) );
  }

  private static String getSymbol(Board board, int row, int col) {
    if (!board.isEmptyAt(row, col))
      return (board.getOwner(row, col) == 0) ? symbol1 : symbol2;
    else
      return " ";
  }
}