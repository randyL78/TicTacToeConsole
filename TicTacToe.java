import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
  /** Main entry point into application */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Create 2 scenes, one for Intro the other for playing game

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
      System.out.println(player1Pane.getName());
      System.out.println(player2Pane.getName());
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
    Scene scnIntro = new Scene(pnIntro, 300, 400);
    introStage.setTitle("Welcome to Tic Tac Toe");
    introStage.setScene(scnIntro);
    introStage.show();

    /* 
     * Set up primary stage components 
     */
    // Use a gridpane to lay out the board
    GridPane boardPane = new GridPane();

    // Create scene and add to the stage
    Scene scene = new Scene(boardPane, 800, 600);
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

    // default first player to human
    if (number == 1) {
      chkHuman.setSelected(true);
    } else {
      // other player defaults to computer, and name field is disabled
      txtPlayer.setDisable(true);
    }

    // add fields to pane
    getChildren().addAll(titleText, chkHuman, txtPlayer);

    // handle checkbox select event
    chkHuman.setOnAction(e -> {
      if (chkHuman.isSelected()) 
        txtPlayer.setDisable(false);
      else 
        txtPlayer.setDisable(true);
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
    else if (txtPlayer.getText().equalsIgnoreCase("name")) 
      // default to player number
      return "Player_" + number;
    else 
      // if user has entered a value for name, return it
      return txtPlayer.getText();
  }
}