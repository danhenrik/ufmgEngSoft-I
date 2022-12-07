 

// Gere o processo eleitoral
public class CertifiedProfessional extends TSEProfessional {
  public void startSession(Election election, String password) {
    election.start(password);
  }

  public void endSession(Election election, String password) {
    election.finish(password);
  }

  public String getFinalResult(Election election, String password) {
    return election.getResults(password);
  }

  public static class Builder {
    protected String user;
    protected String password;

    public Builder user(String user) {
      this.user = user;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public CertifiedProfessional build() {
      if (user == null)
        throw new IllegalArgumentException("user mustn't be null");

      if (user.isEmpty())
        throw new IllegalArgumentException("user mustn't be empty");

      if (password == null)
        throw new IllegalArgumentException("password mustn't be null");

      if (password.isEmpty())
        throw new IllegalArgumentException("password mustn't be empty");

      return new CertifiedProfessional(
          this.user,
          this.password);
    }
  }

  protected CertifiedProfessional(
      String user,
      String password) {
    super(user, password);
  }

  public String getUser() {
    return this.user;
  }
}
