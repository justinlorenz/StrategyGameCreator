package ooga.model.data.GridReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ooga.exception.BadCSVFileException;
import ooga.exception.MissingPropertyException;
import ooga.model.data.PropertiesManager;
import ooga.model.PieceBoardStructure;
import ooga.model.pieces.EmptyPiece;
import ooga.model.pieces.NormalPiece;
import org.junit.jupiter.api.Test;

class CsvReaderTest {

  private static final String NON_EXISTING_CSV_FILE = "NonExistingCSVFile";
  private static final String ALL_EMPTY_GRID = "dataTest/csvSamples/SampleWorkingCSV.csv";
  private static final String WRONG_GRID_SIZE_CSV = "dataTest/csvSamples/WrongGridSizeCSV.csv";
  private static final String ALL_PLAYER_1_NORMAL_PIECES = "dataTest/csvSamples/AllPlayer1NormalPieces.csv";
  private static final String GRID_MISSING_PIECE = "dataTest/csvSamples/GridMissingAPiece.csv";
  private static final String BAD_GRID_SIZE_FORMAT = "dataTest/csvSamples/BadGridSizeFormatCSV.csv";

  @Test
  void readInGamesStatesAllEmptyGridTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    when(propertiesManager.getGameProperty(anyString())).thenReturn(ALL_EMPTY_GRID);
    PieceBoardStructure pieceBoardStructure = csvReader.readInGameStates(propertiesManager);
    assertEquals(3, pieceBoardStructure.getGridSize());
    for(int i=0; i<3; i++) {
      for(int j=0; j<3; j++) {
        assertEquals(new EmptyPiece(0,0), pieceBoardStructure.getPiece(i,j));
      }
    }
  }

  @Test
  void readInGamesStatesAllNormalPiecesGridTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    when(propertiesManager.getGameProperty(anyString())).thenReturn(ALL_PLAYER_1_NORMAL_PIECES);
    PieceBoardStructure pieceBoardStructure = csvReader.readInGameStates(propertiesManager);
    assertEquals(3, pieceBoardStructure.getGridSize());
    for(int i=0; i<3; i++) {
      for(int j=0; j<3; j++) {
        assertEquals(new NormalPiece(1,"NormalPiece",0,0), pieceBoardStructure.getPiece(i,j));
      }
    }
  }

  @Test
  void readInGamesStatesMissingCSVFileTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    when(propertiesManager.getGameProperty(anyString())).thenReturn(NON_EXISTING_CSV_FILE);
    assertThrows(BadCSVFileException.class, ()-> csvReader.readInGameStates(propertiesManager));
  }

  @Test
  void readInGamesStatesWrongGridSizeCSVFileTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    when(propertiesManager.getGameProperty(anyString())).thenReturn(WRONG_GRID_SIZE_CSV);
    assertThrows(BadCSVFileException.class, ()-> csvReader.readInGameStates(propertiesManager));
  }

  @Test
  void readInGamesStatesMissingPieceInCSVFileTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    when(propertiesManager.getGameProperty(anyString())).thenReturn(GRID_MISSING_PIECE);
    assertThrows(BadCSVFileException.class, ()-> csvReader.readInGameStates(propertiesManager));
  }

  @Test
  void badGridSizeFormatCSVFileTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    when(propertiesManager.getGameProperty(anyString())).thenReturn(BAD_GRID_SIZE_FORMAT);
    assertThrows(BadCSVFileException.class, ()-> csvReader.readInGameStates(propertiesManager));
  }

  @Test
  void readInGamesStatesBadCSVFileExceptionTest() {
    CsvReader csvReader = new CsvReader();
    PropertiesManager propertiesManager = mock(PropertiesManager.class);
    doThrow(RuntimeException.class).when(propertiesManager).getGameProperty(anyString());
    assertThrows(BadCSVFileException.class, ()-> csvReader.readInGameStates(propertiesManager));
  }

}