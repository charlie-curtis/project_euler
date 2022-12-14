package problem_72;

import helpers.PrimeCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This problem was probably one of the ones that took longer. I spent alot of time trying
 * to get this to run somewhat fast. The naive implementation takes too long -- hours maybe?
 * Not entirely sure. Didn't feel like waiting to find out.
 * <p>
 * The optimizations I made were:
 * - Use Euler's totient function for computing the # of co primes below n. Also cache those computations
 * for later use
 * - When finding prime factorizations, only attempt to divide by numbers that are prime
 * - If we're trying to find the totient of a prime number, short circuit and return n-1 instead of going through
 * the actual computation. This change alone reduced the running time from 30s to 800ms. This is because computing
 * attemping to determine the prime factorization of a large prime number takes alot of time.
 * <p>
 * <p>
 * In the end, it took ~800ms to compute the answer
 * (which was about 303B non-reducible improper fractions)
 */
public class Problem72 {

  private static final int CUTOFF = 1_000_000;
  private static final HashMap<Integer, Integer> computedTotients = new HashMap<>();

  public static void main(String[] args) {

    //System.out.printf("The answer is %d\n", compute());
    System.out.printf("The answer is %d\n", computeFast());
  }

  //private static int CUTOFF = 8;
  private static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  public static long computeFast() {
    long count = 0;
    long startTime = System.currentTimeMillis();
    PrimeCalculator calculator = new PrimeCalculator(CUTOFF);
    for (int i = 2; i <= CUTOFF; i++) {
      if (i % 10000 == 0) {
        System.out.printf("%d of %d (%.2f percent)%n", i, CUTOFF, (double) i * 100 / CUTOFF);
      }
      count += getTotient(i, calculator);

    }
    long endTime = System.currentTimeMillis();
    System.out.printf("ElapsedTime: %d%n", endTime - startTime);
    return count;
  }

  /**
   * Kinda hard to read, but there is alot of optimizations baked in to get the totient function
   * to return quickly
   */
  private static int getTotient(int n, PrimeCalculator calc) {
    if (calc.isPrime(n)) {
      computedTotients.put(n, n - 1);
      return computedTotients.get(n);
    }
    ArrayList<Integer> listOfPrimes = calc.getListOfPrimes();
    HashSet<Integer> primeFactors = new HashSet<>();
    int original = n;
    int valueToUseInTotientEquation = n;
    int previouslyComputedTotients = 1;

    for (int j = 0; j < listOfPrimes.size(); j++) {
      if (listOfPrimes.get(j) > n) {
        //no more values to check. n should be 1
        break;
      }
      if (computedTotients.containsKey(n)) {
        //we can short circuit here since totient functions are multiplicative
        valueToUseInTotientEquation /= n;
        previouslyComputedTotients *= computedTotients.get(n);
        break;
      }
      while (n % listOfPrimes.get(j) == 0) {
        primeFactors.add(listOfPrimes.get(j));
        n /= listOfPrimes.get(j);
      }
    }

    double val = 1;
    for (Integer k : primeFactors) {
      val *= (1 - (1 / (double) k));
    }
    //we can use previously computed totients to shorten our computation
    //so totient(n*m) = totient(n)*totient(m)
    computedTotients.put(original, (int) (val * valueToUseInTotientEquation * previouslyComputedTotients));
    return computedTotients.get(original);
  }

  public static long compute() {
    //1,3,5,15
    //3/15 6/15 9/15 12/15
    //5/15 10/15

    //14 - 6 = 8

    //1 2 4 5 10 20
    //19 terms
    // 2/20 4/20 6/20 8/20 10/20 12/20 14/20 16/20 18/20
    // 5/20 10/20 15/20


    //get the prime factors

    //If I is prime, then it will have a count of n-1. Easy. skip it
    //If i is not prime, then you need to subtract out the multiples (n-1) -
    int count = 0;
    for (int i = 1; i <= CUTOFF; i++) {
      if (i % 10000 == 0) {
        System.out.printf("%d of %d (%.2f percent)%n", i, CUTOFF, (double) i * 100 / CUTOFF);
      }
      for (int j = 1; j < i; j++) {
        if (gcd(j, i) != 1) {
          continue;
        }
        //System.out.printf("counting %d/%d%n", j, i);
        count++;
      }
    }
    return count;
  }
}
