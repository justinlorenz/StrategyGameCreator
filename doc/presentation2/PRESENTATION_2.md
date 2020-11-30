## Project: Strategy Games (Checkers, Othello, TicTacToe, Connect4)
### Group 1: JuJaJeLo

####Planned series of steps/interactions
1. Create new game of TicTacToe 3x3 with Duke Theme
    - Go through GUI customizations
    - Play a game, let one player win 
        - Talk about how the model continuously checks the boardStructure to see if a player has won, game drawn, or if game is still playing. Sent to controller
    - Reset the game, show a tie
    - Play through a few turns of game
    - Save game and then exit
        - Show how .game file is saved
        - Show how a grid is saved
    - Go back to first game menu
    - Load the saved game
        - Let someone win
        - Reset to show that it resets back to loaded file
    - Show a game with Random AI
    - Save the game midway through
2. Show off Checkers game
    - Play through a few turns
    - Show a working jump

Unit Tests and Exceptions:
	Try loading a game missing properties and show off the exception being thrown in console

	
Unhappy- Show off mockito unit tests checking invalid moves,
Unhappy some exception handling -
Happy - Victory cases for TicTacToe
Show a frontend test with a DialogBox happy tests


### Sprint 1 Goals 
* Get a working test implementation with random AI
* Set up a game board for checkers, connect four, othello, etc.
* Set up core classes with abstractions/interfaces/clean inheritance hierarchy
* Use .CSS and .properties files to start with
* Create a working version of tic tac toe
* Create a few custom exceptions to be thrown

* All features implemented successfully, thinking everything out before starting to code was helpful for the backend
* Features that were hard to implement and impeded progress
	* We were having trouble trying to figure out how to represent states because we wanted to provide lots of flexibility so we decided on using an overall piece class to represent a state of a piece. 
	* We were calling a step function that updated our board every frame. However, we were using picture overlays so this caused our game to lag out and use all of our memory so we had to figure out how to correctly update the display board every turn.


* Communication was good, everyone shows up to nightly meetings and on GroupMe. We can work on starting the week with generating a list of issues to complete throughout the week and consistently update/resolve them as things are implemented



### Sprint 2 Plan:
* Transition our backend into more heavily data driven code (Justin,Loten,Jerry)
* Implement a fully working multiplayer of Checkers, Othello, and Connect Four (Justin and Loten)
* Clean up our game controller to be more concise and flexible (Everyone)
* Catch our custom exceptions in the front end and display an alert box (Jason)
* When a piece is clicked in 2 turn games, display the possible moves on the board (Jason)
* Continue adding optionals if we have time (Everyone)
