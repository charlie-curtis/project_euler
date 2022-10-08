package problem_54;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class PokerTest {

  public static void main(String[] args) {
    test_handsResults_correctState();
    test_compareTo();
    test_compareTo_breaksTies();
    test_acesCanBeHighOrLowForStraights();
  }

  public static void test_handsResults_correctState() {
    HandCalculator calculator = new HandCalculator(createCardsFromString("AH KH TH JH QH"));
    Assert.assertEquals(HandConstants.ROYAL_FLUSH, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("9H KH TH JH QH"));
    Assert.assertEquals(HandConstants.STRAIGHT_FLUSH, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("AH QS QC QD QH"));
    Assert.assertEquals(HandConstants.FOUR_OF_A_KIND, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("AH AS TH TS TC"));
    Assert.assertEquals(HandConstants.FULL_HOUSE, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("AH 3H TH JH QH"));
    Assert.assertEquals(HandConstants.FLUSH, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("2H 3D 6H 4D 5H"));
    Assert.assertEquals(HandConstants.STRAIGHT, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("AH QS TH QD QH"));
    Assert.assertEquals(HandConstants.THREE_OF_A_KIND, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("AH TS TH QD QH"));
    Assert.assertEquals(HandConstants.TWO_PAIR, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("1H 2S 3H 3D QH"));
    Assert.assertEquals(HandConstants.ONE_PAIR, calculator.getResult());

    calculator = new HandCalculator(createCardsFromString("1H 2S 3H 7D QH"));
    Assert.assertEquals(HandConstants.HIGH_CARD, calculator.getResult());
  }

  public static void test_compareTo() {
    HandCalculator royalFlush = new HandCalculator(createCardsFromString("AH KH TH JH QH"));
    HandCalculator fullHouse = new HandCalculator(createCardsFromString("AH AS TH TS TC"));
    HandCalculator twoPair = new HandCalculator(createCardsFromString("AH TS TH QD QH"));
    HandCalculator highCard = new HandCalculator(createCardsFromString("1H 2S 3H 7D QH"));

    Assert.assertEquals(-1, highCard.compareTo(royalFlush));
    Assert.assertEquals(-1, highCard.compareTo(twoPair));
    Assert.assertEquals(1, fullHouse.compareTo(twoPair));
    Assert.assertEquals(1, fullHouse.compareTo(highCard));
  }

  public static void test_acesCanBeHighOrLowForStraights() {
    HandCalculator result = new HandCalculator(createCardsFromString("2H 3D AH 4D 5H"));
    Assert.assertEquals(HandConstants.STRAIGHT, result.getResult());

    HandCalculator betterStraight = new HandCalculator(createCardsFromString("TH QD AH JD KH"));
    Assert.assertEquals(HandConstants.STRAIGHT, betterStraight.getResult());

    Assert.assertEquals(1, betterStraight.compareTo(result));


    HandCalculator straightFlush = new HandCalculator(createCardsFromString("2H 3H AH 4H 5H"));
    Assert.assertEquals(HandConstants.STRAIGHT_FLUSH, straightFlush.getResult());

    HandCalculator betterStraightFlush = new HandCalculator(createCardsFromString("TH QH 9H JH KH"));
    Assert.assertEquals(HandConstants.STRAIGHT_FLUSH, betterStraightFlush.getResult());


    Assert.assertEquals(1, betterStraightFlush.compareTo(straightFlush));

    result = new HandCalculator(createCardsFromString("2H 3D AH 4D AD"));
    Assert.assertEquals(HandConstants.ONE_PAIR, result.getResult());

  }

  public static void test_compareTo_breaksTies() {
    HandCalculator straightFlush = new HandCalculator(createCardsFromString("9H KH TH JH 8H"));
    HandCalculator higherStraightFlush = new HandCalculator(createCardsFromString("9H KH TH JH QH"));
    Assert.assertEquals(1, higherStraightFlush.compareTo(straightFlush));

    HandCalculator fourOfAKind = new HandCalculator(createCardsFromString("AH QS QC QD QH"));
    HandCalculator higherFourOfAKind = new HandCalculator(createCardsFromString("AH KS KC KD KH"));
    Assert.assertEquals(1, higherFourOfAKind.compareTo(fourOfAKind));

    HandCalculator fullHouse = new HandCalculator(createCardsFromString("3H 3S 7H 3C 7C"));
    HandCalculator higherFullHouse = new HandCalculator(createCardsFromString("AH AS TH TS TC"));
    Assert.assertEquals(1, higherFullHouse.compareTo(fullHouse));

    HandCalculator flush = new HandCalculator(createCardsFromString("9H 6H 8H 2H 4H"));
    HandCalculator flushAceKicker = new HandCalculator(createCardsFromString("AH 3H TH JH QH"));
    Assert.assertEquals(1, flushAceKicker.compareTo(flush));

    HandCalculator straight = new HandCalculator(createCardsFromString("2H 3D 6H 4D 5H"));
    HandCalculator higherStraight = new HandCalculator(createCardsFromString("7H 3D 6H 4D 5H"));
    Assert.assertEquals(1, higherStraight.compareTo(straight));

    HandCalculator threeOfAKind = new HandCalculator(createCardsFromString("AH QS TH QD QH"));
    HandCalculator higherThreeOfAKind = new HandCalculator(createCardsFromString("AH KS TH KD KH"));
    Assert.assertEquals(1, higherThreeOfAKind.compareTo(threeOfAKind));

    HandCalculator twoPair = new HandCalculator(createCardsFromString("AH TS TH QD QH"));
    HandCalculator higherTwoPair = new HandCalculator(createCardsFromString("AH AS TH KD KH"));
    Assert.assertEquals(1, higherTwoPair.compareTo(twoPair));

    HandCalculator onePair = new HandCalculator(createCardsFromString("2H 2S TH 5D QH"));
    HandCalculator higherOnePair = new HandCalculator(createCardsFromString("AH AS TH 3D KH"));
    Assert.assertEquals(1, higherOnePair.compareTo(onePair));

    HandCalculator highCard = new HandCalculator(createCardsFromString("1H 2S 3H 7D TH"));
    HandCalculator slightlyHigherHighCard = new HandCalculator(createCardsFromString("1H 2S 5H 7D TH"));
    Assert.assertEquals(1, slightlyHigherHighCard.compareTo(highCard));
  }

  private static List<Card> createCardsFromString(String cardString) {
    List<Card> cards = new ArrayList<>();
    String[] cardArray = cardString.split(" ");
    for (int i = 0; i < cardArray.length; i++) {
      Card card = new Card(cardArray[i].charAt(0), cardArray[i].charAt(1));
      cards.add(card);
    }
    return cards;
  }
}
