package ooga.model;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import ooga.model.data.PropertiesHandler;

/**
 * Class for LeaderboardWriter
 */
public class LeaderboardWriter {

  private final String name;
  private final HashMap<String, Integer> ranksMap = new HashMap<>();
  private HashMap<String, Integer> sortedRanksMap = new LinkedHashMap<>();

  /**
   * Constructor for LeaderboardWriter
   *
   * @param name Name to write into Leaderboard
   */
  public LeaderboardWriter(String name) {
    this.name = name;
  }

  /**
   * Method to write into the leaderboard
   */
  public void writeToLeaderBoard() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File("data/leaderboardLog/leaderboard.lblog");
    propertiesHandler.createProperties(file);

    for (int i = 1; i < propertiesHandler.getKeySize() + 1; i++) {
      String[] playerInfo = propertiesHandler.getGameProperty(String.format("Player%d", i))
          .split(",");
      ranksMap.putIfAbsent(playerInfo[0], Integer.parseInt(playerInfo[1]));
    }

    ranksMap.merge(name, 1, Integer::sum);
    sortRanksMap();
    updateLogFiles(propertiesHandler);

    propertiesHandler.save("data/leaderboardLog/leaderboard.lblog");
  }

  private void updateLogFiles(PropertiesHandler propertiesHandler) {
    int playerNum = 1;
    for (Map.Entry<String, Integer> entry : sortedRanksMap.entrySet()) {
      String key = entry.getKey();
      int score = entry.getValue();
      String playerNumKey = String.format("Player%d", playerNum);
      String playerValue = String.format("%s,%d", key, score);
      propertiesHandler.putProperty(playerNumKey, playerValue);

      playerNum++;
    }
  }

  private void sortRanksMap() {
    sortedRanksMap = new LinkedHashMap<>();
    ranksMap.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(x -> sortedRanksMap.put(x.getKey(), x.getValue()));
  }

}
