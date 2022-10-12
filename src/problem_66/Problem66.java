package problem_66;

public class Problem66 {

  /**
   * Note, when I inevitably revisit this problem, I should use pell's algorithm.
   * I tried bruteforcing below, and it didn't work. The necessary X > 50M
   * @param args
   */
  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();
    System.out.printf("The answer is %f\n", compute());
    System.out.printf("The count was %d%n", count);
    long endTime = System.currentTimeMillis();
    System.out.printf("Time taken %ds%n", (endTime-startTime)/1000);

  }

  public static double compute() {
    double max = 0;
    int bestIndex = 0;
    for (int i = 2; i <= 1000; i++) {
      System.out.println("Processing " + i);
      if (Math.sqrt(i) % 1 == 0) {
        continue;
      }
      double potential = findSolution(i);
      if (potential > max) {
        System.out.printf("Updating max to %.2f and best D to %d%n", potential, i);
        max = potential;
        bestIndex = i;
      }

    }
    return bestIndex;
  }

  private static int count = 0;
  private static double findSolution(int d)
  {
    for (double x = 1; x <5_000_000; x++) {
      double multiplier = 1_000_000_000_000L;
      Double answer = findSolutionForGivenX(x, d, 1, (double)d*multiplier);
      if (answer != null) {
        //System.out.printf("found answer: %.2f %d %.2f %n", x, d, answer);
        return x;
      }
    }
    count++;
    System.out.printf("Could not find value for D = %d%n", d);
    return 0;
  }

  private static Double findSolutionForGivenX(double x, double d, double yStartRange, double yEndRange) {
    if (yEndRange < 0) {
      System.out.println("overflow detected");
    }
    if (yStartRange == yEndRange) {
      Double result = x * x - d * (yStartRange * yStartRange);
      if (result != 1) {
        return null;
      }
      return yEndRange;
    }
    if (yStartRange > yEndRange) {
      Double result = x * x - d * (yStartRange * yStartRange);
      Double result1 = x * x - d * (yEndRange * yEndRange);
      if (result == 1) return yStartRange;
      if (result == 1) return yEndRange;
      return null;
    }

    Double middle = Math.floor((yEndRange + yStartRange)/2);

    Double result = x * x - d * (middle * middle);
    if (result == 1) {
      return middle;
    }
    //need to increase y
    if (result > 1) {
      yStartRange = middle+1;
    } else {
      yEndRange = middle-1;
    }
    return findSolutionForGivenX(x, d, yStartRange, yEndRange);
  }
}
