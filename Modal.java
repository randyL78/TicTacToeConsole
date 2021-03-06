import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Modal
 * Displays a pop up message
 * @author Randy Layne
 * TODO: Make class more generic to be able to reuse in other projects
 */
public class Modal extends Stage {
  // make play again button a field so button event can be accessed by caller
  private Button btnPlay;

  public Modal(String title, String message) {
    // create and style elements to display message
    Text messageText = new Text(message);
    messageText.setFont(Font.font(24));
    messageText.setTextAlignment(TextAlignment.CENTER);
    StackPane messagePane = new StackPane(messageText);
    messagePane.setPadding(new Insets(50, 25, 50, 25));

    // create "play again" and "quit" buttons 
    btnPlay = new Button("Play Again");
    Button btnQuit = new Button("Quit");

    btnPlay.setOnAction(e -> {
      close();
    });

    btnQuit.setOnAction(e -> {
      System.exit(1);
    });

    // Create an hbox to house buttons
    HBox buttonBox = new HBox(15, btnPlay, btnQuit);
    buttonBox.setStyle("-fx-background-color: gray");
    buttonBox.setPadding(new Insets(5, 5, 5, 5));
    buttonBox.setAlignment(Pos.CENTER);

    // Create a border pane to layout other panes
    BorderPane borderPane = new BorderPane(messagePane);
    borderPane.setBottom(buttonBox);

    // create scene and add to stage
    Scene scene = new Scene(borderPane);
    setScene(scene);
    setTitle(title);
    setAlwaysOnTop(true);
    setResizable(false);

    // self show stage after construction
    show();
  }

  /**
   * @return the btnPlay
   */
  public Button getBtnPlay() {
    return btnPlay;
  }
}