package problem_3;

public class Problem3 {

  public static final long TARGET_VALUE = 600_851_475_143L;
  //public static final long TARGET_VALUE = 13195;
  public static void main(String [] args) {

    System.out.printf("\nThe largest prime factor of %d is %d\n", TARGET_VALUE, getLargestPrimeFactor());
  }

  public static long getLargestPrimeFactor() {

    long n = TARGET_VALUE;
    long i = 2;

    long largestPrime = 0;
    while (i <= n) {
      while (n % i == 0) {
        largestPrime = i;
        n = n / i;
        System.out.printf("Found %d to be a prime factor\n", i);
      }
      i++;
    }
    return largestPrime;
  }
}
