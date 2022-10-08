package problem_54;

import helpers.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokerHand implements Comparable  {
  List<Pair> cards;
  private int handResult;

  PokerHand(
    Pair ...inputCards
  ) {
    //sort here
    cards = Arrays.stream(inputCards).collect(Collectors.toList());
    computeHandResult();
  }

  public static List<PokerHand> makeHands(String line)
  {
    String[] split = line.split(" ");
    PokerHand hand1, hand2;
    Pair[] firstPairs = new Pair[5];
    for (int i = 0; i < 5; i++) {
      firstPairs[i] = new Pair(split[i].charAt(0), split[i].charAt(1));
    }

    Pair[] secondPairs = new Pair[5];
    for (int i = 5; i < split.length; i++) {
      secondPairs[i-5] = new Pair(split[i].charAt(0), split[i].charAt(1));
    }

    hand1 = new PokerHand(firstPairs);
    hand2 = new PokerHand(secondPairs);
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

  private int breakTie()
  {
    return -1;
  }

  private void computeHandResult()
  {
    this.handResult = HandResult.FLUSH;
  }

}

class HandResult {

  public static final int ROYAL_FLUSH = 10;
  public static final int STRAIGHT_FLUSH = 9;
  public static final int FOUR_OF_A_KIND = 8;
  public static final int FULL_HOUSE = 7;
  public static final int FLUSH = 6;
  public static final int STRAIGHT = 5;
  public static final int THREE_OF_A_KIND = 4;
  public static final int TWO_PAIR = 3;
  public static final int ONE_PAIR = 2;
  public static final int HIGH_CARD = 1;
}


