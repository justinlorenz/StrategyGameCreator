package ooga.exception;

public abstract class BadFileException extends RuntimeException {

  public abstract String getPropertyKey();

  public BadFileException(String message, Throwable e) {
    super(message, e);
  }

  public BadFileException(String message) {
    super(message);
  }

}
