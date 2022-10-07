package problem_34;

import java.math.BigInteger;

public class Problem34 {

  //I didn't know what the upperbound was, so I just pragmatically picked some upperbound.
  //I guess the idea is that at some high value of n, it becomes impossible for the sum of the factorials to compete
  // with the # of digits in n. Take 5555555 as an example. The sum of the factorials is only 600
  private static final int CUTOFF = 1_000_000;

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int answer = 0;
    for (int i = 10; i < CUTOFF; i++) {
      int n = i;
      BigInteger factorialSum = BigInteger.ZERO;
      while (n != 0) {
        int lastDigit = n % 10;
        factorialSum = factorialSum.add(BigInteger.valueOf(getFactorial(lastDigit)));
        n /= 10;
      }
      BigInteger maxInteger = BigInteger.valueOf(Integer.MAX_VALUE);
      if (factorialSum.compareTo(maxInteger) != 1 && factorialSum.intValue() == i) {
        answer += i;
      }
    }
    return answer;
  }

  private static long getFactorial(int n) {
    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
