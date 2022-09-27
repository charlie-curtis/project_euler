package problem_20;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem20 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    List<Integer> list =  IntStream.range(1, 101).boxed().collect(Collectors.toList());

    BigInteger result = list.stream().map(BigInteger::valueOf).reduce(BigInteger.valueOf(1), (a, b) -> a.multiply(b));

    return Arrays.stream(result.toString().split("")).mapToInt(Integer::parseInt).sum();
  }
}
