package problem_4;

public class Problem4 {

  public static void main(String [] args) {

    //tried 580085. It wasn't right
    System.out.printf("\nThe largest palindrome product is %d\n", getPalindromeProduct());
  }

  public static long getPalindromeProduct() {
    long rangeBegin = 100;
    long rangeEnd = 999;

    long max = 0;
    for (long i = rangeEnd; i >= rangeBegin; i--) {
      for (long j = i; j >= rangeBegin; j--) {
        if (isPalindrome(i*j)) {
          max = Math.max(max, i*j);
        }
      }
    }

    return max;
  }

  public static boolean isPalindrome(long value) {
    long sum = 0;
    long originalValue = value;
    while (value > 0) {
      sum = sum * 10 + value % 10;
      value = value / 10;
    }
    return sum == originalValue;
  }
}
