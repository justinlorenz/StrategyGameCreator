## Project: Strategy Games (Checkers, Othello, TicTacToe, Connect4)
#### Group 1: JuJaJeLo

## Implementation Plan

#### Features:

* Strategy Games (Checkers, Othello, TicTacToe, Connect4)

* Playing with 2 Players or solo w/ a computer

* Ability to set a profile picture, choose a name, choose a theme

* Implement levels of AI logic to allow the user to either play against a computer or another person

* Ability to load, save game and come back later

* Potentially expand into more games if time permits.

#### Members:

 - Jason: Frontend design of the GUI and interfacing with Controller
 - Jerry: Bridge between backend and frontend; helping with Controllers necessary for communicating
          new moves and game logic
 - Justin: Backend implementation of game logic in determining turn and winners, as well as implementing
          various levels of AI.
 - Loten:Backend implementation of game logic in determining turn and winners, as well as implementing
          various levels of AI.
 
#### Features
- Sprint 1: Basic implementation of TicTacToe Game, with emphasis on planning ahead for more complex games like checkers. Have a functioning GUI to test with. 
Work on having a "dumb" AI to start with and ensure all win/lose/tie logic works. Start working on saving/loading games as well.
- Sprint 2: Implement Othello, Connect4 first since they share similarities with TicTacToe. Then work on implementing Checkers. Improve the AI to a "smart" AI. On the GUI side, add more
enhancements for a better user experience such as more language options, more profile pictures, and different boards and pieces for different games.
- Final: Add in more optional features like a Mod for a game, and fix up any other optional features from Sprint 1 and Sprint 2. Look into adding more games or more advanced AI
if time permits.

#### Wireframe

[WireFrame Representation for GUI](https://imgur.com/a/okexQ9i)

Show current GUI implementation

## Design Plan

#### Design and Framework goals:

##### Flexible/Open: 
* We want new users to be able to create different strategy games that they can play (make it as easy as possible to do so)
* We want the users to be able to choose different settings (colors,images,player profiles, different game rules) for various games to allow for multiple unique experiences 
* We want the API design to be flexible in the way that anyone can swap in their own data reader, game engine, player-view APIs into our current code and the game should run as expected
* We want to allow the number of players to be flexible to allow for interesting dynamics in games that usually are hardcoded to 2 players

##### Fixed/Closed:
* We have fixed the user to only choosing a square board (either they can choose from various provided square boards or can create their own board with a csv)
* In terms of game framework, we are following the MVC design for this project. Our backend holds all of our data, and it completely separated from the view which holds our GUI and also holds the game controller that connects these two pieces together

#### Overall project's modules

Engine:

One overall Game Class that serves as the framework that holds all the current game information
A BoardStructure that encapsulates the actual model data’s structure (instances of this class held in Board)
The BoardStructure will hold instances of a cell class which holds the GUIPiece type, coordinates, etc.
We will have overall gametype classes that sprout from a GameType abstraction such as checkers, othello, tic tac toe, etc. that all implement the needed abstract methods for a new game. These classes will hold the BoardStructure object and manipulate the board based on input from the view. Each game class will manipulate the board in their own ways based on the specific game type.
an AI that is able to use the min/max algorithm to figure out the best move out of a provided list of moves

Data:

Read in the provided .game file and store all the info that the user provided
Can read in a csv file to upload the current model board structure. The csv file can either be provided by the user in their properties .game file or we will use one of our own default files
Saving the user’s current progress in the game to a .csv file to load up later to play.

Player (View/GUI):
View of CellPaneBoardStructure that contains the data structure used to display the grid
This cellpaneboard structure can display different types of panes such as varying shapes (this would be done by changing the overall data structure in this class)
CellPaneDisplay to actually display the Grid, using the data structure held in CellPaneBoardStructure
Piece class that is overlaid onto the board and has its own image
Overall button abstraction that each individual button will extend, this class will set the id, text, etc.
Abstraction for all the dialog boxes that need to be displayed


#### APIS:

1. Controller API

```java
public interface ControllerAPI {
  private Game myGame;
  private BoardStructure currBoard;
  private GameType gameType;
  private DataReader dataReader;
  private List<Players> players;

  public void step(); //runs all of our step methods each game tick
  public void addNewPlayerToGame(String playerName, String playerType); //adds new player to list using reflection
  public void createNewGame(); //this is called from the view when game is ready to be loaded -> creates new game instance
  public void loadInGame(File gameFile); //let the dataAPI read in this file and parse the data
  public void saveCurrentGame(); //save current model structure in csv
  public void gotUserInput(int i, int j);
}
```

2. Game API

```java
public interface GameAPI {
 private BoardStructure currBoard;
 private List<Players> myPlayers;
 private Player currPlayer;
 GameType myGameType;

 public void gameConstructor(BoardStructure currBoard, List<Players> players, GameType gameType);
 public GameStatus getGameStatus(); //call gameType.getGameStatus(), GameStatus is enumerated type
 public void gotUserInput(int i, int j);
 public void playerCurrentPlayerTurn();
 private void updateToNextPlayersTurn();
}
```

#### Use Cases:
1. 
* Creating a new game from a loaded in properties file
```java
//in the controller
public void loadInGame(File gameFile) {
 dataReader = new DataReader();
 dataReader.loadInGame(gameFile);
}

public Game loadInGame(File gameFile) {
   propertiesReader = new PropertiesReader(gameFile);
   List<String[]> csvData = readAll(propertiesReader.getGameProperty("InitialConfiguration"));
   Piece[][] gamePieces = readInGameStates(csvData,gameType);
   BoardStructure currBoard = createBoardStructure(gamePieces);
   List<Player> playerList = createPlayersMethod();
   String gameType = propertiesReader.getGameProperty("GameType");
   GameType gameType = findGameType(gameType);
   return new Game(currBoard,playerList,gameType;
}
```

2. 

* A player clicks an invalid move

```java
// Front end communicates to controller user input occurred.
ControllerAPI.userInputDetected(int i, int j)

//Back end receives call from controller
Game.userInputDetected(int i, int j);
boolean validMove = HumanPlayer.makeMove(i , j)

return gameType.isValid() // returns false in HumanPlayer.makeMove()

//Game receives that makeMove() call is false, thus current player turn is not changed inside of game
if(validMove) -> Game.updatePlayerTurn

ControllerAPI.userInputDetected() returns false  
GUI.displayBadMoveMad()

```


#### Alternative Design:

Initially, we defined a Cell to contain a state, which held a Piece represented by an integer. This proved to be a code smell,
as we used a primitive data type to represent something that could become complicated. We initially decided to represent a 
cell state by int to prevent the neccesity to create extra classes for every new game implemented. The gameType class 
would store what pieces are associated with which integers. This older design would result in code that isn't closed, as
it would've required large if trees for games with pieces that have different behaviors. 

```java
public class State {
 private int pieceType,playerNumber;

 public State(int stateValue,int playerNumber) {
   this.pieceType = stateValue;
   this.playerNumber = playerNumber;
 }

 protected int getPlayerNumber() {
   return playerNumber;
 }

 protected int getPieceType() {
   return pieceType;
 }

 protected void setState(int playerNumber, int newPieceType) {
   pieceType = newPieceType;
   this.playerNumber = playerNumber;
 }

}
```

```java
public abstract class Piece {

 private int playerNumber;

 public Piece(int playerNumber) {
   this.playerNumber = playerNumber;
 }

 public int getPlayerNumber() {
   return playerNumber;
 }

 public abstract boolean isValidMove(BoardStructure currBoard, int i, int j);
}
```

