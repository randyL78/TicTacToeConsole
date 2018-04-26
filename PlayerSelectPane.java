import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;

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