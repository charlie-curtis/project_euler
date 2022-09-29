package problem_36;

public class Problem36 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 1_000_000;
    int answer = 0;
    for (int i = 1; i < cutoff; i++) {
      if (isPalindromeForBase(i, 10) && isPalindromeForBase(i, 2)) {
        answer+= i;
      }
    }
    return answer;
  }

  private static boolean isPalindromeForBase(int n, int base)
  {
    int original = n;
    int reverse = 0;
    while (n != 0) {
      reverse = reverse*base + n % base;
      n /= base;
    }
    return reverse == original;
  }
}
