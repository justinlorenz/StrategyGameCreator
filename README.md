final project - Team JuJaJeLo
====

This project is a 2 player strategy game simulator. In our game, you are able to play the variety of strategy games that we have provided with 2 users or against a random AI. On top of this, new users are able to build any game variant they want using our current building blocks, or you can choose to create your own building blocks to be used as well. Also, our project is made up of APIs that are available to be used in other projects as well as being open to extension.

Names:

* Justin Lorenz (jml166)
* Jerry Fang (jdf58)
* Loten Lhatsang (ltl10)
* Jason Dong (jd385)


### Timeline

Start Date: 10/24/2020

Finish Date: 11/18/2020

Hours Spent: 250+ Combined Hours

### Photos Of Our Project

Check out doc/presentation4/ to find four video demos of the games we implemented.

![Starting Screen](/doc/readmeAssets/startScreen.png)

![Player Selection](/doc/readmeAssets/playerselect.png)

![Gameplay](/doc/readmeAssets/gameplaying.png)

### Resources Used
* StackOverflow for specific questions
* [Leaderboard Numbers](https://opengameart.org/content/numbers-blocks-set-01)
* [Board Game Tiles and Pieces](https://graphicriver.net/item/designer-essentials-3d-board-game-tiles/21146124)
* [Styling for buttons](http://fxexperience.com/2011/12/styling-fx-buttons-with-css/)
* [BoardGame Background texture](https://opengameart.org/content/background-texture-for-a-board-game)
* [TicTacToe Sprites](https://www.svgrepo.com/svg/67645/tic-tac-toe)
* [Mockito](https://site.mockito.org/) - org.mockito:mockito-core:3.6.0 - used for JUnit testing purposes.


### Running the Program 

ooga.Main class:

* Run Main.main to start running the program. Make sure /resources is marked as the resources root and appropriate packages are installed.
The splash screen that appears gives the option to:

1. Change the language of the program (currently English, Spanish, and Pig Latin are implemented) using the choice box.
2. Start a new game using default rules and starting boards with the Create Game button.
3. Load a game with user rule configuration, or a previously saved game with the Load Game button.
4. Enter the chat room.
5. Look at the leaderboard.
6. View the 4 default games and their descriptions.


### Data files needed: 

The data files detailed below should be in the ./data directory.

* .game file needed to specify player profiles and resources used for front-end visualization as well as valid game rules.

* .csv file needed for the initial board configuration layout for grids and pieces.

* For creating a new instance of a pre-made game (using the create game button on the start screen), no data files need to be created. However, for the creation of a new game/mod, a .game file and .csv file must be made and loaded using the load game button on the start screen.

#### .game file

The .game file should be formatted the same as a properties file, with the Key values listed below:
- **GameType** = One of the four choices: TicTacToe, ConnectFour, Checkers, or Othello
- **InitialConfiguration** = the file path to the .csv file containing the initial board configuration, starting from ./data. Default grids can be found and used at .data/defaultGrid.
- **Player1** = Human or AI.
- **Player1Name** = Name of Player1.
- **Player2** = Human or AI.
- **Player2Name** = Name of Player2.
- **Player1NormalPiece** = File name of associated picture used to represent Player1's normal pieces. This file should be in ./resources.
- **Player2NormalPiece** = File name of associated picture used to represent Player2's normal pieces. This file should be in ./resources.
- **Player1SpecialPiece** (Required only for games with special pieces i.e. checkers)= File name of associated picture used to represent Player1's special pieces. This file should be in ./resources.
- **Player2SpecialPiece** (Required only for games with special pieces i.e. checkers)= File name of associated picture used to represent Player2's special pieces. This file should be in ./resources.
- **Player1Image** = File name of associated picture used to represent the Player1's profile picture, without the extension
- **Player2Image** = File name of associated picture used to represent the Player2's profile picture, without the extension
- **Theme** = Duke, UNC, or Christmas.
- **CurrentPlayerIndex** = 1 or 2, whichever player's turn the game should start on.

 **The following are Keys used to determine the game's rules**

- **WinOnEnd** = Boolean, whether the game should only be won if there are no more moves available.
- **WinConditions** = List of classes that determine the conditions for if a player has won or not. Classes found in ./src/ooga/model/gameBuildingBlocks/winConditions. You must specify row/diagonal/column win and the specific number of pieces needed for each win. 
- **SpecialPiece** = Boolean, whether the game has a special piece (e.g. King piece in checkers).
- **PieceMove** = List of classes that define how a normal piece moves. Classes found in ./src/ooga/model/gameBuildingBlocks/validMoveChecks
- **SpecialMove** (Required only if SpecialPiece is True) = List of classes that only special pieces can perform (in addition to normal moves). Classes found in ./src/ooga/model/gameBuildingBlocks/validMoveChecks
- **PromotionCondition** (Required only if SpecialPiece is True) = List of classes that specify the promotion conditions of the pieces. Classes found in ./src/ooga/model/gameBuildingBlocks/promotionCondition
- **BoardMoveUpdate** = List of classes the specify how the board is updated when pieces are moved. Classes found in Names of the classes contained in ./src/ooga/model/gameBuildingBlocks/boardMoveUpdate
- **PiecesAreMoved** = Boolean for if pieces are moved (e.g. checkers) or not (e.g. TicTacToe).
- **UserEndsTurn** = Boolean for if the user manually ends their turn, or if the turn automatically ends after a move is performed.

#### .csv file
The .csv file used as the initially configuration of the board should be formatted as such, using an example from a saved checkers game: 

```
8
Empty 0,NormalPiece 2,Empty 0,NormalPiece 2,Empty 0,SpecialPiece 2,Empty 0,NormalPiece 2
NormalPiece 2,Empty 0,NormalPiece 2,Empty 0,Empty 0,Empty 0,NormalPiece 2,Empty 0
Empty 0,NormalPiece 2,Empty 0,Empty 0,Empty 0,Empty 0,Empty 0,NormalPiece 2
Empty 0,Empty 0,Empty 0,Empty 0,NormalPiece 2,Empty 0,NormalPiece 2,Empty 0
Empty 0,Empty 0,Empty 0,SpecialPiece 2,Empty 0,Empty 0,Empty 0,Empty 0
NormalPiece 1,Empty 0,NormalPiece 1,Empty 0,Empty 0,Empty 0,NormalPiece 1,Empty 0
Empty 0,NormalPiece 1,Empty 0,Empty 0,Empty 0,NormalPiece 1,Empty 0,NormalPiece 1
NormalPiece 1,Empty 0,NormalPiece 1,Empty 0,NormalPiece 1,Empty 0,NormalPiece 1,Empty 0

```
The first line determines the dimension of the board (in this case 8x8), and each Piece placed on the board is designated as either Empty 0, NormalPiece PlayerNumber (1 or 2), or SpecialPiece PlayerNumber.

* Exception handling

 Exceptions primarily occur from poorly formatted data files being loaded in. Exceptions are thrown in the backend and the front end catches and handles the exception by displaying a dialogue box giving a description of what the error is. 

 - BadCSVFileException: Thrown when csv file is not formatted correctly 
 - BadDataException: Thrown when data is not properly formatted
 - BadGameFileException: General exception thrown for uncaught exceptions
 - ClassDoesNotExistException: Exception thrown for attempting to use reflection on classes that do not ClassDoesNotExistException
 - MissingPropertyException: Thrown when attempting to use property that is missing
 - SavingFileException: Thrown when file is attempted to save and fails
 - SavingGameFileException: Thrown when game file is attempted to save and fails

### Features implemented:

* Working pre-made games of TicTacToe, Connect 4, Othello, and Checkers as well as a mod for Checkers.
* Ability to view available games
* Ability to select,play, and switch between games repeatedly without quitting
* Customizable games with languages, colors, themes, player names and profile pictures
* Dynamicaly updated game status (Through highlighting the player whos turn it is)
* Leaderboard that tracks number of player wins
* Ability to save and load games
* Ability to go against either another human player, or a simple AI.
* A chat room on the main screen

#### TicTacToe

- This game in it's default implementation has the standard TicTacToe rules, with the required number of pieces in a row required to win being equal to 3.

#### Connect 4

- This game in it's default implementation has the standard Connect 4 rules, in which pieces "fall" to the bottom most available row in the column clicked, and when 4 of a player's pieces are in a row (no matter the size of the board), that player wins.

#### Othello

- This game in it's default implementation has the standard Othello rules. You can only place a new piece where you're outflanking the opponent's 
piece. After you place your piece, all outflanked pieces update based on the new move. The game stops when there is no more valid moves, and the
winner is the player with the most pieces on the board. 

#### Checkers

- This game in it's default implementation has the standard checkers rules. Pieces can only move diagonally forward, until they reach the opposite end of the board and are promoted to King pieces. It is also unique from the other games in that it requires the user to end their turn using the End Turn button. 

- The AI in checkers is limited to performing only one action per turn.

#### Checkers Mod

- This game mod functions the same as checkers, except that pieces are able to move vertially and horizontally as well, rather than only diagonally.

Further mods to these games (or entirely new ones) can be made using the programs gameBuildingBlocks classes using properly configured data files as described above. Games or mods made by the user must be loaded in using the Load Game button.

### Notes/Assumptions

Assumptions or Simplifications:

* User is able to construct their own data file when loading up a mod game of Checkers, TicTacToe,
Connect Four, and Othello. The user should know how to make the .game files and .csv files appropriately
if they want to create a custom game with custom rules. Note: when loading a file, if it is missing any 
of the key properties that are missing, it will throw an exception.

* Assume that there are only a max of 2 Players

* Only one "special piece" type per game is possible

* The grid dimensions will always be a square

* Assume that neither player cheats, and that a player is responsible for ending their own turn after they are done with their move.

* Assume that the player loads up profile pictures that always have a .jpg extension, and the user must also have a slightly off-colored image representing the hover condition in the format "FILENAMEHover.jpg".

Interesting data files:

* Various data files for loading up games with twists on traditional Checkers, TicTacToe, Othello, and 
Connect Four. These files can all be found in ../data/savedGames. 

* Various data files used for testing purposes that test for game behaviour. These files can be
found in ../data/TestingGameFiles

* Various "mods" to play from possible Saved Games. You can also play Checkers Mod from the create game menu. Some examples of various mods 
created is 
    * Checkers Mod with the ability to promote on both columns instead of one row
    * Checkers Mod with the ability to move vertically and horizontally as well as jump in those directions
    * Connect Four Mod that you can play sideways to the rightmost column


Known Bugs:

* Othello has 1 bug for placing a piece on a DiagonalOutFlank with no surrounding cells. 

Extra credit:

Optional features implemented:
* High Scores: Due to the nature of Strategy Games not really being able to have a "high score" feature,
our leaderboard tracks the number of wins a player has based on their name. Winning any game against
any opponent increments your accumulated leaderboard score by 1, and the top 9 leaderboard scores are 
displayed.

* Preferences: The user is able to select a multitude of preferences when starting the game. They can
select a preferred theme (Duke, UNC, Christmas), preferred language (English, Spanish, PigLatin),
preferred Grid Size (varies with each game) as well as editing pieces to use for each game.

* Load Games: The user can choose to load up a game with any initial configuration that they can make
as a .game file with a corresponding .csv for the grid. They can also load up a previous game they had saved.

* Save Game: The user can choose to save their game while they are playing so that they can make a couple
plays, then load up their previously saved game and continue later.

* Artificial Players: A simple AI was implemented that makes random moves if the user chooses to play
singleplayer. 

* Player Profiles: The user can log in and use a different avatar when they load up a new game. This 
profile is saved if the user chooses to save the game as well. Additionally, the user can also customize
their player profiles when entering the Chat Room, which is also saved in a chat log. The user can also
choose to use their own custom profile picture, given that it's in the resources folder.

* Social Center: The user can enter a Chat Room with a name and profile picture that they have picked,
and then interact with other users through the chat box. They can see previous messages, as well
as leave messages for others to see on future runs of the program.  


### Impressions

Great project to work with a larger team in making a larger project. Overall the project was a fun experience in brainstorming our initial idea, developing and implementing our APIs,
adding features and functionality to improve our design and user experience. While seeing this project from start to end took an unprecedented amount of time spent through writing
code and debugging, we believe we carefully managed our ideas and time to produce a functioning and flexible final product.

