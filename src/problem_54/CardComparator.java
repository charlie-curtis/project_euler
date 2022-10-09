package problem_54;


import java.util.List;

public class CardComparator {

  private static int getCardRanking(Card card, boolean isAceHigh) {
    char c = card.value;
    if (!isAceHigh && c == 'A') {
      return 49;
    }
    return switch ((int) c) {
      case 'A' -> 62;
      case 'K' -> 61;
      case 'Q' -> 60;
      case 'J' -> 59;
      case 'T' -> 58;
      default -> (int) c;
    };
  }

  public static int aceHighCompare(Card cardA, Card cardB) {
    int rankA = getCardRanking(cardA, true);
    int rankB = getCardRanking(cardB, true);

    //put high cards first
    if (rankA > rankB) return -1;
    if (rankB > rankA) return 1;
    return 0;
  }

  public static int aceLowCompare(Card cardA, Card cardB) {
    int rankA = getCardRanking(cardA, false);
    int rankB = getCardRanking(cardB, false);

    //put high cards first
    if (rankA > rankB) return -1;
    if (rankB > rankA) return 1;
    return 0;
  }

  private static boolean areCardsSequential(List<Card> cards, boolean isAceHigh)
  {
    for (int i = 0; i < cards.size()-1; i++) {
      Card thisCard = cards.get(i);
      Card nextCard = cards.get(i+1);

      if (getCardRanking(thisCard, isAceHigh) -1 != getCardRanking(nextCard, isAceHigh)) {
        return false;
      }
    }
    return true;
  }

  public static boolean areCardsSequential(List<Card> cards)
  {
    cards.sort(CardComparator::aceHighCompare);
    if (areCardsSequential(cards, true)) {
      return true;
    }
    cards.sort(CardComparator::aceLowCompare);
    return areCardsSequential(cards, false);
  }
}
