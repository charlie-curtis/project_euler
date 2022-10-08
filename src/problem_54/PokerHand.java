package problem_54;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PokerHand implements Comparable  {
  List<Card> cards;
  private int handResult;

  PokerHand(
    Card ...inputCards
  ) {
    //sort here
    cards = Arrays.stream(inputCards).collect(Collectors.toList());
    computeHandResult();
  }

  public static List<PokerHand> makeHands(String line)
  {
    String[] split = line.split(" ");
    PokerHand hand1, hand2;
    Card[] valueCards = new Card[5];
    for (int i = 0; i < 5; i++) {
      valueCards[i] = new Card(split[i].charAt(0), split[i].charAt(1));
    }

    Card[] suitCards = new Card[5];
    for (int i = 5; i < split.length; i++) {
      suitCards[i-5] = new Card(split[i].charAt(0), split[i].charAt(1));
    }

    hand1 = new PokerHand(valueCards);
    hand2 = new PokerHand(suitCards);
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
    for(int i = 0; i < this.cards.size(); i++) {
      if (!(cards.get(i).equals(otherHand.cards.get(i)))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareTo(Object o) {

    PokerHand otherHand = (PokerHand)o;
    if (this.handResult != otherHand.handResult) {
      return this.handResult > otherHand.handResult ? 1 : -1;
    }

    //get high card kicker

    return 0;
  }

  private void computeHandResult()
  {
  }

}

