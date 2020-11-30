package ooga.exception;

public class SavingFileException extends RuntimeException {

  public SavingFileException(String message, Throwable e) {
    super(message, e);
  }

}
