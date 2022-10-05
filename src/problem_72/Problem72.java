package problem_72;

import helpers.PrimeCalculator;

import java.util.ArrayList;
import java.util.HashSet;

public class Problem72 {

  public static void main(String[] args) {

    //System.out.printf("The answer is %d\n", compute());
    System.out.printf("The answer is %d\n", computeFast());
  }

  //private static int CUTOFF = 1_000_000;
  private static int CUTOFF = 1_000_000;
  private static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a %b);
  }
  public static long computeFast() {
    int count = 0;
    PrimeCalculator calculator = new PrimeCalculator(CUTOFF);
    ArrayList<Integer> listOfPrimes = calculator.getListOfPrimes();
    for (int i = 1; i <= CUTOFF; i++) {
      if (i % 10000 == 0) {
        System.out.printf("%d of %d (%.2f percent)%n", i, CUTOFF, (double)i/CUTOFF);
      }
      int amountToAdd = 0;
      if (calculator.isPrime(i)) {
       amountToAdd = (i-1);
      } else {
        amountToAdd += (i-1) - getMultiplesOfPrimeFactorCount(i, listOfPrimes);
      }
      count+= amountToAdd;
      //System.out.printf("adding %d for number %d%n", amountToAdd, i);

    }
    return count;
  }

  private static int getMultiplesOfPrimeFactorCount(int n, ArrayList<Integer> listOfPrimes)
  {
    int original = n;
    HashSet<Integer> mySet = new HashSet<>();
    for (int i = 0; i < listOfPrimes.size(); i++) {
      int current = listOfPrimes.get(i);
      if (current > n) {
        break;
      }
      boolean didRun = false;
      while (n % current == 0) {
        didRun = true;
        n /= current;
      }
      int k = 1;
      while (k*current < original && didRun) {
        mySet.add(k*current);
        k++;
      }
    }
    return mySet.size();
  }

  public static long compute() {
    //1,3,5,15
    //3/15 6/15 9/15 12/15
    //5/15 10/15

    //14 - 6 = 8

    //1 2 4 5 10 20
    //19 terms
    // 2/20 4/20 6/20 8/20 10/20 12/20 14/20 16/20 18/20
    // 5/20 10/20 15/20


    //get the prime factors

    //If I is prime, then it will have a count of n-1. Easy. skip it
    //If i is not prime, then you need to subtract out the multiples (n-1) -
    int count = 0;
    for (int i = 1; i <= CUTOFF; i++) {
      for (int j = 1; j < i; j++) {
        if (gcd(j,i) != 1) {
          continue;
        }
        //System.out.printf("counting %d/%d%n", j, i);
        count++;
      }
    }
    return count;
  }
}
