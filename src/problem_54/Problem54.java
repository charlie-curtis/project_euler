package problem_54;

import helpers.FileParser;

import java.util.List;

public class Problem54 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_54/poker.txt");
    String[] input = parser.toStringArray();

    int count = 0;
    for (int i = 0; i < input.length; i++) {
      String line = input[i];
      List<PokerHand> hands = PokerHand.makeHands(line);
      PokerHand handA = hands.get(0);
      PokerHand handB = hands.get(1);
      if (handA.isBetterThan(handB)) {
        count++;
      }
    }
    return count;
  }
}
