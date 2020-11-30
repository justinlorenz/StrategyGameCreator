package ooga.model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import ooga.exception.BadGameFileException;
import ooga.exception.MissingPropertyException;
import ooga.exception.SavingFileException;

/**
 * Class used to encapsulate properties class
 *
 * @author Loten Lhatsang
 */
public class PropertiesHandler implements PropertiesManager {

  private static final String RESOURCES = "resources/";

  private Properties myProperties;

  /**
   * Creates properties file
   *
   * @param file File used to create properties from
   */
  public void createProperties(File file) {
    myProperties = new Properties();
    try {
      myProperties.load(new FileInputStream(file));
    } catch (IOException e) {
      throw new BadGameFileException(e);
    }
  }

  /**
   * Gets property from key
   *
   * @param key Key to retrieve of
   * @return Returns value of the key stored in sim file. Throws BadSimulationException if key
   * doesn't exist.
   */
  public String getGameProperty(String key) {
    String propertyValue = myProperties.getProperty(key);
    if (propertyValue == null) {
      throw new MissingPropertyException(key);
    } else {
      return propertyValue;
    }
  }

  /**
   * Gets number of properties
   *
   * @return Number of properties
   */
  public int getKeySize() {
    return myProperties.size();
  }

  /**
   * Updates the property associated at key with value
   *
   * @param key   Key of the property to update
   * @param value Value to update the key's property.
   */
  public void updateProperty(String key, String value) {
    if (!myProperties.containsKey(key)) {
      throw new MissingPropertyException(key);
    }
    myProperties.put(key, value);
  }

  /**
   * Puts new property
   *
   * @param key key to store the new property at
   * @param value value of the new property
   */
  public void putProperty(String key, String value) {
    myProperties.put(key, value);
  }

  /**
   * Saves new file with properties
   *
   * @param filePath Path to save the properties at
   */
  public void save(String filePath) {
    try {
      FileWriter fileWriter = new FileWriter(filePath);
      myProperties.store(fileWriter, "Game Saved File");
    } catch (Exception e) {
      throw new SavingFileException("Error saving file to path", e);
    }
  }

}
