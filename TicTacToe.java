import java.util.Scanner;

/**
 * TicTacToe
 * A console version of the classic game
 * @author Randy Layne
 */
public class TicTacToe {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    System.out.println("Welcome to Tic-Tac-Toe");

    // Initialize board and players  
    Board board = new Board();
    Players players = GameConsole.getPlayers(input);

    // Display initial game board
    GameConsole.clearScreen();
    GameConsole.printBoard(board);

    // Loop taking turns until user quits
    boolean quit = false;
    do {
      // Check if current player is human
      if (players.players[players.currentPlayer()].isHuman()) {
        // Take turn and determine if user quit
        quit = GameConsole.takeTurn(board, players, input);
      } else {
        // Take computer turn
        AI.takeTurn(board, players);
      }

      // Display the game board in current state
      GameConsole.clearScreen();
      GameConsole.printBoard(board);

      // If player won, exit;
      if (GameLogic.checkWin(board, players.currentPlayer())) {
        System.out.println("Player " + players.getCurrentName() + " is the winner!");
        System.exit(0);
      }
      // If there is a tie, exit
      if(GameLogic.checkTie(board)) {
        System.out.println("There was a tie!");
        System.exit(1);
      }

      // Switch player turns
      players.flip();

    } while (!quit);

    // Quit the game
    System.out.println("Goodbye!");

  }
}