package problem_6;

import java.util.stream.LongStream;

public class Problem6 {

  public static void main(String[] args) {

    System.out.printf("\nThe answer is %d\n", compute());
  }

  public static long compute() {
    long endRange = 100;

    long squareOfSums = (long) Math.pow(LongStream.range(1, endRange + 1).sum(), 2);
    long sumOfSquares = LongStream.range(1, endRange + 1).map(val -> val*val).sum();

    return Math.abs(sumOfSquares - squareOfSums);
  }

}
