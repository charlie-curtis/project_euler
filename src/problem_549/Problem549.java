package problem_549;

import java.math.BigInteger;
import java.util.HashMap;

public class Problem549 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int CUTOFF = 100_000_000;
  private static BigInteger zero = BigInteger.ZERO;
  public static long compute() {
    int sum = 0;

    for (int i = 2; i<= CUTOFF; i++) {
      sum += computeForGivenNumber(i);
    }
    return sum;
  }

  private static HashMap<Integer, BigInteger> memo = new HashMap<>();
  private static int computeForGivenNumber(int n)
  {
    BigInteger fact = BigInteger.valueOf(1);
    int i = 1;
    while (!(fact.mod(BigInteger.valueOf(n)).equals(zero))) {
      i++;
      fact = fact.multiply(BigInteger.valueOf(i));
    }
    return i;
  }
}
