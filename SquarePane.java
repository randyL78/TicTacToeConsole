import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;

/**
 * SquarePane
 * The GUI representation of an individual square
 * @author Randy Layne
 */
public class SquarePane extends StackPane {
  private Board board;
  private Players players;
  private int row;
  private int col;
  private TicTacToe game;

  /** 
   * Construct a SquarePane with default styling 
   * @param board the current game board
   * @param players the current players
   * @param row which row in the grid this square belongs to
   * @param col which column in the grid this square belongs to
   * @param game the parent TicTacToe class that called the constructor
   */
  public SquarePane(Board board, Players players, int row, int col, TicTacToe game) {
    this.board = board;
    this.players = players;
    this.row = row;
    this.col = col;
    this.game = game;
    setStyle("-fx-border-color: blue; -fx-border-width: 5");
    setPrefSize(300, 300);
    setOnMouseClicked(e -> handleClick());
  }

  /** handle click event by taking turn */
  private void handleClick() {
    // Determine if the board square represented by current square clicked is open
    if (board.isEmptyAt(row, col) && game.gameStatus.get() == -1) {
      int currentPlayer = players.currentPlayer();
      board.setOwner(currentPlayer, row, col);

      // set the symbol based on which players turn it is
      setSymbol(currentPlayer);

     if (GameLogic.checkWin(board, currentPlayer)) {
       System.out.println("Player won!");
       game.gameStatus.set(currentPlayer);
     } else if (GameLogic.checkTie(board)) {
       System.out.println("There was a tie!");
       game.gameStatus.set(3);
     } else {
        // change player turn
        players.flip();

        //  if current player is a computer, have the computer take turn
        if (!players.players[players.currentPlayer()].isHuman()) {
          int[] positionTaken = AI.takeTurn(board, players);
          game.squares[positionTaken[0]][positionTaken[1]].setSymbol(players.currentPlayer()); 

          if (GameLogic.checkWin(board, players.currentPlayer())) {
            System.out.println("Computer won!");
            game.gameStatus.set(2);
          } else if (GameLogic.checkTie(board)) {
            System.out.println("There was a tie!");
            game.gameStatus.set(3);
          }
          players.flip();
        }
      }
    }  
  }

  /** set the symbol based on which players turn it is */
  public void setSymbol(int currentPlayer) {
    Text symbol = new Text(currentPlayer == 0 ? "X" : "O");
    symbol.setFont(Font.font(Font.getDefault().toString(), FontWeight.EXTRA_BOLD, 70));
    getChildren().add(symbol);
  }

  /** clear the square for a new game */
  public void clear() {
    getChildren().clear();
  }
}