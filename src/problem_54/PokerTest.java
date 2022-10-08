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
    FiveCardDrawCalculator calculator = new FiveCardDrawCalculator(createCardsFromString("AH KH TH JH QH"));
    Assert.assertEquals(HandConstants.ROYAL_FLUSH, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("9H KH TH JH QH"));
    Assert.assertEquals(HandConstants.STRAIGHT_FLUSH, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("AH QS QC QD QH"));
    Assert.assertEquals(HandConstants.FOUR_OF_A_KIND, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("AH AS TH TS TC"));
    Assert.assertEquals(HandConstants.FULL_HOUSE, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("AH 3H TH JH QH"));
    Assert.assertEquals(HandConstants.FLUSH, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("2H 3D 6H 4D 5H"));
    Assert.assertEquals(HandConstants.STRAIGHT, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("AH QS TH QD QH"));
    Assert.assertEquals(HandConstants.THREE_OF_A_KIND, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("AH TS TH QD QH"));
    Assert.assertEquals(HandConstants.TWO_PAIR, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("1H 2S 3H 3D QH"));
    Assert.assertEquals(HandConstants.ONE_PAIR, calculator.getResult());

    calculator = new FiveCardDrawCalculator(createCardsFromString("1H 2S 3H 7D QH"));
    Assert.assertEquals(HandConstants.HIGH_CARD, calculator.getResult());
  }

  public static void test_compareTo() {
    FiveCardDrawCalculator royalFlush = new FiveCardDrawCalculator(createCardsFromString("AH KH TH JH QH"));
    FiveCardDrawCalculator fullHouse = new FiveCardDrawCalculator(createCardsFromString("AH AS TH TS TC"));
    FiveCardDrawCalculator twoPair = new FiveCardDrawCalculator(createCardsFromString("AH TS TH QD QH"));
    FiveCardDrawCalculator highCard = new FiveCardDrawCalculator(createCardsFromString("1H 2S 3H 7D QH"));

    Assert.assertEquals(-1, highCard.compareTo(royalFlush));
    Assert.assertEquals(-1, highCard.compareTo(twoPair));
    Assert.assertEquals(1, fullHouse.compareTo(twoPair));
    Assert.assertEquals(1, fullHouse.compareTo(highCard));
  }

  public static void test_acesCanBeHighOrLowForStraights() {
    FiveCardDrawCalculator result = new FiveCardDrawCalculator(createCardsFromString("2H 3D AH 4D 5H"));
    Assert.assertEquals(HandConstants.STRAIGHT, result.getResult());

    FiveCardDrawCalculator betterStraight = new FiveCardDrawCalculator(createCardsFromString("TH QD AH JD KH"));
    Assert.assertEquals(HandConstants.STRAIGHT, betterStraight.getResult());

    Assert.assertEquals(1, betterStraight.compareTo(result));


    FiveCardDrawCalculator straightFlush = new FiveCardDrawCalculator(createCardsFromString("2H 3H AH 4H 5H"));
    Assert.assertEquals(HandConstants.STRAIGHT_FLUSH, straightFlush.getResult());

    FiveCardDrawCalculator betterStraightFlush = new FiveCardDrawCalculator(createCardsFromString("TH QH 9H JH KH"));
    Assert.assertEquals(HandConstants.STRAIGHT_FLUSH, betterStraightFlush.getResult());


    Assert.assertEquals(1, betterStraightFlush.compareTo(straightFlush));

    result = new FiveCardDrawCalculator(createCardsFromString("2H 3D AH 4D AD"));
    Assert.assertEquals(HandConstants.ONE_PAIR, result.getResult());

  }

  public static void test_compareTo_breaksTies() {
    FiveCardDrawCalculator straightFlush = new FiveCardDrawCalculator(createCardsFromString("9H KH TH JH 8H"));
    FiveCardDrawCalculator higherStraightFlush = new FiveCardDrawCalculator(createCardsFromString("9H KH TH JH QH"));
    Assert.assertEquals(1, higherStraightFlush.compareTo(straightFlush));

    FiveCardDrawCalculator fourOfAKind = new FiveCardDrawCalculator(createCardsFromString("AH QS QC QD QH"));
    FiveCardDrawCalculator higherFourOfAKind = new FiveCardDrawCalculator(createCardsFromString("AH KS KC KD KH"));
    Assert.assertEquals(1, higherFourOfAKind.compareTo(fourOfAKind));

    FiveCardDrawCalculator fullHouse = new FiveCardDrawCalculator(createCardsFromString("3H 3S 7H 3C 7C"));
    FiveCardDrawCalculator higherFullHouse = new FiveCardDrawCalculator(createCardsFromString("AH AS TH TS TC"));
    Assert.assertEquals(1, higherFullHouse.compareTo(fullHouse));

    FiveCardDrawCalculator flush = new FiveCardDrawCalculator(createCardsFromString("9H 6H 8H 2H 4H"));
    FiveCardDrawCalculator flushAceKicker = new FiveCardDrawCalculator(createCardsFromString("AH 3H TH JH QH"));
    Assert.assertEquals(1, flushAceKicker.compareTo(flush));

    FiveCardDrawCalculator straight = new FiveCardDrawCalculator(createCardsFromString("2H 3D 6H 4D 5H"));
    FiveCardDrawCalculator higherStraight = new FiveCardDrawCalculator(createCardsFromString("7H 3D 6H 4D 5H"));
    Assert.assertEquals(1, higherStraight.compareTo(straight));

    FiveCardDrawCalculator threeOfAKind = new FiveCardDrawCalculator(createCardsFromString("AH QS TH QD QH"));
    FiveCardDrawCalculator higherThreeOfAKind = new FiveCardDrawCalculator(createCardsFromString("AH KS TH KD KH"));
    Assert.assertEquals(1, higherThreeOfAKind.compareTo(threeOfAKind));

    FiveCardDrawCalculator twoPair = new FiveCardDrawCalculator(createCardsFromString("AH TS TH QD QH"));
    FiveCardDrawCalculator higherTwoPair = new FiveCardDrawCalculator(createCardsFromString("AH AS TH KD KH"));
    Assert.assertEquals(1, higherTwoPair.compareTo(twoPair));

    FiveCardDrawCalculator onePair = new FiveCardDrawCalculator(createCardsFromString("2H 2S TH 5D QH"));
    FiveCardDrawCalculator higherOnePair = new FiveCardDrawCalculator(createCardsFromString("AH AS TH 3D KH"));
    Assert.assertEquals(1, higherOnePair.compareTo(onePair));


    onePair = new FiveCardDrawCalculator(createCardsFromString("2H 2S TH QD QH"));
    higherOnePair = new FiveCardDrawCalculator(createCardsFromString("KH KS TH 2D 2H"));
    Assert.assertEquals(-1, onePair.compareTo(higherOnePair));
    Assert.assertEquals(1, higherOnePair.compareTo(onePair));

    FiveCardDrawCalculator highCard = new FiveCardDrawCalculator(createCardsFromString("1H 2S 3H 7D TH"));
    FiveCardDrawCalculator slightlyHigherHighCard = new FiveCardDrawCalculator(createCardsFromString("1H 2S 5H 7D TH"));
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
