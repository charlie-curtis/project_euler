package problem_50;

import helpers.PrimeCalculator;

import java.util.List;

public class Problem50 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int CUTOFF = 1_000_000;
  public static long compute() {
    PrimeCalculator calculator = new PrimeCalculator(CUTOFF);
    List<Integer> primeList = calculator.getListOfPrimes();

    int maxStreak = 0;
    int maxStartingIndex = 0;
    for (int i = 0; i < primeList.size(); i++) {
      int sum = 0;
      for (int j = i; j < primeList.size(); j++) {
        int current = primeList.get(j);
        if (sum+current >= CUTOFF ) {
          break;
        }
        sum+= current;

        if (calculator.isPrime(sum) && (j-i+1) > maxStreak) {
          maxStartingIndex = i;
          maxStreak = j - i + 1;
        }
      }
    }

    int answer = 0;
    for (int i = maxStartingIndex; i < maxStartingIndex + maxStreak; i++) {
      answer += primeList.get(i);
    }
    return answer;
  }
}
