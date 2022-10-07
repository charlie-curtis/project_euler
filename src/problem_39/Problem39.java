package problem_39;

public class Problem39 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  private static int findMaxSolutions(int desiredPerimeter) {
    int count = 0;
    for (int i = 1; i <= desiredPerimeter; i++) {
      for (int j = 1; j <= desiredPerimeter; j++) {
        int k = desiredPerimeter - i - j;
        if (doSidesSatisfyConstraints(i, j, k)) {
          System.out.printf("(%d, %d, %d) satisfies an equation for perimeter=%d%n", i, j, k, desiredPerimeter);
          count++;
        }
      }
    }
    return count;
  }

  private static boolean doSidesSatisfyConstraints(double a, double b, double c) {
    if (a <= 0 || b <= 0 || c <= 0) {
      return false;
    }

    //a^2 + b^2 = c^2
    return (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2));
  }

  public static long compute() {
    int cutoff = 1000;
    int max = Integer.MIN_VALUE;
    int maxIndex = Integer.MIN_VALUE;
    for (int i = 0; i <= cutoff; i++) {
      int result = findMaxSolutions(i);
      if (result > max) {
        maxIndex = i;
        max = result;
      }
    }
    return maxIndex;
  }
}
