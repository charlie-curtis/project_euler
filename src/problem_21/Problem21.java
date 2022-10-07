package problem_21;

public class Problem21 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoffValue = 10000;
    int[] sums = new int[cutoffValue];
    for (int i = 1; i < cutoffValue; i++) {
      sums[i] = getSumOfDivisors(i);
    }

    int answer = 0;
    for (int i = 1; i < cutoffValue; i++) {
      int indexToCheck = sums[i];
      if (indexToCheck < cutoffValue && sums[indexToCheck] == i && indexToCheck != i) {
        System.out.println("adding " + i);
        answer += i;
      }
    }
    return answer;
  }

  private static int getSumOfDivisors(int n) {
    //start with sum = 1 and i = 2 so that we only count proper divisors (i.e. don't include n)
    int sum = 1;
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        boolean isPerfectSquare = (n / i == i);
        int other = n / i;
        sum += isPerfectSquare ? i : other + i;
      }
    }
    return sum;
  }


}
