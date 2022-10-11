package problem_43;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem43 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());

  }

  public static long compute() {
    String numbers = "xxxxxxxxxx";
    computeRecursively(9, numbers, 0);
    return answer.stream().reduce(BigInteger.ZERO, BigInteger::add).longValue();
  }

  private static Set<BigInteger> answer = new HashSet<>();
  private static void computeRecursively(int numberToPlace, String numbers, int startIndex)
  {
    if (numberToPlace == -1) {
      storeResultIfValid(numbers);
      return;
    } else if (startIndex == numbers.length()) {
      return;
    }

    char[] charArray = numbers.toCharArray();
    charArray[startIndex] = Character.forDigit(numberToPlace, 10);
    if (numbers.charAt(startIndex) == 'x') {
      computeRecursively(numberToPlace - 1, String.valueOf(charArray), 0);
    }
    computeRecursively(numberToPlace, numbers, startIndex+1);
  }

  private static void storeResultIfValid(String numbers)
  {
    int[] divisors = {2, 3, 5, 7, 11, 13, 17};
    int i = 1;
    while (i + 2 < numbers.length()) {
      String num = "" + numbers.charAt(i) + numbers.charAt(i+1) + numbers.charAt(i+2);
      if (Integer.parseInt(num) % divisors[i-1] != 0) {
        return;
      }
      i++;
    }
    answer.add(BigInteger.valueOf(Long.parseLong(numbers)));
  }
}
