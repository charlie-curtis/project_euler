package problem_55;

import java.math.BigInteger;

public class Problem55 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 10_000;
    int count = 0;
    for (int i = 1; i < cutoff; i++) {
      if (isLychrelNumber(BigInteger.valueOf(i), 0)) {
        count++;
      }
    }
    return count;
  }

  private static boolean isLychrelNumber(BigInteger n , int iteration) {
    if (iteration == 50) {
      return true;
    }

    BigInteger reverse = getReverse(n);
    if (reverse.equals(n) && iteration != 0) {
      //validate that this number isn't a palindrome
      return false;
    }
    return isLychrelNumber(reverse.add(n), ++iteration);
  }

  private static BigInteger getReverse(BigInteger n)
  {
    BigInteger sum = BigInteger.ZERO;
    while (!n.equals(BigInteger.ZERO)) {
      sum = sum.multiply(BigInteger.valueOf(10)).add(n.mod(BigInteger.TEN));
      n = n.divide(BigInteger.TEN);
    }
    return sum;
  }
}
