package problem_32;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem32 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());

  }

  public static long compute() {
    String numbers = "000000000";
    computeRecursively(9, numbers, 0);
    return answer.stream().reduce(BigInteger.ZERO, BigInteger::add).longValue();
  }

  private static Set<BigInteger> answer = new HashSet<>();
  private static void computeRecursively(int numberToPlace, String numbers, int startIndex)
  {
    if (numberToPlace == 0) {
      getMaxProduct(numbers);
      return;
    } else if (startIndex == numbers.length()) {
      return;
    }

    char[] charArray = numbers.toCharArray();
    charArray[startIndex] = Character.forDigit(numberToPlace, 10);
    if (numbers.charAt(startIndex) == '0') {
      computeRecursively(numberToPlace - 1, String.valueOf(charArray), 0);
    }
    computeRecursively(numberToPlace, numbers, startIndex+1);
  }

  private static void getMaxProduct(String numbers)
  {
    for (int i = 0; i < numbers.length(); i++) {
      for (int j = i+1; j < numbers.length() - 1; j++) {
        BigInteger number1 = BigInteger.valueOf(Long.parseLong(numbers.substring(0, i+1)));
        BigInteger number2 = BigInteger.valueOf(Long.parseLong(numbers.substring(i+1, j+1)));
        BigInteger number3 = BigInteger.valueOf(Long.parseLong(numbers.substring(j+1)));

        if (number1.multiply(number2).equals(number3)) {
          answer.add(number3);
        }
      }
    }
  }
}
