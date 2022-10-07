package problem_30;

import java.math.BigInteger;

/**
 * This problem is similar to problem 34, so i copy/pasted much of the code
 */
public class Problem30 {

  //I didn't know what the upperbound was, so I just pragmatically picked some upperbound.
  //I guess the idea is that at some high value of n, it becomes impossible for the sum of the factorials to compete
  // with the # of digits in n. Take 5555555 as an example. The sum of the factorials is only 600
  private static final int CUTOFF = 1_000_000;

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    long answer = 0;
    for (int i = 2; i < CUTOFF; i++) {
      int n = i;
      BigInteger sumOfDigitsToTheFifthPower = BigInteger.ZERO;
      while (n != 0) {
        int lastDigit = n % 10;
        sumOfDigitsToTheFifthPower = sumOfDigitsToTheFifthPower.add(BigInteger.valueOf(raiseToFifthPower(lastDigit)));
        n /= 10;
      }
      BigInteger maxInteger = BigInteger.valueOf(Integer.MAX_VALUE);
      if (sumOfDigitsToTheFifthPower.compareTo(maxInteger) != 1 && sumOfDigitsToTheFifthPower.intValue() == i) {
        answer += i;
      }
    }
    return answer;
  }

  private static long raiseToFifthPower(int n) {
    long result = 1;
    for (int i = 0; i < 5; i++) {
      result *= n;
    }
    return result;
  }
}
