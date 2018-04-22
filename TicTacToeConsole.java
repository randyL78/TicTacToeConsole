import java.io.IOException;
import java.util.Scanner;

/**
 * TicTacToeConsole
 * A console version of the classic game
 * @author Randy Layne
 */
public class TicTacToeConsole {
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

      // If there is a winner, exit;
      if (GameLogic.checkWin(board, players.currentPlayer())) {
        
        if (players.players[players.currentPlayer()].isHuman()) {
          // If a human player won, congratulate them
          System.out.println("Player " + players.getCurrentName() + " is the winner!");

          // increment wins for current player
          ((HumanPlayer) players.players[players.currentPlayer()]).addWin();
          // show current player score
          System.out.println(((HumanPlayer) players.players[players.currentPlayer()]));

          if (players.players[players.oppositePlayer()].isHuman()) {
            // increment loss for other player if human
            ((HumanPlayer) players.players[players.oppositePlayer()]).addLoss();
            // show opposit player score
            System.out.println(((HumanPlayer) players.players[players.oppositePlayer()]));          
          }

        
        } else {
          // Otherwise display loss message
          System.out.println("Sorry, you lost!");  
            if (players.players[players.oppositePlayer()].isHuman()) {
              // increment loss for other player if human
              ((HumanPlayer) players.players[players.oppositePlayer()]).addLoss();
              // show opposit player score
              System.out.println(((HumanPlayer) players.players[players.oppositePlayer()]));          
            }
        }

        // ask users if they want to play again, if not quit the  game
        quit = !GameConsole.askToPlayAgain(input);
        board.clear();
        // Display initial game board
        GameConsole.clearScreen();
        GameConsole.printBoard(board);
        // Reset to first player's turn for new game
        if (!players.isFirstPlayerTurn())
          players.flip();
      } else if(GameLogic.checkTie(board)) {
        System.out.println("There was a tie!");
        // Adjust score stats for human players
        // Display scores for human players
        if (players.players[players.currentPlayer()].isHuman()) {
          // increment ties for current player
          ((HumanPlayer) players.players[players.currentPlayer()]).addTie();
          // show current player score
          System.out.println(((HumanPlayer) players.players[players.currentPlayer()]));
        }

        if (players.players[players.oppositePlayer()].isHuman()) {
          // increment loss for other player if human
          ((HumanPlayer) players.players[players.oppositePlayer()]).addTie();
          // show opposit player score
          System.out.println(((HumanPlayer) players.players[players.oppositePlayer()]));          
        }

        // ask users if they want to play again, if not quit the  game
        quit = !GameConsole.askToPlayAgain(input);
        board.clear();
        // Display initial game board
        GameConsole.clearScreen();
        GameConsole.printBoard(board);
        // Reset to first player's turn for new game
        if (!players.isFirstPlayerTurn())
          players.flip();
      } else {
        // Switch player turns
        players.flip();
      }



    } while (!quit);

    // Save players on exit
    try {
      if (players.players[0].isHuman()) {
        FileActions.savePlayer((HumanPlayer) players.players[0]);
      }
      if (players.players[1].isHuman()) {
        FileActions.savePlayer((HumanPlayer) players.players[1]);
      }
      
    } catch(IOException ex) {
      // All initial errors should be fixed, this is a "just-in-case"
      ex.printStackTrace();
    }

    // Quit the game
    System.out.println("Thanks for playing! Goodbye!");


  }
}