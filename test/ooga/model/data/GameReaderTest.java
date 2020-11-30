package ooga.model.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.List;
import ooga.exception.BadCSVFileException;
import ooga.exception.BadDataException;
import ooga.exception.BadFileException;
import ooga.exception.BadGameFileException;
import ooga.exception.MissingPropertyException;
import ooga.model.Game;
import ooga.model.GameEngine;
import ooga.model.data.GridReader.CsvReader;
import ooga.model.data.gameReader.GameReader;
import org.junit.jupiter.api.Test;

class GameReaderTest {

  private static final String WORKING_GAME_FILE_PATH = "dataTest/SampleWorking.game";
  private static final String ALL_MISSING_PROPERTIES_GAME_FILE_PATH = "dataTest/SampleMissingProperties.game";
  private static final String WIN_CONDITION_MISSING_PROPERTIES_GAME_FILE_PATH = "dataTest/SampleMissingWinConditionsProperty.game";
  private static final String BADLY_FORMATTED_INTEGER_PROPERTIES_GAME_PATH = "dataTest/gameSamples/IntegerPropertiesBadFormatting.game";
  private static final String BADLY_FORMATTED_BOOLEAN_PROPERTIES_GAME_PATH = "dataTest/gameSamples/BooleanPropertiesBadFormatting.game";
  private static final String MISSING_INITIAL_CONFIG_GAME_PATH = "dataTest/gameSamples/MissingInitialConfigurationProperty.game";
  private static final String SPECIAL_PIECE_GAME_PATH = "dataTest/gameSamples/SpecialPieceSample.game";
  private static final String BAD_IMAGE_PROPERTY_PATH = "dataTest/gameSamples/badImageProperty.game";


  private static final String FAKE_GAME_TYPE = "FAKE_GAME_TYPE";
  private static final String TIC_TAC_TOE = "TicTacToe";
  private static final int GRID_SIZE_3 = 3;
  private static final int GRID_SIZE_5 = 5;
  private static final List<String> PLAYER_NAMES = List.of("Player1", "Player2");
  private static final List<String> PLAYER_TYPES = List.of("Human", "Human");
  private static final List<String> IMAGE_PATHS = List.of("AmongUsGray.jpg", "AmongUsGray.jpg");
  private static final String THEME = "Duke";

  @Test
  void loadInTicTacToeGameTest() {
    File file = new File(WORKING_GAME_FILE_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    dataReader.loadInGame(file);
  }

  @Test
  void loadInGameExceptionTest() {
    File file = new File(WORKING_GAME_FILE_PATH);
    PropertiesHandler propertiesHandler = mock(PropertiesHandler.class);
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    doThrow(MissingPropertyException.class).when(propertiesHandler).createProperties(any());
    assertThrows(BadFileException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadInGameMissingAllPropertiesExceptionTest() {
    File file = new File(ALL_MISSING_PROPERTIES_GAME_FILE_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(MissingPropertyException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadInGameMissingWinConditionPropertiesExceptionTest() {
    File file = new File(WIN_CONDITION_MISSING_PROPERTIES_GAME_FILE_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(BadCSVFileException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadInDefaultGame3x3Test() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    dataReader.loadDefaultGameFromProperties(TIC_TAC_TOE, GRID_SIZE_3, PLAYER_NAMES,
        PLAYER_TYPES, IMAGE_PATHS, THEME);
  }

  @Test
  void loadInDefaultGame5x5Test() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    dataReader.loadDefaultGameFromProperties(TIC_TAC_TOE, GRID_SIZE_5, PLAYER_NAMES,
        PLAYER_TYPES, IMAGE_PATHS, THEME);
  }

  @Test
  void loadInDefaultGameExceptionTest() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(BadGameFileException.class, () -> dataReader.loadDefaultGameFromProperties(
        FAKE_GAME_TYPE, GRID_SIZE_5, PLAYER_NAMES, PLAYER_TYPES, IMAGE_PATHS, THEME));
  }

  @Test
  void loadingFileWithBadIntegerPropertiesTest() {
    File file = new File(BADLY_FORMATTED_INTEGER_PROPERTIES_GAME_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(BadDataException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadingFileWithBadBooleanPropertiesTest() {
    File file = new File(BADLY_FORMATTED_BOOLEAN_PROPERTIES_GAME_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(BadDataException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadingFileWithMissingInitialConfigurationPropertyTest() {
    File file = new File(MISSING_INITIAL_CONFIG_GAME_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(MissingPropertyException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadInDefaultGameMissingPropertyTest() {
    PropertiesHandler propertiesHandler = mock(PropertiesHandler.class);
    doThrow(MissingPropertyException.class).when(propertiesHandler).updateProperty(any(),any());

    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(MissingPropertyException.class, () -> dataReader.loadDefaultGameFromProperties(TIC_TAC_TOE, GRID_SIZE_5, PLAYER_NAMES,
        PLAYER_TYPES, IMAGE_PATHS, THEME));
  }

  @Test
  void loadInGameOtherExceptionTest() {
    File file = new File(WORKING_GAME_FILE_PATH);
    PropertiesHandler propertiesHandler = mock(PropertiesHandler.class);
    doThrow(RuntimeException.class).when(propertiesHandler).createProperties(file);

    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(BadGameFileException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadInBadImageGameExceptionTest() {
    File file = new File(BAD_IMAGE_PROPERTY_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    assertThrows(BadDataException.class, () -> dataReader.loadInGame(file));
  }

  @Test
  void loadInSpecialPieceGame() {
    File file = new File(SPECIAL_PIECE_GAME_PATH);
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    GameReader dataReader  = new GameReader(propertiesHandler, new CsvReader());
    GameEngine game = dataReader.loadInGame(file);
    assertTrue(game.doesUserEndTurn());
  }
}