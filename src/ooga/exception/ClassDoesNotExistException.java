package ooga.exception;

public class ClassDoesNotExistException extends BadFileException {

  private static final String CLASS_DOES_NOT_EXIST_PROPERTY_KEY = "ClassDoesNotExistException";

  public ClassDoesNotExistException(String message, Throwable e) {
    super(message, e);
  }

  public String getPropertyKey() {
    return CLASS_DOES_NOT_EXIST_PROPERTY_KEY;
  }

}
