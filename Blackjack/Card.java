public class Card {
  private Suit suit;
  private Value value;

  public Card(Suit s, Value v) {
    suit = s;
    value = v;
  }
  public String toString() {
    return value.toString() + " of " + suit.toString();
  }
  public Value getValue() {return value;}

  public Suit getSuit() {return suit;}
}
