package problem_78;

import helpers.PrimeCalculator;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Problem78 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int CUTOFF = 4000;

  private static int LOOKING_FOR = 1_000_000;

  private static HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Integer> memo = new HashMap<>();
  public static long compute() {
    getCombinations(CUTOFF, 1);


    Optional<Integer> answer = memo.entrySet().stream()
      .filter((entry) -> entry.getValue() % LOOKING_FOR == 0)
      .sorted((entryA, entryB) -> entryA.getKey().getKey().compareTo(entryB.getKey().getKey()))
      .map((entry) -> entry.getKey().getKey())
      .findFirst();

    return answer.orElse(-1);
  }

  private static int getCombinations(int n, int valueToConsider)
  {
    AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(n, valueToConsider);
    if (memo.containsKey(entry)) {
      return memo.get(entry);
    }
    if (n == 0 || valueToConsider == n) {
      return 1;
    }
    if (n <= 0 || valueToConsider > n) {
      return 0;
    }


    int sum = getCombinations()
    int sum = getCombinations(n, valueToConsider+1)
      + getCombinations(n-valueToConsider, valueToConsider);
    //System.out.printf("putting %d for %d%n", sum, n);
    memo.put(entry, sum);
    return sum;
  }
}
