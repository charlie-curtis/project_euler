package problem_87;

import helpers.PrimeCalculator;

import java.util.HashSet;
import java.util.Set;

public class Problem87 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  /**
   * I kept messing up this problem because I was counting the unique combinations of i, j, k where
   * i,j,k were all prime and i^2 + j^3 + k^4 < 50M. In reality, I should have been tracking the numbers
   * that satisfied this equation. I.e. I needed to dedupe the result of i^2 + j^3 + k^4.
   */
  public static long compute() {
    int cutoff = 50_000_000;
    Set<Integer> answerHolder = new HashSet<>();
    int iterationCutoff = 8000; //sqrt (50M)
    PrimeCalculator calculator = new PrimeCalculator(cutoff);
    for (long i = 2; i < iterationCutoff; i++) {
      if (!calculator.isPrime((int) i)) {
        continue;
      }
      for (long j = 2; j < iterationCutoff; j++) {
        if (!calculator.isPrime((int) j)) {
          continue;
        }
        for (long k = 2; k < iterationCutoff; k++) {
          if (!calculator.isPrime((int) k)) {
            continue;
          }
          long result = i * i + j * j * j + k * k * k * k;
          if (result >= cutoff) {
            break;
          }
          answerHolder.add((int) result);
        }
      }
    }
    return answerHolder.size();
  }
}
