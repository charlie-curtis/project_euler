package problem_63;

import java.math.BigInteger;

public class Problem63 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    int count = 0;
    for (int i = 1; i <= 1000; i++) {
      if (i % 100 == 0) {
        System.out.println(i);
      }
      for (int j = 1; j <= 1000; j++) {
        if (doesSatisfyEquation(i,j)) {
          count++;
        }
        //count += doesSatisfyEquation(i,j) ? 1: 0;
      }
    }
    return count;
  }

  private static boolean doesSatisfyEquation(int i, int j)
  {
    int number = i;
    int exponent = j;
    BigInteger result = BigInteger.valueOf(i).pow(exponent);
    return result.toString().length() == exponent;
  }
}
