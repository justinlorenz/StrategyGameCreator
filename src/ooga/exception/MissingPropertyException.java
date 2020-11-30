package ooga.exception;

public class MissingPropertyException extends BadFileException {

  private static final String MISSING_PROPERTIES = "MissingPropertiesException";

  public MissingPropertyException(String message, Throwable e) {
    super(message, e);
  }

  public MissingPropertyException(String message) {
    super(message);
  }

  public String getPropertyKey() {
    return MISSING_PROPERTIES;
  }

}
