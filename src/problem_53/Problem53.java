package problem_53;

import helpers.PrimeCalculator;

import java.math.BigInteger;

public class Problem53 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int CUTOFF = 100;
  public static long compute() {

    int count = 0;
    //straight forward brute force. Answer is computed within 1s
    for (int i = 1; i <= CUTOFF; i++) {
      for (int j = 1; j <= i; j++) {
        count += isCombinationAboveOneMillion(i, j) ? 1 : 0;
      }
    }

    return count;
  }

  //n! / (n-k)!k!
  //25 c 22
  //25! / (25-22)!3!
  //25 c 3
  //25! / (25-3)!3! = 22!3!
  static BigInteger oneMillion = BigInteger.valueOf(1_000_000);
  private static boolean isCombinationAboveOneMillion(int n, int k)
  {
    BigInteger result = BigInteger.valueOf(1);
    for (int i = n-k+1; i<=n; i++) {
      result = result.multiply(BigInteger.valueOf(i));
    }

    for (int i = 2; i <= k; i++) {
      result = result.divide(BigInteger.valueOf(i));
    }
    return result.compareTo(oneMillion) == 1;
  }
}
