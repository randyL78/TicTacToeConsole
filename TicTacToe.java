import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    // Create play and quit buttons
    Button btnPlay = new Button("Play");
    Button btnQuit = new Button("Quit");

    // handle Play button click
    btnPlay.setOnAction( e -> {
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

    // Set main pane for intro page
    BorderPane pnIntro = new BorderPane();
    pnIntro.setBottom(hBoxForIntroButtons);

    // Create scene and display stage
    Scene scnIntro = new Scene(pnIntro, 400, 500);
    introStage.setTitle("Welcome to Tic Tac Toe");
    introStage.setScene(scnIntro);
    introStage.show();

    /* 
     * Set up primary stage components 
     */
    // Use a gridpane to lay out the board
    GridPane boardPane = new GridPane();

    // Create scene and add to the stage
    Scene scene = new Scene(boardPane, 600, 600);
    primaryStage.setTitle("Tic Tac Toe!");
    primaryStage.setScene(scene);
    
  }
}