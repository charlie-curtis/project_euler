package problem_83;

import helpers.FileParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Problem83 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int[][] input = getInput();

    int[][] dp = new int[input.length][input.length];
    dp[input.length-1][input.length-1] = input[input.length-1][input.length-1];

    //I think this needs to be called 4 times -- one for every direction. Calling it only 3 times produces the wrong
    //answer. Calling it multiple times is needed in order for propagations in the least-cost map to occur.
    //That behavior reminds me of the bellman-ford algo. I think I could also use dijkstras algo given their aren't
    //negative edges
    computeDp(input, dp, false);
    computeDp(input, dp, true);
    computeDp(input, dp, true);
    computeDp(input, dp, true);
    return dp[0][0];
  }

  private static void computeDp(int[][] input,
                                int[][] dp,
                                boolean considerAllDirections
  ) {
    for (int i = input.length-1; i >= 0; i--) {
      for (int j = input.length-1; j >=0; j--) {
        Integer downOption = (i+1 < input.length) ? dp[i+1][j] : null;
        Integer rightOption = (j+1 < input.length) ? dp[i][j+1] : null;
        Integer leftOption = (j-1 >=0) ? dp[i][j-1] : null;
        Integer upOption = (i-1 >= 0) ? dp[i-1][j] : null;
        ArrayList<Integer> options = new ArrayList<>();
        options.add(rightOption);
        options.add(downOption);
        if (considerAllDirections) {
          options.add(upOption);
          options.add(leftOption);
        }
        dp[i][j] = input[i][j] + getLeastCostPathFromOptions(options);
      }
    }
  }

  private static int getLeastCostPathFromOptions(ArrayList<Integer> options) {
    Optional<Integer> result = options.stream().filter(Objects::nonNull).min(Comparator.naturalOrder());
    //If there are no options passed in, return 0 - this handles the case where we are at the bottom right index
    return result.orElse(0);

  }

  private static int[][] getInput()
  {
    FileParser parser = new FileParser("src/problem_83/matrix.txt");
    return parser.to2DIntArray();
  }
}
