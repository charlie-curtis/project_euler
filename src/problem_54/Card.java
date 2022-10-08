package problem_54;

import java.util.AbstractMap;

public class Card extends AbstractMap.SimpleEntry<Character, Character> implements Comparable {
  public Character value;
  public Character suit;

  public Card(Character first, Character second) {
    super(first, second);
    this.value = first;
    this.suit = second;
  }

  private int getCardRanking() {
    char c = this.value;
    switch ((int) c) {
      case 'A':
        return 62;
      case 'K':
        return 61;
      case 'Q':
        return 60;
      case 'J':
        return 59;
      case 'T':
        return 58;
      default:
        return (int) c;
    }
  }

  public boolean isCardSequential(Card otherCard) {
    if (getCardRanking() + 1 == otherCard.getCardRanking()) {
      return true;
    }
    //handle the case where ace is used low
    return (this.value == 'A' && otherCard.value == '2');

  }

  @Override
  public int compareTo(Object o) {
    Card otherCard = (Card) o;
    int rankA = this.getCardRanking();
    int rankB = otherCard.getCardRanking();

    if (rankA > rankB) return 1;
    if (rankB > rankA) return -1;
    return 0;
  }

  @Override
  public String toString() {
    return this.value + "" + this.suit;
  }
}
