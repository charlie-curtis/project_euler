package problem_16;

import java.math.BigInteger;
import java.util.Arrays;

public class Problem16 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  //multiply 2^100 and sum the digits of the answer
  public static long compute() {
    BigInteger result = (new BigInteger("2")).pow(1000);
    return Arrays.stream(result.toString().split("")).mapToInt(Integer::parseInt).sum();
  }
}
