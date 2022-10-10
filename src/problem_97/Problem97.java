package problem_97;

import java.math.BigInteger;

public class Problem97 {

  public static void main(String[] args) {

    System.out.printf("The answer is %s\n", compute());
  }

  public static String compute() {
    BigInteger number1 = BigInteger.valueOf(28433);
    BigInteger number2 = BigInteger.valueOf(2).pow(7830457);
    BigInteger answer = number1.multiply(number2).add(BigInteger.ONE);

    String allDigits = answer.toString();
    String lastTenDigits = allDigits.substring(allDigits.length() - 10);

    return lastTenDigits;
  }
}
