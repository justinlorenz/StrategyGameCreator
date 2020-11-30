package ooga.exception;

/**
 * Exception for parsing bad data
 */
public class BadDataException extends BadFileException {

  private static final String BAD_DATA_EXCEPTION_KEY = "BadDataException";

  @Override
  public String getPropertyKey() {
    return BAD_DATA_EXCEPTION_KEY;
  }

  public BadDataException(String message, Throwable e) {
    super(message, e);
  }

  public BadDataException(String message) {
    super(message);
  }

}
