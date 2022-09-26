package problem_14;


import java.util.HashMap;

public class Problem14 {

  private static HashMap<Long, Integer> map;
  public static void main(String[] args) {
    System.out.printf("The answer is %d\n", compute());
  }

  public static int compute() {
    final int cutoff = 1_000_000;

    map = new HashMap<>();
    int maxSequence = 0;
    long indexOfMax = 0;

    for (int i = 1; i < cutoff; i++) {
      int currentSequence = getLongestSequenceForNumber(i);
      if (maxSequence < currentSequence) {
        maxSequence = currentSequence;
        indexOfMax = i;
      }
    }
    System.out.printf("found %d to have the longest chain of %d%n", indexOfMax, maxSequence);
    return (int) indexOfMax;
  }
  private static int getLongestSequenceForNumber(long num) {
    if (num <= 0) {
      throw new RuntimeException("0 is not a valid input");
    }
    if (map.containsKey(num)) {
      return map.get(num);
    }
    int value;
    if (num == 1) {
      value = 1;
    } else {
      long prev = num % 2 == 0 ? num/2 : 3*num + 1;
      value = 1 + getLongestSequenceForNumber(prev);
    }
    map.put(num, value);
    return value;
  }

}
