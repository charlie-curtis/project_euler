package problem_54;

import problem_54.comparator.CardComparator;

public class Card implements Comparable {
  public Character value;
  public Character suit;

  public Card(Character first, Character second) {
    this.value = first;
    this.suit = second;
  }

  public Character getValue()
  {
    return value;
  }

  public Character getSuit()
  {
    return suit;
  }

  @Override
  public String toString() {
    return this.value + "" + this.suit;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int compareTo(Object o) {
    Card otherCard = (Card)o;
    return CardComparator.aceHighCompare(this, otherCard);
  }
}
