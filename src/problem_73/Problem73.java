package problem_73;

public class Problem73 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 12_000;
    int count = 0;
    for (int i = 4; i <= cutoff; i++) {
      double denominator = i;
      double lowerBoundNumerator = Math.ceil(denominator / 3);
      double upperBoundNumerator = Math.floor(denominator / 2);
      double numerator = lowerBoundNumerator;
      while (numerator <= upperBoundNumerator) {
        if (gcd((int)numerator, (int)denominator) == 1) {
          count++;
        }
        numerator++;
      }
    }
    return count;
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
