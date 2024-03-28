import java.util.*;

public class Deck {
  private ArrayList<Card> theDeck;

  public Deck() {
    theDeck = new ArrayList<Card>();
  }
  public void createDeck() {
    for (Suit cardSuit : Suit.values()) {
      for (Value cardValue : Value.values()) {
        theDeck.add (new Card(cardSuit,cardValue));
      }
    }
    Shuffle();
  }
  public void Shuffle() {
    Collections.shuffle(theDeck, new Random());
  }
  //Formatting to print a list of all cards in the Deck
  public String toString() {
    String deckList = "";
    int i = 1;
    for (Card aCard : theDeck) {
      deckList += i + ") " + aCard.toString() + "\n";
      i ++;
    }
    return deckList;
  }
  public Card getCard(int i) {
    return theDeck.get(i);
  }
  public int getSize() {
    return theDeck.size();
  }
  public void removeCard(int i) {
    theDeck.remove(i);
  }
  //Pulls top Card from deck then removes it form deck
  public void drawCard(Deck fromDeck) {
    theDeck.add(fromDeck.getCard(0));
    fromDeck.removeCard(0);
  }
  public void addCard(Card card) {
    theDeck.add(card);
  }
  //Returns Card from hand to deck
  public void returnCards(Deck moveTo) {
    int movedDeckSize = theDeck.size();
    for (int i = 0; i < movedDeckSize; i++) {
      moveTo.addCard(theDeck.get(i));
    }
    for (int i = 0; i < movedDeckSize; i++) {
      theDeck.remove(0);
    }
  }
  public int handValue() {
    int total = 0, aces = 0;
    for (Card aCard : theDeck) {
      switch(aCard.getValue()) {
        case ACE: aces += 1; break;
        case TWO: total += 2; break;
        case THREE: total += 3; break;
        case FOUR: total += 4; break;
        case FIVE: total += 5; break;
        case SIX: total += 6; break;
        case SEVEN: total += 7; break;
        case EIGHT: total += 8; break;
        case NINE: total += 9; break;
        case TEN: total += 10; break;
        case JACK: total += 10; break;
        case QUEEN: total += 10; break;
        case KING: total += 10; break;
      }
    }
    for (int i = 0; i < aces; i++) {
      if (total > 10) {
        total += 1;
      }
      else {
        total += 11;
      }
    }
    return total;
  }
}
