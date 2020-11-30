# Design Presentation

## Design Goals

### Flexible/Open
* When we first started this project, we wanted to have data driven game files, especially on customization on aesthetic
user experiences like profile pictures, themes, etc. 

* As we started working on the project, we realized we wanted to go deeper that just the UI customization, and also focus on allowing
the user to create variations on their games through their data as well. We refactored all our code to do more with building blocks, 
resulting in more flexibility/open than we were initially thinking about.

### Data Driven
* Data Drive mentality at the start, but realized that we weren't as data driven as we were hoping for. 
* We've closed the code by implementing our API's and design in such a way that the user would not need to write any new lines of code
to play variations on games, but instead only need to change the .game files. As a result, our code is closed for modification, but open
for extension, and thus to add new features the user would only need to extend existing code as opposed to modifying current code. 

## API's
* Two API’s
    *Old - Data API
    
    ```java
  public interface GameFileReader {
 
      GameEngine loadInGame(File gameFile);
  
      GameEngine loadDefaultGameFromProperties(String gameType, int gridSize, List<String> playerNames,
        List<String> playerTypes, List<String> playerImagePaths, String theme);
  
      String getGameProperty(String key);
  
      GameEngine getCurrGame();
  }
  
  public interface GridReader {
      PieceBoardStructure readInGameStates(PropertiesManager propertiesManager);
  }
  ```
  ```java
  
    public interface GameFileWriter {
    
      void saveGameFile(PropertiesManager propertiesManager, int gridSize,
          PieceDataRetriever pieceDataRetriever,
          String gameName, String author, String description);
    }
  
    public interface GridWriter {
      
      void saveGameGrid(String gameName, int gridSize, PieceDataRetriever gameController);
      
      String gridFileType();  
    }
  ```
    * Open to extension
        * Data interfaces allow for implementations of different type files to be saved and loaded
    * Support users 
        * Methods are easily used and do not need to be created with API. The method names are readable and describe
        their functionality easily.    
    * Changes
        1. DataReader API was split into different API's with separate functionality to provide more functionality. 
            * Split into GridWriter, GridReader, GameFileWriter, GameFileReader
        2. DataReader API for loadingInGames now uses more parameters
            * grid size, playerNames, playerTypes, playerImages, theme, and gameType
* New - BuildingBlocks API

    ```java
    public interface BoardMoveUpdate {  
      void updateBoard(BoardStructure currBoard, int playerNumber, int pieceI, int pieceJ,
          int destinationI, int destinationJ);  
    }
  
    public interface PromotionCondition {  
      void piecePromotionUpdate(BoardStructure currBoard, int pieceI, int pieceJ);  
    } 
    
    public interface ValidMoveCheck {  
      boolean isValidMove(BoardStructure currBoard, int pieceI, int pieceJ, int destinationI,
          int destinationJ);  
    }
  
    public interface WinCondition {  
      WinStatus getWinStatus(BoardStructure currBoard);  
    }
    
    ```
    
    * Open to extension
        * New building blocks can easily be added simply by implementing designed interfaces.
    * Support users 
        * Creating new building blocks only requires writing one new method. 
        * Previously created building blocks can be utilized  
    * Changes
        1. In sprint 2, we decided to go fully data driven and created these interfaces to support this.
      

## Use Cases
Use Cases
* Use Case 1: GameController called from the front end for userInputDetected, then calls backend to determine possible moves using building block APIs
    ```java
        public void userInputDetected(int i, int j) {
          myGame.gotUserInput(i, j);
        }
    ```
  
    ```java
      public void gotUserInput(int i, int j) {
        if (currPlayer.isHuman()) {
          if (!gameMoveHandler.arePiecesMoved() && currPlayer.makeMove(i, j, currBoard)) {
            checkIfUpdateTurn();
            updateGameStatus();
            clickedPiece = new EmptyPiece(0, 0);
          } else if (gameMoveHandler.arePiecesMoved() && updateClickedPiece(i, j) && currPlayer
              .makeMove(clickedPiece.getI(), clickedPiece.getJ(), i, j, currBoard)) {
            checkIfUpdateTurn();
            updateGameStatus();
            clickedPiece = new EmptyPiece(0, 0);
          }
        }
      }
    ```
* Use Case 2: Load in game - Controller API recieves File from frontend. The DataAPI then creates the corresponding games

    ```java
          public void loadInGame(File gameFile) {
            dataReader = new GameReader(propertiesManager, new CsvReader()); // Only reads csvs here
            myGame = dataReader.loadInGame(gameFile);
            gridSize = myGame.getGridSize();
            currGame = dataReader.getGameProperty(GAMETYPE);
            currTheme = dataReader.getGameProperty(THEME);
        
            profilePics.add(dataReader.getGameProperty(PLAYER_1_IMAGE));
            profilePics.add(dataReader.getGameProperty(PLAYER_2_IMAGE));
            playerNames.add(dataReader.getGameProperty(PLAYER_1_NAME));
            playerNames.add(dataReader.getGameProperty(PLAYER_2_NAME));
          }
    ```
  
  ```java
        public GameEngine loadInGame(File gameFile) {
          try {
            propertiesManager.createProperties(gameFile);
            return loadGameFromProperties(propertiesManager);
          } catch (BadFileException e) {
            throw e;
          } catch (Exception e) {
            throw new BadGameFileException(e);
          }
        }
  ```

## Design examples
* One stable: 
    * MVC Concept was stable, clear separation between the 3. 
    * Model is isolated such that it is independent from the view. The Controller serves as the bridge between both.
    This allows any person to implement any aspect of their own Model, View, and Controller
    
* One that has changed significantly: 
    * Data Driven Design aspect from the original plan
    * We were ahead of schedule; felt it was worth it to incorporate a data driven design in the backend as well.
    * Tradeoff: Led to bigger game files and more effort and parameters for the user to pick and create in order to make a new game.
    This is outweighed by the flexibility and extension that the design provides. 
