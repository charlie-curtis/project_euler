package problem_10;

import java.util.Arrays;

public class Problem10 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int primeCutoff = 2_000_000;
    boolean[] isPrime = new boolean[primeCutoff + 1];
    Arrays.fill(isPrime, true);
    long sum = 0;
    for (int i = 2; i < primeCutoff; i++) {
      if (isPrime[i]) {
        sum += i;
        for (int j = 2; i * j < primeCutoff; j++) {
          isPrime[i * j] = false;
        }
      }
    }
    return sum;
  }
}
