package problem_38;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Problem38 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 1_000_000;
    long largestPanFound = Long.MIN_VALUE;
    for (int i = 2; i < cutoff; i++) {
      largestPanFound = Math.max(computeMaxPan(i), largestPanFound);
    }
    return largestPanFound;
  }

  private static long computeMaxPan(int n)
  {
    long maxPossibleValue = 987654321;
    long maxPan = Long.MIN_VALUE;

    long result = Long.MIN_VALUE;
    int k = 2;
    while (result < maxPossibleValue) {
      result = Long.parseLong(computeAndConcat(n, k));
      if (isPanDigitalNumber(result)) {
        maxPan = Math.max(maxPan, result);
      }
      k++;
    }

    return maxPan;
  }

  private static String computeAndConcat(int number, int k)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 1; i <= k; i++) {
      sb.append(number*i);
    }
    return sb.toString();
  }

  private static final int desiredHash = "123456789".hashCode();
  private static boolean isPanDigitalNumber(long n)
  {
    String s = Arrays.stream(String.valueOf(n).split("")).sorted().collect(Collectors.joining());
    return (s.hashCode() == desiredHash);
  }
}
