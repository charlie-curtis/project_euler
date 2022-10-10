package problem_80;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Problem80 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    return IntStream.range(1, 101) //first 100 natural numbers
        .filter(num -> Math.sqrt(num) % 1 != 0) //filter out perfect squares:w
        .mapToObj(num -> {
          //get sqrt with 100 decimal digits -- include some extra padding
          MathContext context = new MathContext(115);
          return BigDecimal.valueOf(num).sqrt(context).toString();
        })
        //convert the string into an array, take the first 100 numbers
        .flatMap(s1 ->
            Arrays.stream(s1.split(""))
                .filter(s2 -> s2.charAt(0) != '.')
                .limit(100)
        )
        .mapToLong(Long::parseLong).sum(); //cast and sum
  }
}
