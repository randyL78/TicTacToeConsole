import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * TicTacToe
 * GUI version of TicTacToe
 * @Randy Layne
 */
public class TicTacToe extends Application {
  public Players players;
  private ScoresPane player1ScoresPane;
  private ScoresPane player2ScoresPane;
  
  // Initialize  game board
  private Board board = new Board();

  // Initialize board squares
  public SquarePane[][] squares = new SquarePane[3][3];

  // create a variable to monitor game status
  public IntegerProperty gameStatus = new SimpleIntegerProperty(-1);

  /** Main entry point into application */
  @Override
  public void start(Stage primaryStage) throws Exception {



    // Create 2 scenes, one for Intro the other for playing game

    /* 
     * Set up primary stage components 
     */

     // =========== board and square elements ===========

    // Set up main layout pane for primary stage
    BorderPane pnMain = new BorderPane();

    // Use a gridpane to lay out the board
    GridPane boardPane = new GridPane();
    pnMain.setCenter(boardPane);


    // =========== bottom buttons =======================
    // Create quit button
    Button btnQuitMain = new Button("Quit");
    btnQuitMain.setOnAction(e -> {
      System.exit(1);
    });

    // Create an hbox to house quit button
    HBox hboxForMainButtons = new HBox(5, btnQuitMain);
    hboxForMainButtons.setAlignment(Pos.CENTER);
    hboxForMainButtons.setPadding(new Insets(5, 5, 5, 5));
    pnMain.setBottom(hboxForMainButtons);



    // Create scene and add to the stage
    Scene scene = new Scene(pnMain, 800, 500);
    primaryStage.setTitle("Tic Tac Toe!");
    primaryStage.setScene(scene);
    

    /*
     * Create an introduction stage where users can select their name and opponent
     */
    // Create intro stage to set up players
    Stage introStage = new Stage();

    // =========== player selection fields =========

    // player 1 selection pane
    PlayerSelectPane player1Pane = new PlayerSelectPane();

    // player 2 selection pane
    PlayerSelectPane player2Pane = new PlayerSelectPane();

    // house player selection panes in a vbox
    VBox playerSelection = new VBox(15, player1Pane, player2Pane);
    playerSelection.setStyle("-fx-background-color: aqua");

    // =========== buttons ===============
    // Create play and quit buttons
    Button btnPlay = new Button("Play");
    Button btnQuit = new Button("Quit");

    // handle Play button click
    btnPlay.setOnAction( e -> {
      // Initialize Players
      players = new Players();

      // Add Players based on if they are computer or human
      if (player1Pane.getName().equalsIgnoreCase("computer")) {
        // Create a computer player and add to players
        players.replace(new ComputerPlayer(player1Pane.getDifficulty()), 0);
      } else {
        try {
          // Load player 1 into Players
          players.replace(FileActions.loadPlayer(new HumanPlayer(player1Pane.getName())), 0);
        } catch (IOException ex) {
          // If there is an error, just create a new player based on field name
          players.replace(new HumanPlayer(player1Pane.getName()), 0);
        }
      }


      if (player2Pane.getName().equalsIgnoreCase("computer")) {
        // Create a computer player and add to players
        players.replace(new ComputerPlayer(player2Pane.getDifficulty()), 1);
      } else {
        try {
          // Load player 1 into Players
          players.replace(FileActions.loadPlayer(new HumanPlayer(player2Pane.getName())), 1);
        } catch (IOException ex) {
          // If there is an error, just create a new player based on field name
          players.replace(new HumanPlayer(player2Pane.getName()), 1);
        }
      }

      // Create panes to display scores
      player1ScoresPane = new ScoresPane(players.players[0]);
      pnMain.setLeft(player1ScoresPane);

      player2ScoresPane = new ScoresPane(players.players[1]);
      pnMain.setRight(player2ScoresPane);
   
      // add board squares
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          boardPane.add(squares[i][j] = new SquarePane(board, players, i, j, this), j, i);
        }
      }

      // Show main screen and close intro screen
      primaryStage.show();
      introStage.close();
    });

    // handle Quit button click
    btnQuit.setOnAction(e -> {
      System.exit(1);
    });

    // Create pane for buttons and add them to it
    HBox hBoxForIntroButtons = new HBox(15, btnPlay, btnQuit);
    hBoxForIntroButtons.setAlignment(Pos.CENTER);
    hBoxForIntroButtons.setPadding(new Insets(5, 5, 5, 5));

    // Set main pane for intro page
    BorderPane pnIntro = new BorderPane(playerSelection);
    pnIntro.setBottom(hBoxForIntroButtons);

    // Create scene and display stage
    Scene scnIntro = new Scene(pnIntro, 300, 500);
    introStage.setTitle("Welcome to Tic Tac Toe");
    introStage.setScene(scnIntro);
    introStage.setResizable(false);
    introStage.show();
    

    // Create an eventlistener for the gamestatus value
    gameStatus.addListener(new InvalidationListener() {
      @Override
      public void invalidated(Observable observable) {
        // check to make sure game is not still running (status code -1)
        if (gameStatus.getValue() != -1) {
          String title = "";
          String message = "";
          switch (gameStatus.getValue()) {
            // player 1 is winner
            case 0:
              // because of status code we know player 1 is human, no need to check just increment win
              ((HumanPlayer) players.players[0]).addWin();

              // check if player 2 is human, if so increment loss
              if (players.players[1].isHuman())
                ((HumanPlayer) players.players[1]).addLoss();

              // set modal message to Congratulate player
              title = "Tic Tac Toe - Winner!";
              message = "Congratulations " + players.players[0].getName() + ", You Won!";
              break;

            // player 2 is the winner
            case 1:
              // check if player 1 is human, if so increment loss
              if (players.players[1].isHuman())
                ((HumanPlayer) players.players[0]).addLoss();

              // because of status code we know player 2 is human, no need to check just increment win
              ((HumanPlayer) players.players[1]).addWin();

              // set modal message to Congratulate player
              title = "Tic Tac Toe - Winner!";
              message = "Congratulations " + players.players[0].getName() + ", You Won!";            

              break;   
            
            // a computer player won
            case 2:
              // check if opposite player is human, if so increment their losses
              if (players.players[players.oppositePlayer()].isHuman()) 
                ((HumanPlayer) players.players[players.oppositePlayer()]).addLoss();

              // set modal window to consul loser
              title = "Tic Tac Toe - Sorry!";
              message = "Sorry " + players.players[players.oppositePlayer()].getName() + ", You Lost!";
              break;

            // there was a tie
            case 3:
              // check both players if they are human, if so increment ties
              if (players.players[0].isHuman())
                ((HumanPlayer) players.players[0]).addTie();
              if (players.players[1].isHuman())
                ((HumanPlayer) players.players[1]).addTie();

              // set modal message to display tie
              title = "Tic Tac Toe - Tie!";
              message = "The Game Was a Tie!";
          }

          // Update displayed scores
          player1ScoresPane.updateScores();
          player2ScoresPane.updateScores();

          // Save scores of human players
          try {
            if(players.players[0].isHuman())
              FileActions.savePlayer((HumanPlayer) players.players[0]);
            if(players.players[1].isHuman())
              FileActions.savePlayer((HumanPlayer) players.players[1]);
          } catch (IOException ex) {
            ex.printStackTrace();
          }

          // Display modal window
          Modal modal = new Modal(title, message);

          // If user clicks play again, reset the game
          modal.getBtnPlay().setOnAction( e -> {
            // TODO: implement game resetting

          });


        }
      }
   });

  }
}




