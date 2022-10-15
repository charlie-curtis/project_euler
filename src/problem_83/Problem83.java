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

    //I think this needs to be called 4 times -- one for every direction. Calling it only 3 times produces the wrong
    //answer. Calling it multiple times is needed in order for propagations in the least-cost map to occur.
    //That behavior reminds me of the bellman-ford algo. I think I could also use dijkstras algo given their aren't
    //negative edges
    computeDp(input, dp);
    computeDp(input, dp);
    computeDp(input, dp);
    computeDp(input, dp);
    return dp[0][0];
  }

  private static void computeDp(int[][] input, int[][] dp) {
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
        dp[i][j] = input[i][j] + getLeastCostPathFromOptions(options);
      }
    }
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
