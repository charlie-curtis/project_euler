package problem_27;

import java.util.Arrays;

public class Problem27 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    initializePrimeHolder();

    int maxA = 1000;
    int maxB = 1000;
    int maxProduct = 0;
    int maxFound = 0;
    for (int i = -1*maxA; i <= maxA; i++) {
      for (int j = -1*maxB; j<= maxB; j++) {
        int count = 0;
        int n = 0;
        int current = n*n + i*n + j;
        while(current <= CUTOFF && current > 0 && isPrime(current)) {
          count++;
          n++;
          current = n*n + i*n + j;
        }
        if (count > maxFound) {
          maxFound = count;
          System.out.printf("new record a,b = %d,%d%n", i, j);
          maxProduct = i*j;
        }
        if (current > CUTOFF) {
          System.out.printf("exited because cutoff was too low for a,b = %d,%d%n", i, j);
        }
      }
    }
    return maxProduct;
  }

  private static int CUTOFF = 1_000_000;
  private static boolean[] isPrimeHolder;

  private static boolean isPrime(int n)
  {
    return isPrimeHolder[n];
  }

  private static void initializePrimeHolder()
  {
    isPrimeHolder = new boolean[CUTOFF+1];
    Arrays.fill(isPrimeHolder, true);
    isPrimeHolder[1] = false;
    for (int i = 2; i <= CUTOFF; i++) {
      if (isPrimeHolder[i]) {
        int k = 2;
        while (i*k <= CUTOFF) {
          isPrimeHolder[i*k] = false;
          k++;
        }
      }
    }
  }
}
