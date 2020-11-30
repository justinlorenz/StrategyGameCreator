package ooga.model.data.GridReader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import ooga.exception.BadCSVFileException;
import ooga.exception.BadFileException;
import ooga.model.PieceBoardStructure;
import ooga.model.data.PropertiesManager;
import ooga.model.pieces.Piece;
import ooga.model.pieces.PieceTypeFactory;

/**
 * Class which reads csv data
 *
 * @author Loten Lhatsang
 * @author Justin Lorenz
 * @author Jerry Fang
 */
public class CsvReader implements GridReader {

  private static final String CSV_EXCEPTION_GRID_SIZE_KEY = "CSVGridSizeError";
  private static final String CSV_EXCEPTION_BAD_GRID_SIZE = "CSVBadGridSize";
  private static final String CSV_EXCEPTION_BAD_CSV_FILE = "CSVBadCSVFile";
  private static final String CSV_EXCEPTION_BAD_CSV_FORMAT = "CSVBadFormat";

  /**
   * Reads grid from csv file
   *
   * @param propertiesManager PropertiesManager which encapsulates game properties
   * @return Encapsulated PieceBoardStructure from read in grid
   */
  @Override
  public PieceBoardStructure readInGameStates(PropertiesManager propertiesManager) {
    try {
      List<String[]> csvData = readAll(propertiesManager.getGameProperty("InitialConfiguration"));
      int size = handleGridSize(csvData);
      handleBadSizing(size, csvData);
      PieceBoardStructure gamePieces = new PieceBoardStructure(size);
      for (int i = 1; i < csvData.size(); i++) {
        for (int j = 0; j < csvData.get(1).length; j++) {
          String[] pieceInfo = csvData.get(i)[j].split(" ");
          int playerNum = Integer.parseInt(pieceInfo[1].trim());
          String pieceType = pieceInfo[0].trim();
          gamePieces.addNewPiece(getPieceType(pieceType, playerNum, i - 1, j), i - 1, j);
        }
      }
      return gamePieces;
    } catch (BadFileException e) {
      throw e;
    } catch (Exception e) {
      throw new BadCSVFileException(CSV_EXCEPTION_BAD_CSV_FORMAT);
    }
  }

  private void handleBadSizing(int size, List<String[]> csvData) {
    if (size != csvData.size() - 1) {
      throw new BadCSVFileException(CSV_EXCEPTION_GRID_SIZE_KEY);
    }
    for (int i = 1; i < csvData.size(); i++) {
      if (size != csvData.get(i).length) {
        throw new BadCSVFileException(CSV_EXCEPTION_GRID_SIZE_KEY);
      }
    }
  }

  private int handleGridSize(List<String[]> csvData) {
    try {
      int size = Integer.parseInt(csvData.get(0)[0].replaceAll("[^\\p{Graph}\n\r\t ]", ""));
      return size;
    } catch (Exception e) {
      throw new BadCSVFileException(CSV_EXCEPTION_BAD_GRID_SIZE, e);
    }
  }

  private List<String[]> readAll(String filePath) {
    try {
      CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(filePath)));
      return csvReader.readAll();
    } catch (CsvException | IOException e) {
      throw new BadCSVFileException(CSV_EXCEPTION_BAD_CSV_FILE, e);
    }
  }

  /**
   * Uses reflection to create a new piece based off gameType, pieceType, and playerNumber
   */
  private Piece getPieceType(String pieceType, int playerNumber, int xCor, int yCor) {
    PieceTypeFactory pieceTypeFactory = new PieceTypeFactory();
    return pieceTypeFactory.getPieceType(pieceType, playerNumber, xCor, yCor);
  }
}
