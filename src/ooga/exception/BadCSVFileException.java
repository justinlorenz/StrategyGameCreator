package ooga.exception;

public class BadCSVFileException extends BadFileException {

  private final String property;

  public BadCSVFileException(String property, Throwable e) {
    super("", e);
    this.property = property;
  }

  public BadCSVFileException(String property) {
    super("");
    this.property = property;
  }

  @Override
  public String getPropertyKey() {
    return this.property;
  }
}
