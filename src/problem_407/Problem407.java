package problem_407;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Problem407 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int CUTOFF = 10_000_000;
  //private static long CUTOFF = 1000;
  public static long compute() {
    /*
    int sum = 0;
    for (int i = 0; i < CUTOFF; i++) {
      for (int j = 0; j < CUTOFF; j++) {
        sum+= i;
      }
    }
    return sum;
    */
    return LongStream.range(1, CUTOFF+1).map(Problem407::getMaxForNumber).sum();
  }

  private static long getMaxForNumber(long n)
  {
    if (n % 10000 == 0) {
      System.out.printf("Progress: %d%n", n);
    }
    for (long i = n-1; i>=1; i--) {
      //i^i % n == i
      BigInteger result = BigInteger.valueOf(i).modPow(BigInteger.valueOf(2), BigInteger.valueOf(n));
      if (result.longValue() == i) {
        return i;
      }
    }
    return 0;
  }
}
