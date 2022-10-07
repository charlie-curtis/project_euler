package problem_76;

public class Problem76 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  //private static int CUTOFF = 4000;
  private static int CUTOFF = 10_000;


  public static long compute() {

    //subtract 1 because n + 0 doesn't count as a sum of 2 numbers
   return bottomUp(CUTOFF) -1;

  }

  private static long bottomUp(int n)
  {
    //row is n
    //col is valueToConsider
    long[][] dp = new long[n+1][n+1];

    for (int j = 0; j< dp.length; j++) {
      dp[0][j] = 1;
    }


    for (int i = 1; i <= n; i++) {
      for (int j = 1; j<=n; j++) {

        //This will be the sum if we don't use this value at
        //all in our answer. So for example, if value = 3, and n = 10,
        //this will count all the scenarios where we add to 10 without
        //using a 3 (i.e. 5+2+1+1+1 is one such example)
        long sum = dp[i][j-1];

        //if we use value once, it looks like
        //dp[n-i][j-1]
        //if we use it twice, it looks like dp[n-2*i][j-1]
        for (int k = 1; k*j <= i; k++) {
          sum += dp[i-k*j][j-1];
        }

        dp[i][j] = sum;
      }
    }
    return dp[n][n];
  }
}
