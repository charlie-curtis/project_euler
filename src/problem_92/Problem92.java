package problem_92;

import java.util.HashMap;

public class Problem92 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  static int  CUTOFF = 10_000_000;
  static HashMap<Integer, Integer> stoppingPoint = new HashMap<>();
  public static long compute() {
    int count = 0;
    for (int i = 1; i < CUTOFF; i++) {
      if (getStoppingPoint(i) == 89) {
        count++;
      }
    }
    return count;
  }

  private static int getStoppingPoint(int num) {
    int originalNumber = num;
    while (num != 1 && num != 89) {
      if (stoppingPoint.containsKey(num)) {
        return stoppingPoint.get(num);
      }
      int temp = 0;
      while (num > 0) {
        int lastDigit = num % 10;
        temp += lastDigit * lastDigit;
        num /= 10;
      }
      num = temp;
    }
    stoppingPoint.put(originalNumber, num);
    return stoppingPoint.get(originalNumber);
  }
}
