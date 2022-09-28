package problem_23;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem23 {

  private static final int CUTOFF = 28123;

  public static void main(String[] args) {

    //tried 391286750. it was wrong
    //tried 391285755. it was wrong. WOOPS. I was returning numbers that ARE the sum of abundant numbers
    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    List<Integer> abundantNumbers = new ArrayList<>();
    Set<Integer> verifiedNumbers = new HashSet<>();
    for (int i = 2; i <= CUTOFF; i++) {
      if (isAbundantNumber(i)) {
        abundantNumbers.add(i);
        addNumbersToVerifiedSet(abundantNumbers, verifiedNumbers);
      }
    }

    int sumOfVerifiedNumbers = verifiedNumbers.stream().mapToInt(i -> i).sum();
    int sumOfAllNumbers = CUTOFF * (CUTOFF + 1) / 2;
    return sumOfAllNumbers - sumOfVerifiedNumbers;
  }

  private static void addNumbersToVerifiedSet(List<Integer> abundantNumbers, Set<Integer> verifiedNumbers)
  {
    int lastNumber = abundantNumbers.get(abundantNumbers.size()-1);
    for (Integer numbers: abundantNumbers) {
      int numberToAdd = numbers + lastNumber;
      if (numberToAdd <= CUTOFF) {
        verifiedNumbers.add(numberToAdd);
      }
    }
  }

  private static boolean isAbundantNumber(int n)
  {
    int sum = 1; //1 is a divisor of all numbers
    for (int i = 2; i<= Math.sqrt(n); i++) {
      if (n % i == 0) {
        //its a divisor
        if (n / i == i) {
          //its a perfect square
          sum+=i;
        } else {
          sum += i + (n/i);
        }
      }
    }
    return sum > n;
  }
}
