package problem_70;

import helpers.PrimeCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This answer is similar to question #72, so i reused the base of my code from that problem
 */
public class Problem70 {

  private static final int CUTOFF = 10_000_000;
  private static final HashMap<Integer, Integer> computedTotients = new HashMap<>();

  public static void main(String[] args) {
    System.out.printf("The answer is %d\n", computeFast());
  }

  //private static int CUTOFF = 79180;
  public static long computeFast() {
    long startTime = System.currentTimeMillis();
    PrimeCalculator calculator = new PrimeCalculator(CUTOFF);
    double minRatio = Double.MAX_VALUE;
    int bestIndex = -1;
    for (int i = 2; i <= CUTOFF; i++) {
      if (i % 10000 == 0) {
        System.out.printf("%d of %d (%.2f percent)%n", i, CUTOFF, (double) i * 100 / CUTOFF);
      }

      int totient = getTotient(i, calculator);
      if (isPermutation(totient, i)) {
        double ratio = (double) i / totient;
        if (ratio < minRatio) {
          minRatio = ratio;
          bestIndex = i;
        }
      }

    }
    long endTime = System.currentTimeMillis();
    System.out.printf("ElapsedTime: %d%n", endTime - startTime);
    return bestIndex;
  }

  private static boolean isPermutation(int i, int j) {
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    while (i != 0 && j != 0) {
      list1.add(i % 10);
      list2.add(j % 10);
      i /= 10;
      j /= 10;
    }
    if (i != j) {
      //both should be 0 at this point
      return false;
    }
    Collections.sort(list1);
    Collections.sort(list2);
    for (int k = 0; k < list1.size(); k++) {
      if (list1.get(k) != list2.get(k)) {
        return false;
      }
    }
    return true;
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
}
