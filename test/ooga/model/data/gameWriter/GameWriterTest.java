package ooga.model.data.gameWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import ooga.controller.GameController;
import ooga.exception.SavingFileException;
import ooga.exception.SavingGameFileException;
import ooga.model.data.GridWriter.GridWriter;
import ooga.model.data.PropertiesHandler;
import org.junit.jupiter.api.Test;

class GameWriterTest {


  private static final String GAME_NAME = "gameName";
  private static final String AUTHOR = "author";
  private static final String DESCRIPTION = "description";

  @Test
  void saveGameFileExceptionTest() {
    GridWriter gridWriter = mock(GridWriter.class);
    PropertiesHandler propertiesHandler = mock(PropertiesHandler.class);
    GameWriter gameWriter = new GameWriter(gridWriter);
    doNothing().when(gridWriter).saveGameGrid(anyString(), anyInt(), any());
    doThrow(SavingFileException.class).when(propertiesHandler).save(anyString());
    assertThrows(
        SavingGameFileException.class, ()-> gameWriter
            .saveGameFile(propertiesHandler, 0, new GameController(), GAME_NAME, AUTHOR, DESCRIPTION));
  }

  @Test
  void saveGameFileTest() {
    PropertiesHandler propertiesHandler = mock(PropertiesHandler.class);
    GridWriter gridWriter = mock(GridWriter.class);
    GameWriter gameWriter = new GameWriter(gridWriter);
    doNothing().when(propertiesHandler).save(anyString());
    gameWriter
        .saveGameFile(propertiesHandler, 0, new GameController(), GAME_NAME, AUTHOR, DESCRIPTION);
  }


}