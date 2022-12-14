package problem_9;

public class Problem9 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 1000;

    for (long i = 0; i <= cutoff; i++) {
      for (long j = i + 1; j <= cutoff; j++) {
        long k = cutoff - i - j;
        if (k >= 0 && (k > j) && (i * i + j * j == k * k)) {
          System.out.printf("Found %d, %d, %d%n", i, j, k);
          return (k * i * j);
        }
      }
    }
    throw new RuntimeException("answer should have been found");
  }
}
