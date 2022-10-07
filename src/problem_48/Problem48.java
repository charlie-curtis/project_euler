package problem_48;

import java.math.BigInteger;

public class Problem48 {

  public static void main(String[] args) {

    //tried 6084369740. it was wrong.
    //It was because of a long overflow. Need to be careful when dealing with really big numbers
    System.out.printf("The answer is %s\n", compute());
  }

  public static String compute() {
    int CUTOFF = 1000;
    BigInteger answer = BigInteger.ZERO;
    for (int i = 1; i <= CUTOFF; i++) {
      BigInteger nextTerm = BigInteger.valueOf(i);
      answer = answer.add(nextTerm.pow(i));
    }

    char[] chars = answer.toString().toCharArray();
    char[] lastDigits = new char[10];
    int j = chars.length - 1;
    for (int i = 9; i >= 0; j--, i--) {
      lastDigits[i] = chars[j];
    }
    return String.valueOf(lastDigits);
  }
}
