package helpers;

import java.util.Arrays;

public class PrimeCalculator {

  private int cutoff;
  private boolean[] isPrimeHolder;

  public PrimeCalculator(int cutoff)
  {
    this.cutoff = cutoff;
    initializePrimeHolder();
  }

  public boolean isPrime(int n)
  {
    return isPrimeHolder[n];
  }

  private void initializePrimeHolder()
  {
    isPrimeHolder = new boolean[this.cutoff+1];
    Arrays.fill(isPrimeHolder, true);
    isPrimeHolder[1] = false;
    isPrimeHolder[0] = false;
    for (int i = 2; i <= this.cutoff; i++) {
      if (isPrimeHolder[i]) {
        int k = 2;
        while (i*k <= this.cutoff) {
          isPrimeHolder[i*k] = false;
          k++;
        }
      }
    }
  }
}