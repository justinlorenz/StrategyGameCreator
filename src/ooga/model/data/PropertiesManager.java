package ooga.model.data;

import java.io.File;

/**
 * Interface used for properties management. Interface used here to allow for
 * classes to contain Game Properties in flexible ways.
 *
 * @author Loten Lhatsang
 */
public interface PropertiesManager {

  /**
   * Creates properties file
   *
   * @param file File used to create properties from
   */
  void createProperties(File file);

  /**
   * Gets property from key
   *
   * @param key Key to retrieve of
   * @return Returns value of the key stored in sim file. Throws BadSimulationException if key
   * doesn't exist.
   */
  String getGameProperty(String key);

  /**
   * Updates the property associated at key with value
   *
   * @param key   Key of the property to update
   * @param value Value to update the key's property.
   */
  void updateProperty(String key, String value);

  /**
   * Puts new property
   *
   * @param key key to store the new property at
   * @param value value of the new property
   */
  void putProperty(String key, String value);

  /**
   * Saves new file with properties
   *
   * @param filePath Path to save the properties at
   */
  void save(String filePath);

}
