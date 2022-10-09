package problem_54.calculator;

import problem_54.Card;
import problem_54.constant.HandConstants;
import problem_54.comparator.CardComparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FiveCardDrawCalculator implements HandCalculator {

  private int result;
  private List<Card> cards;

  public FiveCardDrawCalculator(List<Card> cards) {
    this.cards = cards;
    computeHand();
  }

  public int getResult() {
    return result;
  }

  private void computeHand() {
    this.cards.sort(CardComparator::aceHighCompare);
    if (handleIfRoyalFlush()) {
      this.result = HandConstants.ROYAL_FLUSH;
    } else if (handleIfStraightFlush()) {
      this.result = HandConstants.STRAIGHT_FLUSH;
    } else if (handleIfFourOfAKind()) {
      this.result = HandConstants.FOUR_OF_A_KIND;
    } else if (handleIfFullHouse()) {
      this.result = HandConstants.FULL_HOUSE;
    } else if (handleIfFlush()) {
      this.result = HandConstants.FLUSH;
    } else if (handleIfStraight()) {
      this.result = HandConstants.STRAIGHT;
    } else if (handleIfThreeOfAKind()) {
      this.result = HandConstants.THREE_OF_A_KIND;
    } else if (handleIfTwoPair()) {
      this.result = HandConstants.TWO_PAIR;
    } else if (handleIfOnePair()) {
      this.result = HandConstants.ONE_PAIR;
    } else {
      this.result = HandConstants.HIGH_CARD;
    }
  }


  /**
   * @return true if all the cards are of the same suit, and there is an ace, king, queen, jack, and 10
   */
  private boolean handleIfRoyalFlush() {
    if (!handleIfFlush()) {
      return false;
    }
    Set<Character> lookingFor = new HashSet<>(Set.of('A', 'K', 'J', 'Q', 'T'));
    this.cards.forEach(card -> lookingFor.remove(card.value));

    return lookingFor.size() == 0;
  }

  /**
   * This function has side effects. See handleIfStraight
   */
  private boolean handleIfStraightFlush() {
    return handleIfFlush() && handleIfStraight();
  }

  /**
   * This function has side effects. it puts the 4 of a kind at the front of the hand
   * @return true if there is some value that appears 4 times.
   */
  private boolean handleIfFourOfAKind() {

    Map<Character, Integer> mostCommon = getHighestFrequencyValues(1);

    if (!mostCommon.containsValue(4)) {
      return false;
    }
    sortCardsByValues(mostCommon);
    return true;
  }

  /**
   * This function has side effects. It puts the three of a kind at the front of the hand
   * @return true if there is 3 of a kind + a pair.
   */
  private boolean handleIfFullHouse() {
    Map<Character, Integer> mostCommon = getHighestFrequencyValues(2);

    if (!mostCommon.containsValue(3) || !mostCommon.containsValue(2)) {
      return false;
    }
    sortCardsByValues(mostCommon);
    return true;
  }

  /**
   * @return If all the cards have the same suit, return true
   */
  private boolean handleIfFlush() {
    return this.cards.stream().map(Card::getSuit).distinct().count() == 1;
  }


  /**
   * this function has side effects. It puts the straight in descending order
   * @return true if all the cards are sequential (i.e. King -> Queen -> Jack -> Ten -> 9)
   */
  private boolean handleIfStraight() {
    boolean areCardsSequential = CardComparator.areCardsSequential(this.cards);

    if (!areCardsSequential) {
      return false;
    }

    long numberOfAces = this.cards.stream().filter(card -> card.value == 'A').count();
    long numberOfTwos = this.cards.stream().filter(card -> card.value == '2').count();
    if (numberOfAces == 0 || numberOfTwos == 0) {
      //dont have to worry about the case where ace is low
      return true;
    }

    this.cards.sort(CardComparator::aceLowCompare);

    return true;
  }

  /**
   * @return If the highest frequency is 3, return true.
   */
  private boolean handleIfThreeOfAKind() {
    Map<Character, Integer> mostCommon = getHighestFrequencyValues(1);

    if (!mostCommon.containsValue(3)) {
      return false;
    }
    sortCardsByValues(mostCommon);
    return true;
  }

  /**
   * This function has side effects. It orders the hand by putting the pairs
   * at the front.
   * @return If the two most common cards have a frequency of two, return true
   */
  private boolean handleIfTwoPair() {
    Map<Character, Integer> mostCommon = getHighestFrequencyValues(2);

    long distinctValues = mostCommon.values().stream().distinct().count();
    if (!mostCommon.containsValue(2) || distinctValues != 1L) {
      return false;
    }
    sortCardsByValues(mostCommon);
    return true;
  }

  /**
   *
   * @return true if there are two cards with the same value.
   */
  private boolean handleIfOnePair() {

    Map<Character, Integer> mostCommon = getHighestFrequencyValues(1);

    if (!mostCommon.containsValue(2)) {
      return false;
    }
    sortCardsByValues(mostCommon);
    return true;
  }


  @Override
  public int compareTo(Object o) {

    FiveCardDrawCalculator otherCalculator = (FiveCardDrawCalculator) o;
    if (this.result != otherCalculator.result) {
      return this.result > otherCalculator.result ? 1 : -1;
    }

    //get high card kicker
    System.out.printf("Found TIE -- both were %s%n", this);
    this.cards.forEach(card -> System.out.printf("%s ", card));
    System.out.println();
    otherCalculator.cards.forEach(card -> System.out.printf("%s ", card));
    System.out.println();

    for (int i = 0; i < this.cards.size(); i++) {
      int compareTo = this.cards.get(i).compareTo(otherCalculator.cards.get(i));

      if (compareTo != 0) {
        System.out.printf("Breaking tie: Player %d wins%n", compareTo == -1 ? 1 : 2);
        //We are inverting here because the cards themselves consider larger face values to be -1,
        //but as far as comparing hands, 1 indicates the hand is greater
        return -1 * compareTo;
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
      case HandConstants.FOUR_OF_A_KIND -> "Four of a Kind";
      case HandConstants.FULL_HOUSE -> "Full House";
      case HandConstants.FLUSH -> "Flush";
      case HandConstants.STRAIGHT -> "Straight";
      case HandConstants.THREE_OF_A_KIND -> "Three of a Kind";
      case HandConstants.TWO_PAIR -> "Two Pair";
      case HandConstants.ONE_PAIR -> "One Pair";
      case HandConstants.HIGH_CARD -> "High Card";
      default -> "UNKNOWN";
    };
  }

  /**
   * finds the most frequent X values in a hand.
   */
  private Map<Character, Integer> getHighestFrequencyValues(int limit)
  {
    HashMap<Character, Integer> map = new HashMap<>();
    this.cards.forEach(card -> {
      Integer count = map.getOrDefault(card.value, 0);
      count++;
      map.put(card.value, count);
    });

    return map
      .entrySet()
      .stream()
      .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
      .limit(limit)
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  /**
   * Sorts in the following order
   * - If both cards have the same value -> 0
   * - If neither card is the most frequent element OR they both appeared the same amount -> compare by card value
   * - else compare by the most frequently element
   * @param valuesToSortBy - list of values to sort by. this function will place the character
   *                       with the highest frequency at the front of the list, and then the next
   *                       character, etc.
   */
  private void sortCardsByValues(Map<Character, Integer> valuesToSortBy)
  {
    this.cards.sort((cardA, cardB) -> {
      if (cardA.value == cardB.value) return 0;
      Integer cardAPriority = (valuesToSortBy.get(cardA.value) == null) ? Integer.MIN_VALUE : valuesToSortBy.get(cardA.value);
      Integer cardBPriority = (valuesToSortBy.get(cardB.value) == null) ? Integer.MIN_VALUE : valuesToSortBy.get(cardB.value);
      if (cardAPriority == Integer.MIN_VALUE && cardBPriority == Integer.MIN_VALUE
        || cardAPriority.equals(cardBPriority)) {
        return cardA.value > cardB.value ? -1: 1;
      }

      return cardAPriority > cardBPriority ? -1 : 1;

    });
  }
}
