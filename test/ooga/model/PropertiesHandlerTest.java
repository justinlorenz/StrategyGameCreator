package ooga.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import ooga.exception.BadFileException;
import ooga.exception.MissingPropertyException;
import ooga.exception.SavingFileException;
import ooga.model.data.PropertiesHandler;
import org.junit.jupiter.api.Test;

class PropertiesHandlerTest {

  private static final String WORKING_FILE_PATH = "dataTest/PropertiesTest.game";
  private static final String BROKEN_FILE_PATH = "broken/broken";
  private static final String PROPERTY1 = "Property1";
  private static final String PROPERTY2 = "Property2";
  private static final String FAKE_PROPERTY = "FakeProperty";
  private static final String SUCCESS_MESSAGE = "Success";
  private static final String FAILURE_MESSAGE = "Failure";


  @Test
  void getPropertiesTest() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File(WORKING_FILE_PATH);
    propertiesHandler.createProperties(file);
    assertEquals(SUCCESS_MESSAGE, propertiesHandler.getGameProperty(PROPERTY1));
  }

  @Test
  void updatePropertiesTest() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File(WORKING_FILE_PATH);
    propertiesHandler.createProperties(file);
    assertEquals(FAILURE_MESSAGE, propertiesHandler.getGameProperty(PROPERTY2));
    propertiesHandler.putProperty(PROPERTY2, SUCCESS_MESSAGE);
    assertEquals(SUCCESS_MESSAGE, propertiesHandler.getGameProperty(PROPERTY2));
  }

  @Test
  void createPropertiesException() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File(BROKEN_FILE_PATH);
    assertThrows(BadFileException.class, ()->propertiesHandler.createProperties(file));
  }

  @Test
  void saveException() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File(WORKING_FILE_PATH);
    propertiesHandler.createProperties(file);
    assertThrows(SavingFileException.class, ()->propertiesHandler.save(BROKEN_FILE_PATH));
  }

  @Test
  void updatePropertiesExceptionTest() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File(WORKING_FILE_PATH);
    propertiesHandler.createProperties(file);
    assertEquals(FAILURE_MESSAGE, propertiesHandler.getGameProperty(PROPERTY2));
    assertThrows(MissingPropertyException.class, ()->propertiesHandler.updateProperty(FAKE_PROPERTY, SUCCESS_MESSAGE));
  }

  @Test
  void getPropertiesExceptionTest() {
    PropertiesHandler propertiesHandler = new PropertiesHandler();
    File file = new File(WORKING_FILE_PATH);
    propertiesHandler.createProperties(file);
    assertThrows(MissingPropertyException.class, ()->propertiesHandler.getGameProperty(FAKE_PROPERTY));
  }

}