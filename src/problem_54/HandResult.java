package problem_54;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
  //I wanna change this so that its a sorted list of cards for kickers
  private Card kicker;
  private List<Card> cards;

  public HandResult(List<Card> cards) {
    this.cards = cards;
  }

  private void assignHand() {
    if (checkIfRoyalFlush()) {
      return;
    } else if (checkIfStraightFlush()) {
      return;
    } else if (checkIfFourOfAKind()) {
      return;
    } else if (checkIfFullHouse()) {
      return;
    } else if (checkIfFlush()) {
      return;
    } else if (checkIfThreeOfAKind()) {
      return;
    } else if (checkIfTwoPair()) {
      return;
    } else if (checkIfOnePair()) {
      return;
    } else {
      checkIfHighCard();
    }

  }

  private boolean checkIfFlush() {
    Set<Character> suitesFound = new HashSet<>();
    for (int i = 0; i < this.cards.size(); i++) {
      Card card = this.cards.get(i);
      suitesFound.add(card.suit);
    }
    if (suitesFound.size() == 1) {
      this.cardStart = this.cards.stream().sorted(Comparator.reverseOrder()).findFirst().get();
      result = FLUSH;
      return true;
    }
    return false;
  }

  private boolean checkIfFullHouse() {
    HashMap<Character, Integer> map = new HashMap<>();
    this.cards.forEach(i -> {
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


  private boolean checkIfHighCard() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.cardStart = this.cards.stream().sorted(Comparator.reverseOrder()).findFirst().get();
    this.result = HIGH_CARD;
    return true;
  }

  private boolean checkIfOnePair() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.cards.forEach(card -> {
      Integer count = map.getOrDefault(card.value, 0);
      count++;
      map.put(card.value, count);
    });

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 2) {
        this.cardStart = this.cards.stream().filter(card -> card.value == entry.getKey()).findAny().get();
        this.result = ONE_PAIR;
        return true;
      }
    }
    return false;
  }

  private boolean checkIfTwoPair() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.cards.forEach(card -> {
      Integer count = map.getOrDefault(card.value, 0);
      count++;
      map.put(card.value, count);
    });

    if (map.size() != 3) {
      return false;
    }
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 2) {
        this.cardStart = this.cards.stream().filter(card -> card.value == entry.getKey()).findAny().get();
        this.result = TWO_PAIR;
        return true;
      }
    }
    return false;
  }

  private boolean checkIfThreeOfAKind() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.cards.forEach(card -> {
      Integer count = map.getOrDefault(card.value, 0);
      count++;
      map.put(card.value, count);
    });

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 3) {
        this.cardStart = this.cards.stream().filter(card -> card.value == entry.getKey()).findAny().get();
        this.result = THREE_OF_A_KIND;
        return true;
      }
    }
    return false;
  }

  private boolean checkIfFourOfAKind() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.cards.forEach(card -> {
      Integer count = map.getOrDefault(card.value, 0);
      count++;
      map.put(card.value, count);
    });

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 4) {
        this.cardStart = this.cards.stream().filter(card -> card.value == entry.getKey()).findAny().get();
        this.result = FOUR_OF_A_KIND;
        return true;
      }
    }
    return false;
  }

  private boolean checkIfStraightFlush() {
    List<Card> sortedCards = this.cards.stream().sorted().toList();

    Set<Character> suitesFound = new HashSet<>();
    for (int i = 0; i < sortedCards.size() - 1; i++) {
      suitesFound.add(sortedCards.get(i).suit);
      if (sortedCards.get(i).value + 1 != sortedCards.get(i + 1).value) {
        return false;
      }
    }
    if (suitesFound.size() == 1) {
      cardStart = sortedCards.get(sortedCards.size() - 1);
      this.result = STRAIGHT_FLUSH;
      return true;
    }
    return false;
  }

  private boolean checkIfRoyalFlush() {
    Set<Character> lookingFor = Set.of('A', 'K', 'J', 'Q', 'T');
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

  public int breakTie(HandResult otherHand) {
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

