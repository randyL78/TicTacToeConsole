# Algorithms for TicTacToe project
Broken down by class for readability

## Classes that are version independant

### AI.java

``` java

// create a static constant field MAX_DIFFICULTY and set value to 2
// create a method takeTurn that performs a computer turn
  // pass the board and players to takeTurn
  // create an int variable to store the difficulty level of current computer player
  // create an int variable to store the index of the current player in players
  // create an int variable to store the index of the opposite player in players
  // Basic Strategy:
    // 1. always go for middle on first move. (medium/hard)
    // 2. check if there is a win on this move, if so make winning move (hard)
    // 3. check if opponent can win on this move, if so block win (medium/hard)
    // 4. randomly choose from remainder of board (easy/medium/hard)
  // if difficulty is medium or hard check if middle square is owned and if not, take it
  // else if difficulty is set to hard, check if computer can win on this turn
    // if computer can win, select winning square
  // else if difficulty is set to medium or hard, check if opposite player can win on their next turn
    // if opposite player can win, bloack win by selecting winning square
  // else choose a random square
// create a method named easyTurn that is passed game board and a player index
  // loop until a square is chosen that is not taken
    // select a random square
  // return the random square chosen
// create a method named checkMiddle and pass it the current game board
  // check if middle square is taken
    // return true if middle square is empty
    // return false is middle square is taken
// create a method named canWin that is passed the game board and a player's index
  // check if the player that is passed can win on their next move
  // loop through rows and call canRowWin
    // if a row can win, return the winning square location
  // loop through columns and call canColumnWin
    // if a column can win, return the winning square location
  // call canDiagonalWin
    // if a diagonal can win, return winning square location
  // if no way to win, return null
// create a method canRowWin that is passed the game board, a player index and a row index
  // Store owner of each square in row in variables
  // check each row for a combination of 2 squares owned by player and 1 empty square
    // if a combination is found, return row and column index of empty square
  // if no matches found return null
// create a method canColumnWin that is passed the game board, a player index and a column index
  // Store owner of each square in column in variables
  // check each row for a combination of 2 squares owned by player and 1 empty square
    // if a combination is found, return row and column index of empty square
  // if no matches found return null
// create a method named canDiagonalWin and pass it the game board and a player index
  // if player owns center, check open corners for one corner taken by player and the opposite empty
    // if match is found return row and column index of empty square
  // if center is empty, check for two 2 diagonally opposite corners taken by player
    // if match is found, return row and column of middle square
    // in reality by second move of game, middle will always be filled, but will continue to check in case a different AI algorithm is used later
  // if no match is found, return null
  

```

### Board.java

``` java

  // create an 2 dimensional array of Square objects named squares to store board squares
  // create static constant int variables named ROWS and COLS and set their value to 3
  // create a default constructor
    // loop through ROWS 
      // loop through COLS
        // Create a new Square object and assign it to row and column of squares array
  // create a method named isEmptyAt that is passed a row and column index
    // select Square object in Squares array based on passed row and column
      // call Square object's isEmpty method, and return the result
  // create a method named getOwner that is passed a row and column index
    // select Square object in Squares array based on passed row and column
      // call Square object's getOwnedBy method, and return the result
  // create a method named setOwner that is passed a player index and a row and column index
    // select Square object in Squares array based on passed row and column
      // call Square object's setOwnedBy method and pass it player index
  // create a method named clear
    // loop through rows
      // loop through columns
        // select Square object in Squares array based on row and column index
          // call Square object's clear method
  // create a method named clearAt that is passed a row and a column index
    // select Square object in Squares array based on passed row and column
      // call Square object's clear method 

```

### ComputerPlayer

``` java
  
// Create a class ComputerPlayer that extends Player class
// create a private static constant int named MAX_DIFFICULTY and set it equal to AI.MAX_DIFFICULTY
// create a private static constant String named NAME and set it equal to "Computer"
// create a private int field named difficulty
// create a default constructor
  // call the Player class constructor and pass false and NAME
// create a constructor that is passed an int named difficulty
  // call the Player class constructor and pass false and NAME
  // call method setDifficulty and pass it difficulty
// create a method named setDifficulty that accepts an int named difficulty
  // if the difficulty 0 and MAX_DIFFICULTY, said the field difficulty equal to difficulty
  // else throw an IllegalArgumentException
// create a method named get difficulty that returns the value of difficulty field
// create a method named toString that overrides Player class toString method
  // return concatenation of player class toString and difficulty field

```

### FileActions.java

``` java 

// create a private final static String field named FILE_NAME and set its value to "saves.dat"
// create a public static method name savePlayer that accepts a HumanPlayer object and declare it to throw an IOException
  // create a boolean variable named playerExists to store whether player exists or not, initially set to false
  // Create an ArrayList named players to store loaded players in
  // try with resources to open an ObjectInputStream from file named FILE_NAME
    // loop through each Player in file 
      // cast the read object to a HumanPlayer
      // if the humanPlayer's name is equal to player arguments' getName,
        // add the player to the ArrayList
        // set playerExists to true
      // else add humanPlayer to the ArrayList
  // suppress EOF error, when thrown all information will have been gathered
  // suppress other IO exceptions, if they get thrown treat as if player doesn't exist and create new save file 
// if player doesn't exist, add player to ArrayList
// try with resources to open an ObjectOutputStream from file named FILE_NAME
  // loop through each player in ArrayList and write object to file
// create a public static method name loadPlayers declare it to throw an IOException
  // Initialize an ArrayList of players to store loaded players in
  // try with resources to open an ObjectInputStream from file named FILE_NAME
    // loop through each Player in file 
      // cast the read object to a HumanPlayer and add to ArrayList
  // catch ClassNotFoundException and display its StackTrace
  // supress EOF exception, because all data will have been loaded
  // return the ArrayList of players
// create a public static method named loadPlayer that accepts a HumanPlayer argument named player
  // declare method to throw an IOException
  // Create an ArrayList named players and store the results of loadPlayers in it
  // loop through each player in players
    // if player at index has same name as player argument, set player argument to player at index
  // return player

```

### GameLogic.java

```java

// create public static method named checkWin that accepts Board named board and index of current player
  // create a boolean variable named won and set it to an initial value of false
  // set value of won to the return value of checkRows or itself
  // set value of won to the return value of checkColumns or itself
  // set value of won to the return value of checkDecreaseDiagonal or itself
  // set value of won to the return value of checkIncreaseDiagonal or itself
  // return won
// create private static method named checkRows that accepts Board named board and index of current player
  // create a boolean variable named won and set it to an initial value of false
  // loop through each row and check for winner
    // set value of won to return value of checkRow or itself
  // return won
// create private static method named checkRow that accepts Board named board, index of current player, and index of current row
  // loop through each column in row
    // if the owner of the square at current row and column is not equal to current player, return false
  // return true
// create private static method named checkColumns that accepts Board named board and index of current player
  // create a boolean variable named won and set it to an initial value of false
  // loop through each column and check for winner
    // set value of won to return value of checkColumn or itself
  // return won
// create private static method named checkColumn that accepts Board named board, index of current player, and index of current column
  // loop through each row in column
    // if the owner of the square at current row and column is not equal to current player, return false
  // return true
// create private static method named checkDecreaseDiagonal that checks for win on diagonal with negative slope
  // accepts Board named board and index of current player
  // loop through each square on the diagonal with row index increasing and column index decreasing
    // if any square doesn't belong to the current player, return false
  // return true
// create private static method named checkIncreaseDiagonal that checks for win on diagonal with positive slope
  // accepts Board named board and index of current player
  // loop through each square on the diagonal with row index increasing and column index increasing
    // if any square doesn't belong to the current player, return false
  // return true
// create private static method named checkTie that accepts Board named board 
  // loop through each row
    // loop through each column
      // if square at indexes of row and column is unowned, there is not a tie so return false
  // return true

```

### HumanPlayer.java

``` java

  // Create a class HumanPlayer that extends Player class and implements Serializable interface
  // Declare private String field named name
  // Declare private int fields named wins, losses, ties, and count
  // Declare private long field serialVersionUID and assign it a unique ID
  // Create a default constructor
    // Invoke overloaded constructor and pass value of "Player_" plus value of count with 1 added to it
  // Create a constructor that accepts a String named name
    // Invoke overloaded constructor and pass value of name, 0, 0, and 0
  // Create a constructor that accepts a String named name and int values named name, wins, losses, and ties
    // call the parent Player class constructor and pass values of true and name
    // set each field to corresponding argument
    // increment count
  // create public  getter and setter methods for wins, losses, and ties
  // create public addWin method that increments win
  // create public addLoss method that increments losses
  // create public addTie method that increments ties
  // create public method getGamesPlayed that returns sum of wins + losses + ties
  // create public method getName that overrides Player class getName
    // return name
  // create public method toString that overrrides Player class toString
    // return Player class toString plus name, wins, losees, and ties

```

### Player.java

``` java

// declare private boolean field named isHuman 
// declare protected String field named name
// create a default constructor invokes overloaded constructor and passes values true and "Player"
// create a constructor that accepts String arguments named type and name
  // if type equal 'human" then set isHuman to true
  // else if type equals "computer" set isHuman to false
  // else throw an IllegalArgumentException
  // set field name to argument name
// create getter methods for isHuman and name
// create a public method name getType 
  // if isHuman is true return "human" else return "computer"
// create a setter method for name
// create a public method name toString that overrides parent toString method
  // return type of player

```

### Players.java

``` java 

// declare private boolean field named firstPlayerTurn and set to true
// declare public Player array named players
// create a default constructor that sets players to 2 new Player objects
// create a constructor that accepts Player objects named player1 and player2
  // add player1 and player2 to players array
// create a constructor named that accepts an array of Player objects named players
  // set field players equal to argument players
// create a public method names flip
  // if firstPlayer turn equals true, set it to false, else set it to true
  // return the result of currentPlayer
// create a getter method for firstPlayerTurn
// create a public method named currentPlayer
  // if firstPlayerTurn equals true return 0, else return 1
// create a public method named oppositePlayer
  // if firstPlayerTurn equals true return 1, else return 0
// create a public method named replace that accepts a Player object named player and an int named index
  // set players at index equal player
// create public method named getCurrentName that returns the name of the current player
  // invoke the getName method of the player in players at index of currentPlayer


```
## Square.java

``` java

// declare a private in field named ownedBy
// create a default constructor that invokes clear method
// create getter and setter methods for ownedBy
// create public method named isEmpty that returns true if ownedBy equals -1, else returns false
// create public method named clear that sets value of ownedBy to -1

```

## Classes specific to Console version

### GameConsole.java

``` java

// create private constant fields SYMBOL1 and SYMBOL2 and set their values to "X" and "O"
// create a private static method getPlayers that accepts a Scanner object named input
  // Create a Player object named player1 and set it the return value of enterName
  // Create a Player object named player2 and initially set its value to null
  // Attempt to load player1 from save file
    // set the value of player1 to the return value of FileActions loadPlayer method
  // Catch IOEXception
    // Display "Player not found"
  // Display player1 scores by invoking its toString method
  // Prompt user to enter type of second player
  // loop until user makes valid selection
    // if user enters "human" 
      // set value of player2 to results of enterName
      // Attempt to load player2 from save file
        // set value of player2 to the return value of FileAction loadPlayer method
      // catch IOEXception
        // Display "Player not found"
      // Display player2 scores by invoking player2's toString method
    // else if user enters "computer"
      // Create a ComputerPlayer object with difficulty of return value of selectDifficulty method
      // Set player2 equal to ComputerPlayer
    // else display "Invalid Selection" 
  // Prompt user to enter player order
  // loop until user makes valid selection
    // if user enters "Y" or "y" create a Players object with order of player1 then player2
      // return Players object
    // else if user enters "N" or "n" create a Players object with order of player2 then player1
      // return Players object
    // else display "Invalid Selection"
// create a private static method enterName that accepts a Scanner named input
  // Prompt user to enter name
    // return value user enters
// create a private static method named selectDifficulty that accepts a Scanner named input
  // declare a int variable named difficulty and set its initial value to -1
  // loop until difficulty does not equal -1
    // Prompt user to enter a difficulty level
    // declare an int variable named intValue and set it equal to input.nextInt
    // if intValue is between 0 and AI field MAX_DIFFICULTY set difficulty equal to intValue
    // else display "Invalid Selection
  //return difficulty
// create a private static method named takeTurn that accepts a Scanner named input
  // Prompt user to enter a grid location
  // declare a boolean variable name turnComplete and set its initial value to false
  // loop until turnComplete equals true
    // attempt to make a grid selection fromm user input
      // declare a String variable name inputString and set it input.next()
      // if inputString equals "Q" or "q" return true to quit the game
      // declare an int variable named row and assign it the value of inputString minus 1
      // set inputString equal to input.next()
      // if inputString equals "Q" or "q" return true to quit the game
      // declare an int variable named col and assign it the value of inputString minus 1
      // if board square is empty at index row and col 
        // set the owner of the board square to the index of the current player
        // set the value of turnComplete to true
      // else display the square is taken
    // catch NumberFormatException when a user enters something other than a q or number
    // catch ArrayIndexOutOFBoundsException when numbers values are greater than number of rows or columns
  // return false
// create a public static method named printBoard to display board to console
  // Invoke printRow method for each row
  // Display "-------------" in between each row
// create a private static method named printRow to display board row to console 
  // accepts a Board named board, and int named row 
  // display the results of getSymbol for each column in current row with a "|" in between
// create a public static method named clearScreen that clears the console screen
// create a private static method named getSymbol 
  // accepts a Board named board, and ints named row and col
  // if the board is empty at index row and column, return " "
  // else return the symbol of the owner of the board square at index row and col
// create a public static method named askToPlayAgain
  // Prompt user to enter if that want to play another game
  // loop until valid answer is given
  // return the results of user answer

```

TicTacToeConsole.java

``` java

// declare and initialize a Scanner object named input 
// display welcome messaage
// declare and initialize Board object named board
// declare a Players object named players and set it equal to return value of GameConsole.getPlayers
// Display initial game board
  // invoke GameConsole.clearScreen and GameConsole.PrintBoard
// declare a boolean value named quit and set its initial value to false
// loop until quit equals true
  // if current player is human take turn by invoking GameConsole.takeTurn and determine if user quit
  // else take computer turn by invoking AI.takeTurn
  // Display game board in current state
    // invoke GameConsole.clearScreen and GameConsole.PrintBoard
  // If there is a winner
    // If a human player won, congratulate them
      // increment wins for current player
      // display current player score
      // if opposite player is human
        // increment loss for other player
        // display opposite player score
    // else display loss message
      // increment loss for other player if human
      // show opposite player score if human
    // ask users if they want to play again, if not quit the  game
    // clear and display game board
    // Reset to first player's turn for new game
  // if there was a tie
    // Adjust score stats for human players
    // Display scores for human players
    // ask users if they want to play again, if not quit the  game
    // clear and display game board
    // Reset to first player's turn for new game
  // else invoke players.flip
// save players on exit
// display game exit message


```

## Classes specific to GUI version

### Modal.java

```java

// create class Modal that extends javafx.stage.Stage class
// declare a private Button field named btnPlay so it can be accessed by class invoker
// create a constructor that accepts Strings named title and message
  // create and style elements to display message
    // Declare a Text object named messageText with text of message
    // style messageText
  // Declare a StackPane object named messagePane and add messageText to it
    // style message pane
  // Declare Button objects name btnPlay with text "PlayAgain" and btnQuit with text "Quit"
  // create an event handler for btnPlay that closes Modal
  // create an event handler for btnQuit that exits program
  // Declare a HBox object named buttonBox and btnPlay and btnQuit to it
    // Style buttonBox
  // Create a BorderPane object
    // Add messagePane as borderPane center
    // Add buttonPane as borderPane bottom
  // create scene and add to Modal
    // Set Modal title equal to title
    // Style Modal
    // Show Modal    
// create public method getBtnPlay that returns btnPlay

```

### PlayerSelectPane

``` java 

// create class PlayerSelectPane that extends javafx.scene.layout.VBox
// declare private static int field named count
// declare private int field named number
// declare private CheckBox field named chkHuman
// declare private TextField named txtPlayer
// declare private Text field named difficultyText
// declare private ComboBox field named cbDifficulty
// create a default constructor
  // style PlayerSelectPane
  // increment count
  // set number equal to count
  // declare a Text object named titleText and set its text equal to title
    // style titleText
  // initialize chkHuman with text "Is Player Human"
  // initialize txtPlayer with text "Name"
  // initialize difficultyText with text "Difficulty Level"
  // initialize cbDifficulty with String values of "Easy" "Medium" and "Hard"
    // select second item in cbDifficulty by default
  // default first player to human
    // disable cbDifficulty and set chkHuman selected value to true
  // other player defaults to computer, and name field is disabled
  // add titleText, chkHuman, txtPlayer, difficultyText, and cbDifficulty to PlayerSelectPane
  // handle chkHuman onAction event
    // if chkHuman is selected enable txtPlayer and disable cbDifficulty
    // else disable txtPlayer and enable cbDifficulty
  // handle txtPlayer onMouseClick event
    // if text in txtPlayer equals "name", set text equal to ""
// create public method named getName 
  // if chkHuman is not selected, return "Computer"
  // else if text of txtPlayer equals "name" or "", return "Player_" plus number
  // else return text value of txtPlayer
// create a public method getDifficulty
  // if chcKuman is selected, return -1,  else return selected value of cbDifficulty

```

### ScoresPane

``` java

// declare class ScoresPane that extends javafx.scene.layout.VBox
// declare private Player field named Player
// declare private Text fields named winsText, lossesText, and TiesText
// create a constructor that accepts a Player object named player
  // set field player equal to argument player
  // display name
  // display scores of human players
        // set text values of win, losses, and ties Text component equal to player values
  // style ScoresPane
// create a public method named updateScores
  // if player is human 
    // set text values of win, losses, and ties Text component equal to player values

```

### SquaresPane

``` java

// declare a Board field named board
// declare a Players field named players
// declare int fields named row and column
// declare TicTacToe field named game
// create constructor that accepts a Board and a Player object, ints named row and col, and a TicTacToe object named game
  // set each field equal to argument counterpart
  // set SquarePane styles
  // set SquarePane mouse click event handler to handleClick method
// create a public method named handleClick
  // Determine if the board square represented by current square clicked is open
  // set the symbol based on which players turn it is
  // check for win or tie
    // update gameStatus
  // change player turn
  // if current player is a computer, have the computer take turn
    // create an int array named positionTaken and set it equal to the return value of AI.takeTurn
    // select the SquarePane represented by positionTaken and invoke its setSymbol method
    // check for win or tie
      // update gameStatus
    // change player turn
// create a public method named setSymbol that accepts the index value of the currentPlayer
  // declare a Text object named symbol 
  // style symbol
  // if currentPlayer equals 0 set symbols text value to "X"
  // else set symbols's text value to "O"
// create public method named clear that removes SquarePane's children

```

### TicTacToe.java

``` java

// create TicTacToe class that extends javafx.application.Application
// create start method
// Initialize board object
// declare a 3 x 3 Array of SquarePane objects that display the TicTacToe squares
// create a IntegerProperty object named gameStatus to monitor game status
// Create elements for primary stage
  // Create a border pane to layout main sections of scene, create scene
    // Create and add grid pane to house the Tic Tac Toe Squares as center of border pane
      // Add the SquarePane objects
    // Create and add a Hbox to house UI buttons as bottom of border pane
      // Create and add a “quit” button
        // add an event listener to exit program when quit button is pressed
    // Create and add 2 ScorePane objects to display player scores as border pane left and right
// Create elements for introduction page stage
  // house player selection panes in a vbox
    // add player 1 PlayerSelectionPane
    // add player 2 PlayerSelectionPane
  // Create play and quit buttons
    // Create handler for btnPlay on action event
      // Initialize Players
      // Add Players based on if they are computer or human
        // if player1pane getName returns "computer"
          // Create a computer player and add to players
        // else create a player based on results of FileAction.loadPlayer with value of player1pane.getName passed to it
        // if player2pane getName returns "computer"
          // Create a computer player and add to players
        // else create a player based on results of FileAction.loadPlayer with value of player2pane.getName passed to it
      // Create ScorePanes to display scores
      // loop 3 times
        // loop 3 times
          // add SquarePane to boardPane
      // Show main screen and close intro screen
    // handle Quit button click 
      // exit program
   // Create pane for buttons and add play and quit buttons to it
   // create a BorderPane object to layout introduction stage and set playerSelection pane as center
    // set pane for button as BorderPane bottom
  // create scene, add to stage, and display stage
  // Create an eventlistener for the gamestatus value
    // check to make sure game is not still running (status code -1)
      // if gameStatus equals 0 player 1 is the winner
        // because of status code we know player 1 is human, no need to check just increment win
        // check if player 2 is human, if so increment loss
        // set modal message to Congratulate player
      // if gameStatus equals 1 player 2 is the winner
        // because of status code we know player 2 is human, no need to check just increment win
        // check if player 1 is human, if so increment loss
        // set modal message to Congratulate player
      // if gameStatus equals 2 computer was the winner
        // check if opposite player is human, if so increment their losses
        // set modal window to consul loser
      // if gameStatus equals 3 there was a tie
        // check both players if they are human, if so increment ties
        // set modal message to display tie
      // Update displayed scores
      // Save scores of human players
      // Display modal window
        // If user clicks play again, reset the game
          // close modal window
          // Clear board data
          // set turn to player 1
          // Clear gridPane Squares
          // Reset game status to -1

```