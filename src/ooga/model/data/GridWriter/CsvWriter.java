package ooga.model.data.GridWriter;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ooga.controller.PieceData;
import ooga.controller.PieceDataRetriever;
import ooga.exception.SavingFileException;

/**
 * Class used to write grid files to save grid data
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class CsvWriter implements GridWriter {

  private static final String CSV = ".csv";

  /**
   * Saves grid into file
   *
   * @param simName Name of game saved
   * @param gridSize Size of grid being saved
   * @param gameController Controller which is used to retrieve pieceData
   */
  @Override
  public void saveGameGrid(String simName, int gridSize, PieceDataRetriever gameController) {
    String filePath = "data/savedGridData/" + simName + CSV;
    File file = new File(filePath);
    try {
      FileWriter outputFile = new FileWriter(file);
      CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, '"', "\n");
      String[] header = {Integer.toString(gridSize)};
      writer.writeNext(header);
      for (int r = 0; r < gridSize; r++) {
        String[] rowData = new String[gridSize];
        for (int c = 0; c < gridSize; c++) {
          PieceData pieceData = gameController.getPieceData(r, c);
          rowData[c] = pieceData.getPieceId() + " " + pieceData.getPlayerNumber();
        }
        writer.writeNext(rowData);
      }
      writer.close();
    } catch (IOException e) {
      throw new SavingFileException("Error saving CSV file", e);
    }
  }

  /**
   * Gets file extension type - .csv
   *
   * @return Return grid file extension as String
   */
  @Override
  public String gridFileType() {
    return CSV;
  }

}
