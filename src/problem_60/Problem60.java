package problem_60;

import helpers.PrimeCalculator;

import java.math.BigInteger;
import java.util.List;

/**
 * I looked at ideas online to figure out this problem because I couldn't think of a non-brute force solution.
 * It turns out most of the solutions online recommend the bruteforce approach, and can get the answer in <30s.
 * That seems pretty quick, so i'm going to try that.
 */
public class Problem60 {

  public static void main(String[] args) {

    long start = System.currentTimeMillis();
    System.out.printf("The answer is %d\n", compute());
    long end = System.currentTimeMillis();
    System.out.printf("Elapsed: %ds %n", (end-start)/1000);
  }

  private static final int CUTOFF = 10_000;
  //private static final int CUTOFF = 25;
  private static PrimeCalculator calculator;
  private static long count = 0;

  private static long expectedLoopIterations = 0;

  private static void setExpectedLoopIterations()
  {
    int primeSize = calculator.getListOfPrimes().size();
    BigInteger temp = BigInteger.valueOf(primeSize);
    for (int i = primeSize - 1; i > primeSize-5 && i > 0; i--) {
      temp = temp.multiply(BigInteger.valueOf(i));
    }
    temp = temp.divide(BigInteger.valueOf(5*4*3*2));
    expectedLoopIterations = temp.longValue();
  }

  public static long compute() {
    calculator = new PrimeCalculator(CUTOFF);
    List<Integer> primeList = calculator.getListOfPrimes();
    setExpectedLoopIterations();

    System.out.printf("There are %d numbers in the primes%n", primeList.size());

    int bestAnswer = Integer.MAX_VALUE;
    //There are N pick 4 combinations we need to check
    for (int i = 0; i < primeList.size()-3; i++) {
      int num1 = primeList.get(i);
      System.out.printf("Beginning to check sets beginning with %d%n", num1);
      if (num1 > bestAnswer) {
        break;
      }
      for (int j = i + 1; j < primeList.size()-2; j++) {
        int num2 = primeList.get(j);
        if (num1 + num2 > bestAnswer) {
          break;
        }
        for (int k = j + 1; k < primeList.size()-1; k++) {
          int num3 = primeList.get(k);
          if (num1 + num2 +num3 > bestAnswer) {
            break;
          }
          for (int l = k + 1; l < primeList.size(); l++) {
            int num4 = primeList.get(l);
            if (num1 + num2 + num3 + num4 > bestAnswer) {
              break;
            }
            for (int m = l + 1; m < primeList.size(); m++) {
              count++;
              if (count % 100_000_000 == 0) {
                System.out.printf("Progress %d of %d (%.4f)%n", count, expectedLoopIterations, (double)count*100/expectedLoopIterations);
              }
              int num5 = primeList.get(m);
              if (num1 + num2 + num3 + num4 + num5 > bestAnswer) {
                break;
              }
              if (isValid(num1, num2, num3, num4, num5)) {
                int sum = num1 + num2+ num3+ num4+ num5;
                if (sum < bestAnswer) {
                  System.out.printf("Updating the sum to %d based on %d, %d, %d, %d, %d%n", sum, num1, num2, num3, num4, num5);
                  bestAnswer = sum;
                }
              }
            }
          }
        }
      }
    }
    return bestAnswer;
  }

  private static boolean isValid(int ...args)
  {
    /*
    for (int i = 0; i+1 < args.length; i++) {
      for (int j = i+1; j < args.length; j++) {
        int num1 = args[i];
        int num2 = args[j];
        String firstCombo = "" + num1 + num2;
        String secondCombo = "" + num2 + num1;

        if (!calculator.isPrime(Integer.parseInt(firstCombo)) ||
          !calculator.isPrime(Integer.parseInt(secondCombo))) {
          return false;
        }
      }
    }

     */
    return false;
  }
}
