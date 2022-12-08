import java.util.Set;

public class FederalDeputy extends Candidate {
  protected final String state;

  public static class Builder {
    protected String name;
    protected String party;
    protected int number;
    protected String state;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder party(String party) {
      this.party = party;
      return this;
    }

    public Builder number(int number) {
      this.number = number;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      return this;
    }

    public FederalDeputy build() {
      if (number <= 0)
        throw new IllegalArgumentException("number mustn't be less than or equal to 0");

      if (name == null)
        throw new IllegalArgumentException("name mustn't be null");

      if (name.isEmpty())
        throw new IllegalArgumentException("name mustn't be empty");

      if (party == null)
        throw new IllegalArgumentException("party mustn't be null");

      if (party.isEmpty())
        throw new IllegalArgumentException("party mustn't be empty");

      if (state == null)
        throw new IllegalArgumentException("state mustn't be null");

      if (state.isEmpty())
        throw new IllegalArgumentException("state mustn't be empty");

      Set<String> validStates = Set.of("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
          "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");

      if (!validStates.contains(state))
        throw new IllegalArgumentException("state is invalid");

      return new FederalDeputy(
          this.name,
          this.party,
          this.number,
          this.state);
    }
  }

  protected FederalDeputy(
      String name,
      String party,
      int number,
      String state) {
    super(name, party, number);
    this.state = state;
  }

  @Override
  public String toString() {
    return super.name + super.party + super.number + this.state;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (!(obj instanceof FederalDeputy))
      return false;

    var fd = (FederalDeputy) obj;

    return this.toString().equals(fd.toString());
  }

  public String getState(){
    return this.state;
  }
}