package problem_54;

import junit.framework.Assert;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

public class PokerTest {

  public static void main(String[] args)
  {
    test_handsResults_correctState();
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

    handResult = new HandResult(createCardsFromString("9H KS TH JH QH"));
    Assert.assertEquals(HandResult.STRAIGHT_FLUSH, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH QS TH QD QH"));
    Assert.assertEquals(HandResult.THREE_OF_A_KIND, handResult.getResult());

    handResult = new HandResult(createCardsFromString("AH TS TH QD QH"));
    Assert.assertEquals(HandResult.TWO_PAIR, handResult.getResult());

    handResult = new HandResult(createCardsFromString("1H 2S 3H 3D QH"));
    Assert.assertEquals(HandResult.ONE_PAIR, handResult.getResult());

    handResult = new HandResult(createCardsFromString("1H 2S 3H 7D QH"));
    Assert.assertEquals(HandResult.HIGH_CARD, handResult.getResult());
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
