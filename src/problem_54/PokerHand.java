package problem_54;

import helpers.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    Card<Character,Character>[] suitCards = new Card[5];
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

class HandResult implements Comparable {

  private static final int ROYAL_FLUSH = 10;
  private static final int STRAIGHT_FLUSH = 9;
  private static final int FOUR_OF_A_KIND = 8;
  private static final int FULL_HOUSE = 7;
  private static final int FLUSH = 6;
  private static final int STRAIGHT = 5;
  private static final int THREE_OF_A_KIND = 4;
  private static final int TWO_PAIR = 3;
  private static final int ONE_PAIR = 2;
  private static final int HIGH_CARD = 1;

  private int result;
  private Card cardStart;
  private Card kicker;
  private Card backupKicker;
  private List<Card> cards;

  public HandResult(List cards) {
    this.cards = cards;
  }

  private void assignHand()
  {
    if (checkIfRoyalFlush()) {
      return;
    } else if (checkIfStraightFlush()) {
      return;
    } else if (checkIfFourOfAKind()) {
      return;
    }

  }

  private boolean checkIfFullHouse()
  {
    HashMap<Character, Integer> map = new HashMap<>();
    this.cards.forEach(i ->{
      int count = map.getOrDefault(i.value, 0);
      map.put(i.value, ++count);
    });

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 3) {
        this.cardStart = this.cards.stream().filter(card -> card.value == entry.getKey()).findFirst().get();
        this.result = FULL_HOUSE;
        return true;
      }
    }
    return false;
  }
  private boolean checkIfFourOfAKind()
  {
    HashMap<Character, Integer> map = new HashMap<>();

    this.cards.forEach(card -> {
      Integer count = map.getOrDefault(card.value, 0);
      count++;
      map.put(card.value, count);
    });

    for(Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 4) {
        this.cardStart = this.cards.stream().filter(card -> card.value == entry.getKey()).findAny().get();
        this.result = STRAIGHT_FLUSH;
        return true;
      }
    }
    return false;
  }
  private boolean checkIfStraightFlush()
  {
    List<Card> sortedCards = this.cards.stream().sorted().toList();

    for (int i = 0; i < sortedCards.size()-1; i++) {
      if (sortedCards.get(i).value + 1 != sortedCards.get(i+1).value) {
        return false;
      }
    }
    cardStart = sortedCards.get(0);
    this.result = STRAIGHT_FLUSH;
    return true;
  }
  private boolean checkIfRoyalFlush()
  {
    Set<Character> lookingFor = Set.of('A','K', 'J', 'Q', 'T');
    Set<Character> suitesFound = new HashSet<>();
    for (int i = 0; i < this.cards.size(); i++) {
      Card card = this.cards.get(i);
      lookingFor.remove(card.value);
      suitesFound.add(card.suit);
    }

    if (lookingFor.size() == 0 && suitesFound.size() == 1) {
      this.result = ROYAL_FLUSH;
      return true;
    }
    return false;
  }
  public int breakTie(HandResult otherHand)
  {
    return -1;
  }

  @Override
  public int compareTo(Object o) {

    HandResult otherHandResult = (HandResult) o;
    if (this.result != otherHandResult.result) {
      return this.result > otherHandResult.result ? 1 : -1;
    }

    //get high card kicker

    return 0;
  }

}


