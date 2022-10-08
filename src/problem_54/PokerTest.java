package problem_54;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class PokerTest {

  public static void main(String[] args)
  {
    test_handsResults_correctState();
    test_compareTo();
    test_compareTo_breaksTies();
    test_acesCanBeHighOrLowForStraights();
  }

  public static void test_handsResults_correctState()
  {
    HandResult handResult = new HandResult(createCardsFromString("AH KH TH JH QH"));
    Assert.assertEquals(HandResult.ROYAL_FLUSH, handResult.getResult());

    handResult = new HandResult(createCardsFromString("9H KH TH JH QH"));
    Assert.assertEquals(HandResult.STRAIGHT_FLUSH, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH QS QC QD QH"));
    Assert.assertEquals(HandResult.FOUR_OF_A_KIND, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH AS TH TS TC"));
    Assert.assertEquals(HandResult.FULL_HOUSE, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH 3H TH JH QH"));
    Assert.assertEquals(HandResult.FLUSH, handResult.getResult());

    handResult = new HandResult(createCardsFromString("2H 3D 6H 4D 5H"));
    Assert.assertEquals(HandResult.STRAIGHT, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH QS TH QD QH"));
    Assert.assertEquals(HandResult.THREE_OF_A_KIND, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH TS TH QD QH"));
    Assert.assertEquals(HandResult.TWO_PAIR, handResult.getResult());

    handResult = new HandResult(createCardsFromString("1H 2S 3H 3D QH"));
    Assert.assertEquals(HandResult.ONE_PAIR, handResult.getResult());

    handResult = new HandResult(createCardsFromString("1H 2S 3H 7D QH"));
    Assert.assertEquals(HandResult.HIGH_CARD, handResult.getResult());
  }

  public static void test_compareTo()
  {
    HandResult royalFlush = new HandResult(createCardsFromString("AH KH TH JH QH"));
    HandResult fullHouse = new HandResult(createCardsFromString("AH AS TH TS TC"));
    HandResult twoPair = new HandResult(createCardsFromString("AH TS TH QD QH"));
    HandResult highCard = new HandResult(createCardsFromString("1H 2S 3H 7D QH"));

    Assert.assertEquals(-1, highCard.compareTo(royalFlush));
    Assert.assertEquals(-1, highCard.compareTo(twoPair));
    Assert.assertEquals(1, fullHouse.compareTo(twoPair));
    Assert.assertEquals(1, fullHouse.compareTo(highCard));
  }

  public static void test_acesCanBeHighOrLowForStraights()
  {
    HandResult result = new HandResult(createCardsFromString("2H 3D AH 4D 5H"));
    Assert.assertEquals(HandResult.STRAIGHT, result.getResult());

    HandResult betterStraight = new HandResult(createCardsFromString("TH QD AH JD KH"));
    Assert.assertEquals(HandResult.STRAIGHT, betterStraight.getResult());

    Assert.assertEquals(1, betterStraight.compareTo(result));


    HandResult straightFlush = new HandResult(createCardsFromString("2H 3H AH 4H 5H"));
    Assert.assertEquals(HandResult.STRAIGHT_FLUSH, straightFlush.getResult());

    HandResult betterStraightFlush = new HandResult(createCardsFromString("TH QH 9H JH KH"));
    Assert.assertEquals(HandResult.STRAIGHT_FLUSH, betterStraightFlush.getResult());


    Assert.assertEquals(1, betterStraightFlush.compareTo(straightFlush));

    result = new HandResult(createCardsFromString("2H 3D AH 4D AD"));
    Assert.assertEquals(HandResult.ONE_PAIR, result.getResult());

  }

  public static void test_compareTo_breaksTies()
  {
    HandResult straightFlush  = new HandResult(createCardsFromString("9H KH TH JH 8H"));
    HandResult higherStraightFlush  = new HandResult(createCardsFromString("9H KH TH JH QH"));
    Assert.assertEquals(1, higherStraightFlush.compareTo(straightFlush));

    HandResult fourOfAKind = new HandResult(createCardsFromString("AH QS QC QD QH"));
    HandResult higherFourOfAKind = new HandResult(createCardsFromString("AH KS KC KD KH"));
    Assert.assertEquals(1, higherFourOfAKind.compareTo(fourOfAKind));

    HandResult fullHouse = new HandResult(createCardsFromString("3H 3S 7H 3C 7C"));
    HandResult higherFullHouse = new HandResult(createCardsFromString("AH AS TH TS TC"));
    Assert.assertEquals(1, higherFullHouse.compareTo(fullHouse));

    HandResult flush = new HandResult(createCardsFromString("9H 6H 8H 2H 4H"));
    HandResult flushAceKicker = new HandResult(createCardsFromString("AH 3H TH JH QH"));
    Assert.assertEquals(1, flushAceKicker.compareTo(flush));

    HandResult straight = new HandResult(createCardsFromString("2H 3D 6H 4D 5H"));
    HandResult higherStraight = new HandResult(createCardsFromString("7H 3D 6H 4D 5H"));
    Assert.assertEquals(1, higherStraight.compareTo(straight));

    HandResult threeOfAKind = new HandResult(createCardsFromString("AH QS TH QD QH"));
    HandResult higherThreeOfAKind = new HandResult(createCardsFromString("AH KS TH KD KH"));
    Assert.assertEquals(1, higherThreeOfAKind.compareTo(threeOfAKind));

    HandResult twoPair = new HandResult(createCardsFromString("AH TS TH QD QH"));
    HandResult higherTwoPair = new HandResult(createCardsFromString("AH AS TH KD KH"));
    Assert.assertEquals(1, higherTwoPair.compareTo(twoPair));

    HandResult onePair = new HandResult(createCardsFromString("2H 2S TH 5D QH"));
    HandResult higherOnePair = new HandResult(createCardsFromString("AH AS TH 3D KH"));
    Assert.assertEquals(1, higherOnePair.compareTo(onePair));

    HandResult highCard = new HandResult(createCardsFromString("1H 2S 3H 7D TH"));
    HandResult slightlyHigherHighCard = new HandResult(createCardsFromString("1H 2S 5H 7D TH"));
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
