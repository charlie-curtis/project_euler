package problem_7;

import java.util.ArrayList;

public class Problem7 {

  public static void main(String[] args) {

    System.out.printf("\nThe answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 10_001;

    ArrayList<Integer> primes = new ArrayList<>();
    int i = 2;
    while (primes.size() <= cutoff) {
      boolean isPrime = true;
      for (int j = 0; j < primes.size(); j++) {
        if (i % primes.get(j) == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        primes.add(i);
      }
      i++;
    }

    return primes.get(cutoff - 1);
  }

}
