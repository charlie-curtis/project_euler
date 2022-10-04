package problem_52;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Problem52 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int CUTOFF = 1_000_000;
  public static long compute() {
    for (int i = 1; i < CUTOFF; i++) {
      boolean foundAnswer = true;
      int hashCode = getHashForNumber(i);
      for (int j = 2; j<=6; j++) {
        int secondHashCode = getHashForNumber(j*i);
        if (secondHashCode != hashCode) {
          foundAnswer = false;
          break;
        }
      }
      if (foundAnswer) {
        return i;
      }
    }
    return 0;
  }

  private static int getHashForNumber(int n)
  {
    String s = String.valueOf(n);
    return Arrays.stream(s.split("")).sorted().collect(Collectors.joining()).hashCode();
  }
}
