package problem_5;

import java.util.HashMap;

public class Problem5 {

  public static void main(String[] args) {
    System.out.printf("\nThe least common multiple is %d\n", getLeastCommonMultiple()); //232792560
  }

  public static int getLeastCommonMultiple() {
    HashMap<Integer, Integer> primeNumberToCountMap = new HashMap<>();

    int endRange = 20;

    for (int i = 2; i <= endRange; i++) {
      computeAndStorePrimeFactors(i, primeNumberToCountMap);
    }

    return primeNumberToCountMap.entrySet().stream().<Integer>mapMulti((entry, consumer) -> {
      Integer divisor = entry.getKey();
      int count = entry.getValue();
      for (int current = 0; current < count; current++) {
        consumer.accept(divisor);
      }
    }).reduce(1, (a, b) -> a * b);
  }

  public static void computeAndStorePrimeFactors(int value, HashMap<Integer, Integer> map) {
    int divisor = 2;
    while (value > 1) {
      int count = 0;
      while (value % divisor == 0) {
        count++;
        value /= divisor;
      }
      //if this divisor has a higher count than what is previously stored, update it.
      map.put(divisor, Math.max(count, map.getOrDefault(divisor, 0)));
      divisor++;
    }
  }
}


/**
 * Scratch notes to help with algorithm
 * // 2 2
 * // 3 3
 * // 4 2 * 2
 * // 5 5
 * // 6 3*2
 * // 7 7
 * // 8 2 * 2 *2
 * // 9 3 * 3
 * // 10 5 * 2
 * <p>
 * //, , , 3, , 2,
 * // 2, 3, 2, 5, 7, 2, 3,
 * // 2, 3, 2, 5, 7,
 * <p>
 * //2, 3, 2, 5, 7, 2, 3,
 */
