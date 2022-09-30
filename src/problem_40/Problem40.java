package problem_40;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem40 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 1_000_000;
    Set<Integer> digitsToLookFor = new HashSet<>(List.of(1, 10, 100, 1_000, 10_000, 100_000, 1_000_000));
    int product = 1;
    int digitsSoFar = 0;
    int digitsFound = 0;
    for (int i = 1; i<= cutoff; i++) {
      if (digitsFound == digitsToLookFor.size()) {
        break;
      }
      int digitsToAdd = (int)Math.log10(i) + 1;
      for (int j = 0; j < digitsToAdd; j++) {
        digitsSoFar++;
        if (digitsToLookFor.contains(digitsSoFar)) {
          int digit = Integer.parseInt(String.valueOf(i).split("")[j]);
          System.out.printf("multiplying by %d because the digit is %d%n", digit, digitsSoFar);
          product *= digit;
          digitsFound++;
        }
      }
    }
    return product;
  }
}
