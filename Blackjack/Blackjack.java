import java.util.Scanner;

public class Blackjack {

  public static void main (String[]args) {
    System.out.println("***********************\nWelcome to Blackjack!\n***********************");

    Deck playDeck = new Deck();
    playDeck.createDeck();

    Deck playerHand = new Deck();
    Deck dealerHand = new Deck();

    int playerBalance = 25000;

    Scanner userInput = new Scanner(System.in);

    //Game round
    while (playerBalance > 0) {

      //Player balance and players bet amount
      System.out.println("Your Balance: $"+playerBalance+ "\nHow much would you like to bet?");
      int playerBet = userInput.nextInt();
      if (playerBet >= playerBalance) {
        playerBet = playerBalance;
        System.out.println("ALL-IN!");
      }

      //Dealing cards to player & dealer w/ interchanging deals
      playerHand.drawCard(playDeck);
      dealerHand.drawCard(playDeck);
      playerHand.drawCard(playDeck);
      dealerHand.drawCard(playDeck);


      boolean endRound = false;

      while (true) {
        System.out.println("\nYour hand: " + playerHand.handValue());
        System.out.println(playerHand);

        // Blackjack instance
        if (playerHand.handValue() == 21 && playerHand.getSize() == 2 && endRound == false) {
          System.out.println("BLACKJACK!");
          playerBalance += (playerBet*1.25);
          System.out.println("+$" + playerBet*1.25);
          endRound = true;
          break;
        }

        System.out.println("Dealer's hand:\n" + dealerHand.getCard(0) + "\n[HIDDEN]");

        //Player Choices
        System.out.println("(1)Hit or (2)Stand");
        int response = userInput.nextInt();

        //Hit or Stand Choices
        if (response == 1) {
          playerHand.drawCard(playDeck);
          if (playerHand.handValue() > 21) {
            System.out.println("Bust. With hand of: "+ playerHand.handValue() + "\n" + playerHand + "-$" + playerBet);
            playerBalance -= playerBet;
            endRound = true;
            break;
          }
        }
        else if (response == 2) {
          break;
        }
      }
      System.out.println("\nDealer Hand: " + dealerHand.handValue() + "\n" + dealerHand);

      //Dealer hits @ <= 16 Stands at >= 17
      while(dealerHand.handValue() < 17 && endRound == false) {
        dealerHand.drawCard(playDeck);
        System.out.println("Dealer Draws: " + "<"+dealerHand.getCard(dealerHand.getSize()-1)+">" + " for a hand of: " + dealerHand.handValue());
      }

      //Dealer high hand instance
      if (dealerHand.handValue() > playerHand.handValue() && dealerHand.handValue() <= 21 && endRound == false) {
        System.out.println("House Wins: -$" + playerBet);
        playerBalance -= playerBet;
        endRound = true;
      }

      //House Bust instance
      if (dealerHand.handValue() > 21 && endRound == false) {
        System.out.println("House busts! You win. +$" + playerBet);
        playerBalance += playerBet;
        endRound = true;
      }

      //Push (Tie) instance
      if (playerHand.handValue() == dealerHand.handValue() && endRound == false) {
        System.out.println("Push");
        endRound = true;
      }

      //High hand win instance
      if (playerHand.handValue() > dealerHand.handValue() && endRound == false) {
        System.out.println("You win! +$" + playerBet);
        playerBalance += playerBet;
        endRound = true;
      }

      //returning cards to hand
      playerHand.returnCards(playDeck);
      dealerHand.returnCards(playDeck);
      System.out.println("*************\nEnd of hand.\n*************");
      playDeck.Shuffle();
    }
    if (playerBalance == 0) {
      System.out.println("You're out of money!");
    }
  }
}
