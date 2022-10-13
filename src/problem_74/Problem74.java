package problem_74;

import com.sun.jdi.LongValue;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem74 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 1_000_000;
    //int cutoff = 221;
    int count = 0;
    int maxChain = 0;
    for (int i = 2; i < cutoff; i++) {
      int chain = computeMaxChain((long)i);
      if (chain > maxChain) {
        maxChain = chain;
      }

      count += chain == 60 ? 1 : 0;
    }
    return count;
  }

  private static int computeMaxChain(long n)
  {
    Set<Long> numbersSeen = new HashSet<>();
    while (!numbersSeen.contains(n)) {
      numbersSeen.add(n);
      long sum = 0;
      while (n > 0) {

        sum += getFactorial((int)n % 10);
        n /= 10;
      }
      n = sum;
    }
    return numbersSeen.size();
  }

  private static Long getFactorial(int n)
  {
    return switch (n) {
      case 0, 1 -> 1L;
      case 2 -> 2L;
      case 3 -> 6L;
      case 4 -> 24L;
      case 5 -> 120L;
      case 6 -> 720L;
      case 7 -> 5040L;
      case 8 -> 40320L;
      case 9 -> 362880L;
      default -> throw new RuntimeException();
    };
  }
}
