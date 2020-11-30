## Project: Strategy Games (Checkers, Othello, TicTacToe, Connect4)
### Group 1: JuJaJeLo

#### Sprint 2 Milestones Completed:
* Completely redid how users add new games to play. Instead of adding new code, users use building blocks to create their own games through a .game data file.
* Implement a fully working multiplayer of Checkers, Connect Four, and added new variations of current games. Began implementing Othello game.
* Added a chat room  
* Cleaned up our game controller to be more concise and flexible (Everyone)
* Catch our custom exceptions thrown in the front end and display an alert box (Jason)
* When a piece is clicked in checkers board is highlighted to display what piece is selected 

### Features Hard to Implement
* Data Driven Switch:
	* The switch to a fully data driven program using the various “building block” classes required us to refactor the majority of our backend, and also took multiple hours of planning at the beginning of the week.
	* Our previous implementation required creating new classes for each new type of game. This lead to game specific bugs and duplication of code in different games. By spliting up the "features" of the game into building blocks, our code became more organized and readable. It also reduced duplication as games could share building blocks for similar features.

### Communication
* Communication was good, everyone shows up to nightly meetings and on GroupMe. We can work on starting the week with generating a list of issues to complete throughout the week and consistently update/resolve them as things are implemented. Specifically, the creation of the "building blocks" was not an individual event, but rather the four of us wrote the code together during one of our meetings.

#### Sprint Complete Plan:
* Fix up the bugs in some of the games 
* Create a leaderboard that ranks players based on how many games they have won
* Create a HUD that shows whose turn it is
* Implement a random AI
* When a piece is clicked in checkers display the possible moves on the board 
* Change the data reader to be more flexible so that we can use different types of files 
* Continue adding optionals if we have time 
	* Dynamically change game rules while running game

		


