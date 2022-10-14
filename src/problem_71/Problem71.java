package problem_71;

public class Problem71 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 1_000_000;
    int bestNumerator = -1;
    int bestDenominator = -1;
    double closest = -1;
    for (int i = 8; i < cutoff; i++) {
      double denominator = i;
      double numerator = Math.floor((denominator * 3) / 7);
      while (true) {
        double result = numerator / denominator;
        if (result < closest || result == 0) {
          //no point in checking any further -- even if these were co-primes, it wouldn't beat what we've seen so far
          break;
        }
        if (gcd((int)numerator, (int)denominator) == 1) {
          closest = result;
          bestNumerator = (int)numerator;
          bestDenominator = (int)denominator;
          break;
        }
        numerator--;
      }
    }
    System.out.printf("Best num and denom was %d/%d%n", bestNumerator, bestDenominator);
    return 0;
  }

  //1 % 2
  //2 , (2 % 1) = 0
  private static int gcd(int a, int b)
  {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
