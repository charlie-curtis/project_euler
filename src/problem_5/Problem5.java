package problem_5;

import java.util.HashMap;

public class Problem5 {

  public static void main(String[] args) {

    System.out.printf("\nThe least common multiple is %d\n", getLeastCommonMultiple());
    //232792560
  }

  public static int getLeastCommonMultiple() {
    HashMap<Integer, Integer> primeNumberToCountMap = new HashMap<>();

    int i = 2;
    int endRange = 20;

    while (i <= endRange) {
      HashMap<Integer, Integer> answer = computePrimeFactors(i);
      answer.forEach(
        (divisor, count) -> primeNumberToCountMap.put(
          divisor, Math.max(count, primeNumberToCountMap.getOrDefault(divisor, 0))
        )
      );
      i++;
    }

    return primeNumberToCountMap.entrySet().stream().<Integer>mapMulti((entry, consumer) -> {
      Integer divisor = entry.getKey();
      int count = entry.getValue();
      for (int current = 0; current < count; current++) {
        consumer.accept(divisor);
      }
    }).reduce(1, (a,b) -> a*b);
  }

  public static HashMap<Integer, Integer> computePrimeFactors(int value) {

    HashMap<Integer, Integer> answer = new HashMap<>();
    int divisor = 2;
    while (value > 1) {
      int count = 0;
      while (value % divisor == 0) {
        count++;
        value /= divisor;
      }
      if (count != 0) {
        answer.put(divisor, count);
      }
      divisor++;
    }
    return answer;
  }
}


/**
 * Scratch notes to help with algorithm
 // 2 2
 // 3 3
 // 4 2 * 2
 // 5 5
 // 6 3*2
 // 7 7
 // 8 2 * 2 *2
 // 9 3 * 3
 // 10 5 * 2

 //, , , 3, , 2,
 // 2, 3, 2, 5, 7, 2, 3,
 // 2, 3, 2, 5, 7,

 //2, 3, 2, 5, 7, 2, 3,
 */
