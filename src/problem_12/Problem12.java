package problem_12;

public class Problem12 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int targetDivisorCount = 500;
    long sum = 0;

    for (int i = 1; true; i++) {
      sum+= i;
      int actualDivisors = getNumberOfDivisors(sum);
      if (actualDivisors % 100 == 0) {
        System.out.printf("Currently at %d divisors%n", actualDivisors);
      }
      if (actualDivisors >= targetDivisorCount) {
        break;
      }
    }
    return sum;
  }

  public static int getNumberOfDivisors(long n) {

    int count = 0;
    for (int i = 1; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        //if it's a perfect square, then incrment divisors by 1. Else increment divisors by 2
        count += n / i == i ? 1:2;
      }
    }
    return count;
  }
}
