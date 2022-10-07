package problem_56;

import java.math.BigInteger;
import java.util.Arrays;

public class Problem56 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    int maxDigitalSum = Integer.MIN_VALUE;
    for (int i = 1; i <= 100; i++) {
      for (int j = 1; j <= 100; j++) {
        BigInteger number = BigInteger.valueOf(i).pow(j);
        int digitalSum = Arrays.stream(number.toString().split("")).mapToInt(Integer::parseInt).sum();
        maxDigitalSum = Math.max(maxDigitalSum, digitalSum);
      }
    }
    return maxDigitalSum;
  }
}
