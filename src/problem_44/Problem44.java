package problem_44;

import java.util.HashMap;

public class Problem44 {

  public static void main(String[] args) {

    compute();
  }

  private static HashMap<Long, Long> indexToValMap = new HashMap<>();
  private static HashMap<Long, Long> valToIndexMap = new HashMap<>();
  public static void compute() {

    long count = 0;
    int cutoff = 5_000;

    for (long i = 1; i < cutoff; i++) {
      long val = i*(3*i - 1)/2;
      indexToValMap.put(i, val);
      valToIndexMap.put(val, i);
    }
    long minDistance = Long.MAX_VALUE;
    for (long i = 1; i < cutoff; i++) {
      for (long j = i+1; j < cutoff; j++) {
        count++;
        if (count % 100_000_000 == 0) {
          System.out.printf("%,d%n", count);
        }
        long val1 = indexToValMap.get(i);
        long val2 = indexToValMap.get(j);
        if (!valToIndexMap.containsKey(val1 + val2) || !valToIndexMap.containsKey(val2 - val1)) {
          continue;
        }
        if (minDistance > j-i) {
          System.out.printf("Updated for values %d and %d%n", i, j);
          minDistance = j - i;
          System.out.printf("The Pk - Pj = %d%n", indexToValMap.get(j) - indexToValMap.get(i));
        }
      }
    }
  }
}
