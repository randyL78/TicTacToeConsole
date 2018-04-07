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

    boolean quit = false;

    Scanner input = new Scanner(System.in);
    do {
      GameConsole.printBoard(board);
      quit = GameConsole.takeTurn(board, players, input);

      players.flip();

    } while (!quit);

    System.out.println("Goodbye!");

  }
}