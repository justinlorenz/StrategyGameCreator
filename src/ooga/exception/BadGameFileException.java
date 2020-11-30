package ooga.exception;

public class BadGameFileException extends BadFileException {

  private static final String BAD_GAME_FILE_EXCEPTION = "BadGameFileException";

  @Override
  public String getPropertyKey() {
    return BAD_GAME_FILE_EXCEPTION;
  }

  public BadGameFileException(Throwable e) {
    super("", e);
  }

  public BadGameFileException(String message) {
    super(message);
  }

}
