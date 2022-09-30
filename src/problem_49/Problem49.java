package problem_49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Problem49 {

  public static void main(String[] args) {

    System.out.printf("Computing answer %n");
    compute();
  }

  public static long compute() {
    initializePrimes();
    Integer[] primes = primeHolder.toArray(Integer[]::new);
    for (int i = 0; i < primes.length; i++) {

      int number = primes[i];
      //if (number == 1487 || number == 4817 || number == 8147) {
        //continue;
      //}
      if (number == 1487) {
       System.out.println(number);
      }
      int shiftedNumber = number;
      ArrayList<Integer> myList = new ArrayList<>();
      do {
        if(primeHolder.contains(shiftedNumber)) {
          myList.add(shiftedNumber);
        }
        shiftedNumber = shiftNumber(shiftedNumber);

      } while (shiftedNumber != number);

      myList.sort(Comparator.naturalOrder());
      //if (myList.size() >= 3 && (myList.get(1) - myList.get(0) == myList.get(2) - myList.get(1))) {
      if (myList.size() >= 3) {
       myList.forEach(k -> System.out.printf("%d, ", k));
       System.out.println();
      }
    }
    return 0;
  }

  private static int shiftNumber(int n)
  {
    //take the last number and move it to the front
    int lastDigit = n % 10;
    int multiplier = 1000;
    n/=10;
    //5234 -> 523
    //523 + 4000 -> 4523
    return n + lastDigit * multiplier;

  }


  private static int CUTOFF = 10000;
  private static HashSet<Integer> primeHolder = new HashSet<>();
  private static boolean[] sievePrimes = new boolean[CUTOFF];

  private static void initializePrimes()
  {
    Arrays.fill(sievePrimes, true);
    sievePrimes[1] = false;
    for (int i = 2; i < CUTOFF; i++) {
      if (!sievePrimes[i]) {
        continue;
      }
      if (i >= 1000 && i <= 9999) {
        primeHolder.add(i);
      }
      int k = 2;
      while (i * k < CUTOFF) {
        sievePrimes[i * k] = false;
        k++;
      }
    }
  }
}
