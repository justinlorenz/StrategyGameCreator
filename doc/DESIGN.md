# Simulation Design Final
## Design Goals
#### What Features are Easy to Add

* You can create any variation of the games implemented (i.e. Connect3 Sideways, Vertical/Horizontal Checkers, etc) just by changing the game files.

* Additional game building blocks that allow future additions to add additional WinConditions, PromotionConditions, PieceMoves, ValidMoves to essentially create
any variation of Tic-Tac-Toe, Checkers, Othello, and Connect Four

* Can easily create an extension of the DataAPI to read in different kinds of files

## High-level Design
#### Core Classes

* GameEngine: Primary model class, responsible for handling the current player's, the GameStatus, the board structure, as well as holding the game move handler object.

* GameMoveHandler: This class deals with everything in terms of a move. Some of the move handler does is checks for valid moves to make, plays different pieces, and updates the board. Additionally, piece promotion is handled by GameMoveHandler as well. 

* GameFileReader: API used for concrete methods for a Reader for our .game file. Expandability to XML and JSON files is possible by utilizing this interface.

* Controller: Serves as the main bridge between the view and the model. It contains functions to be able to pass data from the view and model, such as userInput, clickedPieceData, possibleMoves, etc. Additionally, the controller hosts the Data API and calls loadInGame within the loadIn() method. 

* MainGUI: Primary user interface that shows the player HUD settings. Some features implemented include separate panels for each player complete with their name and profile picture. If an end turn button is used for the game, the end turn button dynamically switches between the player to show the current player's turn. Additionally, the MainGUI houses the gameboard to play on for the user to click and execute their moves. There are also additional buttons for quitting to the starting screen without quitting the game, accessing the chat room, and resetting the game. 

## Assumptions that Affect the Design

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

#### Features Affected by Assumptions

* Only 2 players can play at a time

* "Special Pieces" implementation would need to be changed to implement something like Chess that have a lot of special pieces and moves

* Limited to using .jpg files for profile pictures, would need to convert .png or other file formats first before using it. 

## Significant differences from Original Plan

* Instead of mainly focusing on aesthetic changes for customizations, we also made the backend flexible to customization

* RandomAI implemented only instead of a fully working minimax AI algorithm. 

## New Features HowTo
#### Easy to Add Features

* Easy to add GUI Customizations of additional themes, languages, profile pictures, and default grids. To add additional themes, you would need to import new banners that appear behind the player's name and profile pictures and name them in the format "christmasTheme1.png", where "christmas" is the name of the new theme. To add additional languages, new .properties files would be needed to add all the translations for key value pairs for the new language. To add new profile pictures, you must have a new image to add to the resources file with a .jpg extension, and also a corresponding Hover image. See the resources folder for the format.

* Easy to add additional game building blocks to add any additional features that you want to customize the playing experience. To add new building blocks, implement existing Game Building Block Interfaces and complete the required methods. Then, to use these building blocks, simply add them to the customized game files. 

* Easy to make new data files to create new files to load from. 

* A programmer can also extend the Data Interfaces to implement their own readers/writers for different types of data files such as .XML or .JSON files. 


#### Other Features not yet Done

* Minimax AI Algorithm that can be implemented easily in the future by using our existing building blocks

* Ability to play multiple games at once (Add a higher level backend manager to create new instances of the backend for multiple games to run at once)

* Implement GameWriter and GameReader to support different files,such as .XML or .JSON files. Allow the user to specify what kinds of files on the 
UI to choose what kinds of files to read and write.