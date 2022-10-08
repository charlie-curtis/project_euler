package problem_54;

import helpers.FileParser;
import helpers.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static problem_54.PokerHand.makeHands;

public class Problem54 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_54/poker.txt");
    String[] input = parser.toStringArray();

    int count = 0;
    for(int i = 0; i < input.length; i++) {
      String line = input[i];
      List<PokerHand> hands = PokerHand.makeHands(line);
      if (hands.get(0).compareTo(hands.get(1)) > 0) {
        count++;
      }
    }
    return count;
  }
}
