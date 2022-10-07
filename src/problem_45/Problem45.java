package problem_45;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;

public class Problem45 {

  public static void main(String[] args) {

    //tried 55385. it was wrong
    //tried 15337768. it was wrong
    //I had the right answer the first time, but I entered the INDEX of the triangle number instead of the
    //actual triangle number (1533776805). The second time, I just copied the wrong number over
    System.out.printf("The answer is %s\n", compute());
  }

  public static String compute() {
    int cutoff = 1_000_000;
    HashSet<BigInteger> s1 = new HashSet<>();
    HashSet<BigInteger> s2 = new HashSet<>();
    HashSet<BigInteger> s3 = new HashSet<>();
    for (long n = 0; n < cutoff; n++) {
      s1.add(BigInteger.valueOf(n * (n + 1) / 2));
      s2.add(BigInteger.valueOf(n * (3 * n - 1) / 2));
      s3.add(BigInteger.valueOf(n * (2 * n - 1)));
    }

    return s1.stream()
      .filter(i -> s2.contains(i) && s3.contains(i))
      .max(Comparator.naturalOrder())
      .get().or(BigInteger.ZERO).toString();
  }
}
