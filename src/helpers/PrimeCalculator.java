package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PrimeCalculator {

  private int cutoff;
  private boolean[] isPrimeHolder;
  private ArrayList<Integer> listOfPrimes = new ArrayList<>();

  public PrimeCalculator(int cutoff)
  {
    this.cutoff = cutoff;
    initializePrimeHolder();
  }

  public ArrayList<Integer> getListOfPrimes()
  {
    return this.listOfPrimes;
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

    for (int i = 2; i < isPrimeHolder.length; i++) {
      if (isPrimeHolder[i]) {
        this.listOfPrimes.add(i);
      }
    }
  }
}
