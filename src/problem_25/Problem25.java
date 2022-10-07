package problem_25;

import java.math.BigInteger;

public class Problem25 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    BigInteger f1 = BigInteger.valueOf(1);
    BigInteger f2 = BigInteger.valueOf(1);
    BigInteger temp;
    int i = 0;
    while (!shouldTerminateLoop(f2)) {
      temp = f1.add(f2);
      f1 = f2;
      f2 = temp;
      i++;
    }
    //to get the fib index, take i and add 2
    return i + 2;
  }

  private static boolean shouldTerminateLoop(BigInteger f2) {
    return (f2.toString().length() >= 1000);
  }
}
