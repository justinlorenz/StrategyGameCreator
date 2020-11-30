# OOGA Final Game API Changes

## Names and NetIDs
* Justin Lorenz (jml166)
* Jerry Fang (jdf58)
* Loten Lhatsang (ltl10)
* Jason Dong (jd385)

## OOGA Final Game API Changes

*Game API
    * Added additional methods for getClickedPieceData and getPieceData
        * Implemented to easily pass information between the view and the model through the controller
        using our own class
    * Added method for getPossibleMove
        * Implemented for getting the legal moves of a piece to make, as well as let the frontend have a visual 
        representation for the user to see what moves they can make with the piece. 
    * Added boolean for playAITurn
        * Initially we thought the AI Turn would be fully handled in the backend, but we realized that the
        frontend is partially responsible for communicating with the backend when it's the AI's turn (after user input)

*Data API
    * Changes
        * Loading in default games, added instance variables that needed to be passed in. Originally we really only accounted for players, but also
        need to take into account the grid size, playerNames, playerTypes, playerImages, theme, and gameType. 
        * Overall, we decided to split the DataReaderAPI up into multiple separate APIs to allow for more flexibility in future implementations
            * For example, we had DataReader handling the CSVReader AND the game creation, but that was split up
    * Additions - All new additions allow for open-ended extension for different types of files instead of just .csv files
        * GridWriter 
            * We created this API to easily save the grid state. 
        * GridReader 
            * This API was created to easily read in any saved grids that we have. 
        *GameFileReader 
            * This API was created to handle reading through the game file to find all the properties needed to create the game.
        *GameFileWriter 
            * We created this API to easily create the game file when the game is saved. 

*Controller API
    * Changes
        * Addition of getters and setters to be exclusively used for creating a default game, allowing for the user to customize all aspects of the GUI
        * We used to have a method addNewPlayerToGame, but now it is coded with the assumption of 2 players as strategy games implemented don't work beyond 2 players
        * No longer using a step() function, due to running into errors with performance. Continually updating the board with new images was no longer needed and 
        hardware intensive. We moved this step function inside the view to only update after feedback from the model (i.e. gameStatus, if the player's turn was completed, etc.)
        * All methods in our original Controller API that dealt with creating the game was moved inside the DataAPI to support open/closed and single responsibility.
        * Saving games used to take no parameters, but now they are taken in from the frontend (Game name, author, description). 

## New API Additions

*Player API
    * Created for anyone to create a player in any game that they want

*Building Blocks
    * Added to allow for all our building blocks be their own API. Added to allow for extension in the future with more building blocks
        * BoardMoveUpdate
        * PieceMove
        * PromotionCondition
        * ValidMoveCheck
        * WinCondition

*Game Move Handler
    * Created to be used by our game engine as well as to allow anyone to use this to handle moves in their own game. 
    
*Special Piece Handler
    * Created to separate responsibility of special pieces from GameMoveHandler

*Pieces
    * Created to represent all pieces on the board, supports extension as well in that you can create your own pieces.