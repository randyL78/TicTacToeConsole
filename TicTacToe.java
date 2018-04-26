import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * TicTacToe
 * GUI version of TicTacToe
 * @Randy Layne
 */
public class TicTacToe extends Application {
  public Players players;
  /** Main entry point into application */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create 2 scenes, one for Intro the other for playing game
    /* 
     * Create the main layout pane for game
     */
    BorderPane pnMain = new BorderPane();
    

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

      // Initialize board and game
      
      // Create panes to display scores
      ScoresPane player1ScoresPane = new ScoresPane(players.players[0]);
      pnMain.setLeft(player1ScoresPane);

      ScoresPane player2ScoresPane = new ScoresPane(players.players[1]);
      pnMain.setRight(player2ScoresPane);
   

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
    introStage.show();

    /* 
     * Set up primary stage components 
     */
    // Use a gridpane to lay out the board
    GridPane boardPane = new GridPane();
    pnMain.setCenter(boardPane);

    // Create scene and add to the stage
    Scene scene = new Scene(pnMain, 800, 600);
    primaryStage.setTitle("Tic Tac Toe!");
    primaryStage.setScene(scene);
  
    
  }
}

//---------------------------------------------------------------------------------

/** 
 * PlayerSelectionPane
 * defines a verticle box pane for housing player selection data
 */
class PlayerSelectPane extends VBox {
  private static int count;
  private int number;
  private CheckBox chkHuman;
  private TextField txtPlayer;
  private Text difficultyText;
  private ComboBox cbDifficulty;


  public PlayerSelectPane() {
    // pane styles
    setSpacing(15);
    setPadding(new Insets(25, 15, 15, 15));

    // pane title
    number = ++count;
    Text titleText = new Text("Player " + number);
    titleText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));

    // player type selection
    chkHuman = new CheckBox("Is player human?");


    // player name selection
    txtPlayer = new TextField("Name");

    // computer difficulty selector
    difficultyText = new Text("Difficulty Level");
    cbDifficulty = new ComboBox<>(FXCollections.observableArrayList(new String[] {"Easy", "Medium", "Hard"}));
    // set default difficulty to medium
    cbDifficulty.getSelectionModel().select(1);


    // default first player to human
    if (number == 1) {
      chkHuman.setSelected(true);
      cbDifficulty.setDisable(true);
      difficultyText.setFill(Color.GRAY);
    } else {
      // other player defaults to computer, and name field is disabled
      txtPlayer.setDisable(true);
    }

    // add fields to pane
    getChildren().addAll(titleText, chkHuman, txtPlayer, difficultyText, cbDifficulty);

    // handle checkbox select event
    chkHuman.setOnAction(e -> {
      if (chkHuman.isSelected()) {
        txtPlayer.setDisable(false);
        cbDifficulty.setDisable(true);
        difficultyText.setFill(Color.GRAY);
      } else { 
        txtPlayer.setDisable(true);
        cbDifficulty.setDisable(false);
        difficultyText.setFill(Color.BLACK);
      }
    });

    // handle text field event
    txtPlayer.setOnMouseClicked(e -> {
      if (txtPlayer.getText().equalsIgnoreCase("name"))
        txtPlayer.setText("");
    });
 
  }

  /**
   * @return the player name or "computer"
   */
  public String getName() {
    // see if checkbox is checked
    if (!chkHuman.isSelected()) 
      return "Computer";
    else if (txtPlayer.getText().equalsIgnoreCase("name") || 
             txtPlayer.getText().equalsIgnoreCase(""))
      // default to player number
      return "Player_" + number;
    else 
      // if user has entered a value for name, return it
      return txtPlayer.getText();
  }

  /**
   * @return difficulty of computer player or -1 if human
   */
  public int getDifficulty() {
    // see if checkbox is checked
    if (chkHuman.isSelected()) 
      return -1;
    else 
      return cbDifficulty.getSelectionModel().getSelectedIndex();
  }
}

//---------------------------------------------------------------------------------

/** 
 * ScoresPane
 * defines a verticle box pane for housing player scores in main game
 */
class ScoresPane extends VBox {
  public ScoresPane(Player player) {
    System.out.println(player.getName());
    // Display name
    Text nameText = new Text(player.getName());
    nameText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
    getChildren().addAll(nameText);

    if (player.isHuman()) {
      // Display scores of human players
      // Wins
      Text winsLabel = new Text("Wins:");
      Text winsText = new Text("" + ((HumanPlayer) player).getWins());
      // Losses
      Text lossesLabel = new Text("Losses:");
      Text lossesText = new Text("" + ((HumanPlayer) player).getLosses());
      // Ties
      Text tiesLabel = new Text("Ties:");
      Text tiesText = new Text("" + ((HumanPlayer) player).getTies());

      getChildren().addAll(winsLabel, winsText, lossesLabel, lossesText, tiesLabel, tiesText);
    } 

    // set pane styling
    setStyle("-fx-background-color: aqua");
    setSpacing(5);
    setPadding(new Insets(5, 10, 0, 10));
    setMinWidth(170);
  }
}