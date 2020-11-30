package ooga.model.data.GridWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.opencsv.CSVWriter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import ooga.controller.PieceData;
import ooga.controller.PieceDataRetriever;
import ooga.exception.SavingFileException;
import org.junit.jupiter.api.Test;

class CsvWriterTest {

  private static final String GAME_NAME = "gameName";
  private static final String EMPTY = "Empty";
  private static final int GRID_SIZE = 3;
  private static final String BROKEN_PATH = "broken/Path/";

  @Test
  void saveGameCSVTest() {
    PieceDataRetriever pieceDataRetriever = mock(PieceDataRetriever.class);
    CsvWriter csvWriter = new CsvWriter();
    when(pieceDataRetriever.getPieceData(anyInt(),anyInt())).thenReturn(new PieceData(EMPTY, 1,0,0));
    csvWriter.saveGameGrid(GAME_NAME, GRID_SIZE, pieceDataRetriever);

    Path path = Paths.get("data/savedGridData/"+GAME_NAME+".csv");
    File file = new File(path.toAbsolutePath().normalize().toString());
    assertTrue(file.delete());
  }

  @Test
  void saveGameCSVExceptionTest() {
    PieceDataRetriever pieceDataRetriever = mock(PieceDataRetriever.class);
    CsvWriter csvWriter = new CsvWriter();
    assertThrows(SavingFileException.class, ()-> csvWriter.saveGameGrid(BROKEN_PATH, GRID_SIZE, pieceDataRetriever));
  }

  @Test
  void getCsvString() {
    CsvWriter csvWriter = new CsvWriter();
    assertEquals(".csv", csvWriter.gridFileType());
  }
}