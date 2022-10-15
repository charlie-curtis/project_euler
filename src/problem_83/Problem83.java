package problem_83;

import helpers.FileParser;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Problem83 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int[][] input = getInput();

    int[][] dp = new int[input.length][input.length];
    initialize(dp);

    dp[input.length-1][input.length-1] = input[input.length-1][input.length-1];

    int count = 0;
    //this propagation pattern reminds me of the bellman ford. I also wonder if we could use dijkstra here.
    boolean didChangesOccur = true;
    while (didChangesOccur) {
      count++;
      didChangesOccur = computeDp(input, dp);
    }

    System.out.printf("Map resolved in %d iterations%n", count);
    return dp[0][0];
  }

  private static boolean computeDp(int[][] input, int[][] dp) {
    boolean didChangesOccur = false;
    for (int i = input.length-1; i >= 0; i--) {
      for (int j = input.length-1; j >=0; j--) {
        if (i == input.length-1 && j == input.length-1) {
          continue;
        }
        Integer downOption = (i+1 < input.length) ? dp[i+1][j] : Integer.MAX_VALUE;
        Integer rightOption = (j+1 < input.length) ? dp[i][j+1] : Integer.MAX_VALUE;
        Integer leftOption = (j-1 >=0) ? dp[i][j-1] : Integer.MAX_VALUE;
        Integer upOption = (i-1 >= 0) ? dp[i-1][j] : Integer.MAX_VALUE;
        List<Integer> options = List.of(rightOption, leftOption, downOption, upOption) ;
        int prev = dp[i][j];
        dp[i][j] = input[i][j] + getLeastCostPathFromOptions(options);
        if (dp[i][j] != prev) {
          didChangesOccur = true;
        }
      }
    }
    return didChangesOccur;
  }

  private static int getLeastCostPathFromOptions(List<Integer> options) {
    Optional<Integer> result = options.stream().min(Comparator.naturalOrder());
    //If there are no options passed in, return 0 - this handles the case where we are at the bottom right index
    return result.orElse(0);
  }

  private static void initialize(int[][] dp)
  {
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
  }

  private static int[][] getInput()
  {
    FileParser parser = new FileParser("src/problem_83/matrix.txt");
    return parser.to2DIntArray();
  }
}
