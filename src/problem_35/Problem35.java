package problem_35;

import java.util.Arrays;
import java.util.HashSet;

public class Problem35 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    //int cutoff = 100;
    int cutoff = 1_000_000;
    int count = 0;
    computePrimesBelowValue(cutoff);
    for (int i = 2; i< cutoff; i++) {
      if (isCircularPrime(i)){
        count++;
      }
    }
    return count;
  }

  private static HashSet<Integer> set = new HashSet<>();
  private static void computePrimesBelowValue(int n)
  {
    boolean[] primeHolder = new boolean[n+1];
    Arrays.fill(primeHolder, true);

    for (int i = 2; i < primeHolder.length; i++) {
      if (!primeHolder[i]) {
        continue;
      }
      set.add(i);
      int k = 2;
      while (i * k < primeHolder.length) {
        primeHolder[i*k] = false;
        k++;
      }
    }

    //set.forEach(System.out::println);
    //System.out.printf("There are %d primes below %d%n", set.size(), n);
  }
  private static boolean isCircularPrime(int n)
  {
    int shiftedNumber = n;
    do {
      if (!set.contains(shiftedNumber)) {
        return false;
      }
      shiftedNumber = shiftNumber(shiftedNumber);
    }
    while (shiftedNumber != n);

    //System.out.printf("%d is a circular prime!%n", n);
    return true;
  }

  private static int shiftNumber(int n)
  {
    int multiplier = 1;
    while (multiplier < n) {
      multiplier *= 10;
    }
    multiplier /= 10;
    int lastDigit = n % 10;
    n = n / 10;
    return lastDigit*multiplier + n;
  }
}
