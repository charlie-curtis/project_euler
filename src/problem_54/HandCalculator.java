package problem_54;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class HandCalculator implements Comparable {

  private int result;
  private List<Card> kickers = new ArrayList<>();
  private List<Card> cards;

  public HandCalculator(List<Card> cards) {
    this.cards = cards;
    computeHand();
  }

  public int getResult() {
    return result;
  }

  private void computeHand() {
    if (checkIfRoyalFlush()) {
      return;
    } else if (isStraightFlush()) {
      this.result = HandConstants.STRAIGHT_FLUSH;
    } else if (checkIfFourOfAKind()) {
      return;
    } else if (checkIfFullHouse()) {
      return;
    } else if (isFlush()) {
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
      result = HandConstants.FLUSH;
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

  private boolean isFlush() {
    Set<Character> suitsFound = new HashSet<>();
    this.cards.forEach(card -> suitsFound.add(card.suit));
    return suitsFound.size() == 1;
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
        this.result = HandConstants.FULL_HOUSE;
        return true;
      }
    }
    return false;
  }


  private boolean checkIfHighCard() {
    HashMap<Character, Integer> map = new HashMap<>();

    this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
    this.result = HandConstants.HIGH_CARD;
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
        this.result = HandConstants.ONE_PAIR;
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
        this.result = HandConstants.TWO_PAIR;
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
        this.result = HandConstants.THREE_OF_A_KIND;
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
        this.result = HandConstants.FOUR_OF_A_KIND;
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
      Card nextCard = sortedCards.get(i + 1);
      if (!(thisCard.isCardSequential(nextCard))) {
        return false;
      }
    }

    if (sortedCards.size() == this.cards.size()) {
      //no aces
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
      this.result = HandConstants.STRAIGHT;
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


    this.result = HandConstants.STRAIGHT;
    return true;
  }

  private boolean isStraightFlush() {
    return isFlush() && checkIfStraight();
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
      this.result = HandConstants.ROYAL_FLUSH;
      this.kickers = this.cards.stream().sorted(Comparator.reverseOrder()).toList();
      return true;
    }
    return false;
  }

  @Override
  public int compareTo(Object o) {

    HandCalculator otherCalculator = (HandCalculator) o;
    if (this.result != otherCalculator.result) {
      return this.result > otherCalculator.result ? 1 : -1;
    }

    //get high card kicker
    System.out.printf("Found TIE -- both were %s%n", this);
    this.cards.forEach(card -> System.out.printf("%s ", card));
    System.out.println();
    otherCalculator.cards.forEach(card -> System.out.printf("%s ", card));
    System.out.println();

    for (int i = 0; i < this.kickers.size(); i++) {
      int compareTo = this.kickers.get(i).compareTo(otherCalculator.kickers.get(i));

      if (compareTo != 0) {
        System.out.printf("Breaking tie: Player %d wins%n", compareTo == 1 ? 1 : 2);
        return compareTo;
      }
    }

    System.out.println("There ws still a tie. Were the hands identical?");
    return 0;
  }

  @Override
  public String toString() {
    return switch (result) {
      case HandConstants.ROYAL_FLUSH -> "Royal Flush";
      case HandConstants.STRAIGHT_FLUSH -> "Straight Flush";
      case HandConstants.FOUR_OF_A_KIND -> "Four of a kind";
      case HandConstants.FULL_HOUSE -> "Full house";
      case HandConstants.FLUSH -> "Flush";
      case HandConstants.STRAIGHT -> "Straight";
      case HandConstants.THREE_OF_A_KIND -> "Three of a kind";
      case HandConstants.TWO_PAIR -> "Two pair";
      case HandConstants.ONE_PAIR -> "One pair";
      case HandConstants.HIGH_CARD -> "High card";
      default -> "UNKNOWN";
    };
  }
}

