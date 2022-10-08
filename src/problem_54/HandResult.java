package problem_54;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class HandResult implements Comparable {

  static final int ROYAL_FLUSH = 10;
  static final int STRAIGHT_FLUSH = 9;
  static final int FOUR_OF_A_KIND = 8;
  static final int FULL_HOUSE = 7;
  static final int FLUSH = 6;
  static final int STRAIGHT = 5;
  static final int THREE_OF_A_KIND = 4;
  static final int TWO_PAIR = 3;
  static final int ONE_PAIR = 2;
  static final int HIGH_CARD = 1;

  private int result;
  private List<Card> kickers = new ArrayList<>();
  private List<Card> cards;

  public HandResult(List<Card> cards) {
    this.cards = cards;
    assignHand();
  }

  public int getResult()
  {
    return result;
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
    } else if (checkIfStraight()) {
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
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
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
      if (entry.getValue() == 3 && map.size() == 2) {
        this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
        this.result = FULL_HOUSE;
        return true;
      }
    }
    return false;
  }


  private boolean checkIfHighCard() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
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

        List<Card> highToLow = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
        for (int i = 0; i < highToLow.size(); i++) {
          Card card = highToLow.get(i);
          if (card.value == entry.getKey()) {
            kickers.add(0, card);
          } else {
            kickers.add(card);
          }
        }
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
    List<Card> highToLow = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 2) {
        Card remainingCard = null;
        for (int i = 0; i < highToLow.size(); i++) {
          Card card = highToLow.get(i);
          if (map.get(card.value) == 2) {
            kickers.add(card);
          } else {
            remainingCard = card;
          }
        }
        kickers.sort(Comparator.naturalOrder());
        kickers.add(remainingCard);
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

    List<Card> highToLow = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 3) {
        List<Card> remainingCards = new ArrayList<>();
        for (int i = 0; i < highToLow.size(); i++) {
          Card card = highToLow.get(i);
          if (map.get(card.value) == 3) {
            kickers.add(card);
          } else {
            remainingCards.add(card);
          }
        }
        kickers.addAll(remainingCards);
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
        List<Card> highToLow = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
        List<Card> remainingCards = new ArrayList<>();
        for (int i = 0; i < highToLow.size(); i++) {
          Card card = highToLow.get(i);
          if (map.get(card.value) == 4) {
            kickers.add(card);
          } else {
            remainingCards.add(card);
          }
        }
        kickers.addAll(remainingCards);
        this.result = FOUR_OF_A_KIND;
        return true;
      }
    }
    return false;
  }

  private boolean checkIfStraight() {
    List<Card> sortedCards = this.cards.stream().sorted().toList();

    //get rid of the ace if it exists, for now.
    sortedCards = sortedCards.stream().filter(card -> card.value != 'A').toList();

    for (int i = 0; i < sortedCards.size() - 1; i++) {
      Card thisCard = sortedCards.get(i);
      Card nextCard = sortedCards.get(i+1);
      if (!(thisCard.isCardSequential(nextCard))) {
        return false;
      }
    }

    if (sortedCards.size() == this.cards.size()) {
      //no aces
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
      this.result = STRAIGHT;
      return true;
    }

    //there was an ace
    long numberOfAces = this.cards.stream().filter(card -> card.value == 'A').count();
    long numberOfKings = this.cards.stream().filter(card -> card.value == 'K').count();
    long numberOfTwos = this.cards.stream().filter(card -> card.value == '2').count();

    if (numberOfAces > 1) {
      return false;
    }
    if (numberOfKings == 0 && numberOfTwos == 0) {
      return false;
    }

    if (numberOfKings == 1) {
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
    } else {
      this.kickers = new ArrayList<>();
      this.kickers.addAll(sortedCards.stream().sorted(Comparator.reverseOrder()).toList());
      this.kickers.add(this.cards.stream().filter(card -> card.value == 'A').findFirst().get());
    }


    this.result = STRAIGHT;
    return true;
  }

  private boolean checkIfStraightFlush() {
    boolean isStraightFlush = (checkIfFlush() && checkIfStraight());
    if (isStraightFlush) {
      this.result = STRAIGHT_FLUSH;
    }
    return isStraightFlush;
  }

  private boolean checkIfRoyalFlush() {
    Set<Character> lookingFor = new HashSet<>(Set.of('A', 'K', 'J', 'Q', 'T'));
    Set<Character> suitesFound = new HashSet<>();
    for (int i = 0; i < this.cards.size(); i++) {
      Card card = this.cards.get(i);
      lookingFor.remove(card.value);
      suitesFound.add(card.suit);
    }

    if (lookingFor.size() == 0 && suitesFound.size() == 1) {
      this.result = ROYAL_FLUSH;
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
      return true;
    }
    return false;
  }
  @Override
  public int compareTo(Object o) {

    HandResult otherHandResult = (HandResult) o;
    if (this.result != otherHandResult.result) {
      return this.result > otherHandResult.result ? 1 : -1;
    }

    //get high card kicker
    System.out.printf("Found TIE -- both were %s%n", this);
    this.cards.forEach(card -> System.out.printf("%s ", card));
    System.out.println();
    otherHandResult.cards.forEach(card -> System.out.printf("%s ", card));
    System.out.println();

    for (int i = 0; i < this.kickers.size(); i++) {
      int compareTo = this.kickers.get(i).compareTo(otherHandResult.kickers.get(i));

      if (compareTo != 0) {
        System.out.printf("Breaking tie: Player %d wins%n", compareTo == 1 ? 1: 2);
        return compareTo;
      }
    }

    return 0;
  }

  @Override
  public String toString() {
    return switch (result) {
      case ROYAL_FLUSH -> "Royal Flush";
      case STRAIGHT_FLUSH -> "Straight Flush";
      case FOUR_OF_A_KIND -> "Four of a kind";
      case FULL_HOUSE -> "Full house";
      case FLUSH -> "Flush";
      case STRAIGHT -> "Straight";
      case THREE_OF_A_KIND -> "Three of a kind";
      case TWO_PAIR -> "Two pair";
      case ONE_PAIR -> "One pair";
      case HIGH_CARD -> "High card";
      default -> "UNKNOWN";
    };
  }
}

