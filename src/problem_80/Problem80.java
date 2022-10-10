package problem_80;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Problem80 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    long sum = 0;
    for (int i = 2; i <= 100; i++) {
      if (Math.sqrt(i) % 1 == 0) {
        continue;
      }
      MathContext context = new MathContext(115);
      String result = BigDecimal.valueOf(i).sqrt(context).toString();
      int j = 0;
      while (j < 101) {
        if (result.charAt(j) == '.') {
          j++;
          continue;
        }
        sum+= Integer.parseInt(result.substring(j, j+1));
        j++;
      }
    }
    return sum;
  }
}
