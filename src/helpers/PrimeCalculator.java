package helpers;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeCalculator {

  private int cutoff;
  private boolean[] isPrimeHolder;
  private final ArrayList<Integer> listOfPrimes = new ArrayList<>();

  public PrimeCalculator(int cutoff) {
    this.cutoff = cutoff;
    initializePrimeHolderAndPrimeList();
  }

  protected PrimeCalculator()
  {

  }

  public void setCutoff(int cutoff)
  {
    this.cutoff = cutoff;
  }

  public static PrimeCalculator quickInitialize(int cutoff)
  {
    PrimeCalculator calc = new PrimeCalculator();
    calc.setCutoff(cutoff);
    calc.initializePrimeHolder();
    return calc;
  }

  public ArrayList<Integer> getListOfPrimes() {
    return this.listOfPrimes;
  }


  public boolean isPrime(int n) {
    return isPrimeHolder[n];
  }

  private void initializePrimeHolder()
  {
    isPrimeHolder = new boolean[this.cutoff + 1];
    Arrays.fill(isPrimeHolder, true);
    isPrimeHolder[1] = false;
    isPrimeHolder[0] = false;
    for (int i = 2; i <= this.cutoff; i++) {
      if (isPrimeHolder[i]) {
        int k = 2;
        while ((long)i * k <= this.cutoff) {
          isPrimeHolder[i * k] = false;
          k++;
        }
      }
    }
  }
  private void initializePrimeHolderAndPrimeList() {

    for (int i = 2; i < isPrimeHolder.length; i++) {
      if (isPrimeHolder[i]) {
        this.listOfPrimes.add(i);
      }
    }
  }
}
