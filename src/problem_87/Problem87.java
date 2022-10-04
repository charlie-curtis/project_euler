package problem_87;

import helpers.PrimeCalculator;

public class Problem87 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 50_000_000;
    int iterationCutoff = 7071; //sqrt (50M)
    PrimeCalculator calculator = new PrimeCalculator(cutoff);
    int count = 0;
    for (int i = 2; i < iterationCutoff; i++) {
      System.out.println(i);
      for (int j = 2; j < iterationCutoff; j++) {
        for (int k = 2; k < iterationCutoff; k++) {
          long result = i*i + j*j*j + k*k*k*k;
          if (calculator.isPrime(i) && calculator.isPrime(j) && calculator.isPrime(k) && (result) < cutoff) {
            count++;
          }
        }
      }
    }
    return count;
  }
}
