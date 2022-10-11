package problem_26;

import java.math.BigDecimal;
import java.math.MathContext;

public class Problem26 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int max = 0;
    int bestIndex = 0;
    for (int i = 2; i < 1000; i++) {
      int potential = getMaxRepeating(i);
      if (potential > max) {
        max = potential;
        bestIndex = i;
        System.out.printf("Updating best to %d because it had a length of %d%n", bestIndex, max);
      }
    }
    return bestIndex;
  }

  private static int getMaxRepeating(int n)
  {
    String decimal = BigDecimal.ONE.
      divide(BigDecimal.valueOf(n), new MathContext(10000)).toString().substring(2);
    if (decimal.length() <= 1) {
      return 1;
    }
    for (int i = 0; i < decimal.length(); i++) {
      int result = getRepeatingSequenceLengthStartingFromIndex(decimal, i);
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }

  private static int getRepeatingSequenceLengthStartingFromIndex(String decimal, int leftPointer)
  {
    char left = decimal.charAt(leftPointer);
    int rightPointer = leftPointer +1;
    char right;
    while (rightPointer < decimal.length()) {
      right = decimal.charAt(rightPointer);
      if (left != right) {
        rightPointer++;
        continue;
      }
      if (2*rightPointer <= decimal.length()) {
        //compare strings
        String pattern1 = decimal.substring(leftPointer, rightPointer);
        String pattern2 = decimal.substring(rightPointer, rightPointer + (rightPointer - leftPointer));
        if (pattern1.equals(pattern2) && doesPatternSatisfyString(decimal, pattern1, leftPointer)) {
          return pattern1.length();
        }
      }
      rightPointer++;
    }
    return 0;
  }

  private static boolean doesPatternSatisfyString(String decimal, String pattern, int startIndex)
  {
    for (int i = startIndex; i + pattern.length()-1 < decimal.length(); i = i + pattern.length()) {
      if (!decimal.startsWith(pattern, i)) {
        return false;
      }
    }
    return true;
  }
}
