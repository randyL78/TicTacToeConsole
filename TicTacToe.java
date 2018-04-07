import java.util.Scanner;

/**
 * TicTacToe
 * A console version of the classic game
 * @author Randy Layne
 */
public class TicTacToe {
  public static void main(String[] args) {
    // Initialize board and players  
    Board board = new Board();
    Players players = new Players(new HumanPlayer(), new ComputerPlayer());

    Scanner input = new Scanner(System.in);
    // Display initial game board
    GameConsole.clearScreen();
    GameConsole.printBoard(board);

    // Loop taking turns until user quits
    boolean quit = false;
    do {
      // Take turn and determine if user quit
      quit = GameConsole.takeTurn(board, players, input);

      // Display the game board in current state
      GameConsole.clearScreen();
      GameConsole.printBoard(board);

      // If player won, exit;
      if (GameLogic.checkWin(board, players.currentPlayer())) 
        quit = true;

      // Switch player turns
      players.flip();

    } while (!quit);

    // Quit the game
    System.out.println("Goodbye!");

  }
}