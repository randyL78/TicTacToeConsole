import java.io.IOException;
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
   * @return name of the player from user input
   */
  private static String enterName(Scanner input) {
    System.out.print("Please enter your name: ");
    return input.nextLine();
  }

  /** 
   * Uses input to determine 2 player types, names, etc 
   * @return the 2 players
   */
  public static Players getPlayers(Scanner input) {
    Player player1 = new HumanPlayer(enterName(input));
    Player player2 = null;

    // Attempt to load player from file
    try {
      player1 = FileActions.loadPlayer((HumanPlayer) player1);
    } catch (IOException ex) {
      System.out.println("Player not found, creating a new player");
    }
    // Display scores
    System.out.println(player1);

    // Prompt user to select type of second player
    System.out.println("Would you like to play against the computer or another human?");
  
    do {
      System.out.print("Please enter \"human\" or \"computer\": ");
      String playerType = input.nextLine();
      if (playerType.toLowerCase().equals("human")) {
        // create a second human player
        player2 = new HumanPlayer(enterName(input));
        // Attempt to load player from file
        try {
          player2 = FileActions.loadPlayer((HumanPlayer) player2);
        } catch (IOException ex) {
          System.out.println("Player not found, creating a new player");
        }
        // Display scores
        System.out.println(player2);


      } else if (playerType.toLowerCase().equals("computer")) {
        // creates a new computer player with selected difficulty
        player2 = new ComputerPlayer(selectDifficulty(input));
      } else {
        System.out.println("Invalid selection");
      }
    } while (player2 == null);

    // Prompt user to select playing order
    while (true) {
      System.out.println("Would you like to go first? (Y/n) ");
      String answer = input.next();

      if (answer.toLowerCase().equals("y"))
        return new Players(player1, player2);
      else if (answer.toLowerCase().equals("n")) 
        return new Players(player2, player1);
      else 
        System.out.println("Invalid selection, please try again.");
    } 
  }

  /** 
   * prompts user to select computer difficulty
   * @return the computer difficulty level
   */
  private static int selectDifficulty(Scanner input) {
    int difficulty = -1;
    // Prompt user to select difficulty
    // Loop until user enters valid value
    while(difficulty == -1) {
      System.out.print("Please enter a opponent difficulty: (0 - " + AI.MAX_DIFFICULTY + " ");
      int intValue = input.nextInt();
      if (intValue >= 0 && intValue <= AI.MAX_DIFFICULTY)
        difficulty = intValue;
      else 
        System.out.println("Invalid difficulty level");
    }

    return difficulty;
  }

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

  /** clears terminal screen */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");  
  }

  /** @return the Symbol at a given board square */
  private static String getSymbol(Board board, int row, int col) {
    if (!board.isEmptyAt(row, col))
    return (board.getOwner(row, col) == 0) ? SYMBOL1 : SYMBOL2;
  else
    return " ";
  }

  /** check to see if players would like to play a new game */
  public static boolean askToPlayAgain(Scanner input) {
    // variable to store whether or not to play again, intitially set to false
    boolean playAgain = false;

    // loop until users enters a valid answer
    boolean answered = false;
    while (!answered && !playAgain) {
      System.out.print("Would you like to play again? (Y/n)");
      char answerChar = input.next().toLowerCase().toCharArray()[0];
      // if user types a y or Y return true and exit loop
      if (answerChar == 'y') 
        playAgain = true;
      // if user enters a n or N return false and exit loop
      else if (answerChar == 'n')
        answered = true;
    }
    return playAgain;
  }
}