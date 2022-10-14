package problem_95;

import helpers.DivisorCalculator;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Problem95 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int cutoff = 1_000_000;
  private static int[] divisorSums = new int[cutoff];
  public static long compute() {
    int max = 0;
    int smallest = Integer.MAX_VALUE;
    for (int i = 1; i < 1_000_000; i++) {
      int[] result = getMaxChain(i);
      int chain = result[0];
      if (chain > max) {
        max = chain;
        smallest = result[1];
        System.out.printf("Updating max chain to %d for value %d %n", max, i);
      }
    }
    return smallest;
  }

  private static int[] getMaxChain(int n)
  {
    Set<Integer> seen = new HashSet<>();
    int startingPoint = n;
    int count = 0;
    int[] defaultReturn = { Integer.MIN_VALUE, Integer.MIN_VALUE };
    do {
      count++;
      seen.add(n);
      n = getProperDivisorSum(n);
      if (n >= 1_000_000) {
        return defaultReturn;
      }
    } while (n != startingPoint && !seen.contains(n));

    if (n == startingPoint) {
      int[] answer = {count, seen.stream().min(Comparator.naturalOrder()).get()};
      return answer;
    }
    return defaultReturn;
  }

  private static int getProperDivisorSum(int n)
  {
    if (divisorSums[n] == 0) {
      divisorSums[n] = DivisorCalculator.getProperDivisors(n).stream().reduce(0, Integer::sum);
    }
    return divisorSums[n];
  }

}
