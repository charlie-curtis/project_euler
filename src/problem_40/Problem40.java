package problem_40;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem40 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    Set<Integer> indexesToLookFor = new HashSet<>(List.of(1, 10, 100, 1_000, 10_000, 100_000, 1_000_000));
    int product = 1;
    int currentIndex = 0;
    int numberOfIndexesFound = 0;
    int i = 1;
    while (numberOfIndexesFound != indexesToLookFor.size()) {
      int digitsToAdd = (int) Math.log10(i) + 1;
      for (int j = 0; j < digitsToAdd; j++) {
        currentIndex++;
        if (indexesToLookFor.contains(currentIndex)) {
          int digit = Integer.parseInt(String.valueOf(i).split("")[j]);
          System.out.printf("multiplying by %d because the digit's place is %d%n", digit, currentIndex);
          product *= digit;
          numberOfIndexesFound++;
        }
      }
      i++;
    }
    return product;
  }
}
