package problem_54;

import problem_54.calculator.FiveCardDrawCalculator;
import problem_54.calculator.HandCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHand implements Comparable {
  List<Card> cards;
  private final HandCalculator calculatorStrategy;

  PokerHand(
    Card... inputCards
  ) {
    //sort here
    cards = Arrays.stream(inputCards).collect(Collectors.toList());
    calculatorStrategy = new FiveCardDrawCalculator(cards);
  }

  public static List<PokerHand> makeHands(String line) {
    String[] split = line.split(" ");
    PokerHand hand1, hand2;
    Card[] firstHandCards = new Card[5];
    for (int i = 0; i < 5; i++) {
      firstHandCards[i] = new Card(split[i].charAt(0), split[i].charAt(1));
    }

    Card[] secondHandCards = new Card[5];
    for (int i = 5; i < split.length; i++) {
      secondHandCards[i - 5] = new Card(split[i].charAt(0), split[i].charAt(1));
    }

    hand1 = new PokerHand(firstHandCards);
    hand2 = new PokerHand(secondHandCards);
    return List.of(hand1, hand2);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PokerHand otherHand)) {
      return false;
    }
    if (otherHand.cards.size() != this.cards.size()) {
      return false;
    }
    for (int i = 0; i < this.cards.size(); i++) {
      if (!(cards.get(i).equals(otherHand.cards.get(i)))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareTo(Object o) {

    PokerHand otherHand = (PokerHand) o;
    return calculatorStrategy.compareTo(otherHand.calculatorStrategy);
  }

  /**
   * Syntactic sugar for compareTo
   */
  public boolean isBetterThan(PokerHand otherHand) {
    return this.compareTo(otherHand) == 1;
  }
}


