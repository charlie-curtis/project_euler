package problem_29;

import java.math.BigInteger;
import java.util.HashSet;

public class Problem29 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    HashSet<BigInteger> set = new HashSet<>();
    int cutoff = 100;
    for (int a = 2; a<= cutoff; a++) {
      for (int b = 2; b <= cutoff; b++) {
        set.add(BigInteger.valueOf(a).pow(b));
      }
    }
    return set.size();
  }
}
