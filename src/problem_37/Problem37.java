package problem_37;

import java.util.Arrays;

public class Problem37 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int cutoff = 1_000_000;
  public static long compute() {
    System.out.printf("Checking the primes that are less than %d%n", cutoff);

    computePrimes();

    int sum = 0;
    int found = 0;
    for (int i = 10; i < cutoff; i++) {
      if (isTruncatablePrime(i)) {
        //System.out.printf("%d is truncatable%n", i);
        sum+=i;
        found++;
      }

    }
    System.out.printf("found %d numbers for a sum of %d%n", found, sum);
    return sum;
  }

  private static boolean isTruncatablePrime(int n)
  {
    int multiplier = 1;
    while (multiplier < n) {
      multiplier *= 10;
    }
    int originalMultiplier = multiplier;

    //3797 % 10000
    //3797 % 1000
    //3797 % 100
    //3797 % 10
    //stop when multipler = 1;
    while (multiplier > 1) {
      if (!isPrime(n % multiplier)) {
        return false;
      }
      multiplier /= 10;
    }

    multiplier = originalMultiplier / 10;
    //3797 / 1000 = 3
    //3797 / 100 =  37
    //3797 / 10 = 379
    //3797 / 1 = 3797 (Note we already checked that above. This is for consistency)
    while (multiplier != 0) {
      if (!isPrime(n / multiplier)) {
        return false;
      }
      multiplier /= 10;
    }
    return true;
  }

  private static boolean isPrime(int n)
  {
    boolean result = primes[n];
    return result;
  }
  private static boolean[] primes = new boolean[cutoff];
  private static void computePrimes()
  {
    Arrays.fill(primes, true);
    primes[1] = false;
    for (int i = 2; i < cutoff; i++) {
      if (primes[i]) {
        int k = 2;
        while (k*i < cutoff) {
          primes[k*i] = false;
          k++;
        }
      }
    }
  }
}
