package problem_83;

import helpers.FileParser;

import java.util.ArrayList;
import java.util.Comparator;
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
    //answer
    computeDp(input, dp, true, true, false, false);
    computeDp(input, dp, true, true, true, true);
    computeDp(input, dp, true, true, true, true);
    computeDp(input, dp, true, true, true, true);
    return dp[0][0];
  }

  private static void computeDp(int[][] input,
                                int[][] dp,
                                boolean canGoDown,
                                boolean canGoRight,
                                boolean canGoLeft,
                                boolean canGoUp
  ) {
    for (int i = input.length-1; i >= 0; i--) {
      for (int j = input.length-1; j >=0; j--) {
        Integer downOption = null;
        Integer rightOption = null;
        Integer leftOption = null;
        Integer upOption = null;
        if (j+1 < input.length && canGoRight) {
          rightOption = dp[i][j+1];
        }
        if (i+1 < input.length && canGoDown) {
          downOption = dp[i+1][j];
        }
        if (i-1 >= 0 && canGoUp) {
          upOption = dp[i-1][j];
        }
        if (j-1 >=0 && canGoLeft) {
          leftOption = dp[i][j-1];
        }
        ArrayList<Integer> vals = new ArrayList<>();
        if (canGoUp) vals.add(upOption);
        if (canGoDown) vals.add(downOption);
        if (canGoRight) vals.add(rightOption);
        if (canGoLeft) vals.add(leftOption);
        int val = input[i][j] + getMinFromInts(vals);
        dp[i][j] = dp[i][j] == 0 ? val : Math.min(dp[i][j], val);
      }
    }
  }

  private static int getMinFromInts(ArrayList<Integer> vals) {
    Optional<Integer> result = vals.stream().filter(i -> i != null).min(Comparator.naturalOrder());
    return result.isPresent() ? result.get() : 0;

  }

  private static int[][] getInput()
  {
    FileParser parser = new FileParser("src/problem_83/matrix.txt");
    return parser.to2DIntArray();
  }
}
