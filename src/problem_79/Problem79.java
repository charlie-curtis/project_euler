package problem_79;

import java.util.Arrays;

public class Problem79 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    String[] input = getInput().split("\n");
    input = Arrays.stream(input).sorted().distinct().toArray(String[]::new);
    int[][] followers = new int[10][10];
    for (int i = 0; i < input.length; i++) {
      int digitA = Integer.parseInt(input[i].substring(0,1));
      int digitB = Integer.parseInt(input[i].substring(1,2));
      int digitC = Integer.parseInt(input[i].substring(2,3));
      followers[digitA][digitB]++;
      followers[digitA][digitC]++;
      followers[digitB][digitC]++;
    }

    for (int i = 0; i < followers.length; i++) {
      for (int j = 0; j < followers.length; j++) {
        if (followers[i][j] != 0) {
          System.out.printf("%d was followed by %d %d times%n", i, j, followers[i][j]);
        }
      }
    }
    return 0;
  }

  private static String getInput()
  {
   return "319\n" +
     "680\n" +
     "180\n" +
     "690\n" +
     "129\n" +
     "620\n" +
     "762\n" +
     "689\n" +
     "762\n" +
     "318\n" +
     "368\n" +
     "710\n" +
     "720\n" +
     "710\n" +
     "629\n" +
     "168\n" +
     "160\n" +
     "689\n" +
     "716\n" +
     "731\n" +
     "736\n" +
     "729\n" +
     "316\n" +
     "729\n" +
     "729\n" +
     "710\n" +
     "769\n" +
     "290\n" +
     "719\n" +
     "680\n" +
     "318\n" +
     "389\n" +
     "162\n" +
     "289\n" +
     "162\n" +
     "718\n" +
     "729\n" +
     "319\n" +
     "790\n" +
     "680\n" +
     "890\n" +
     "362\n" +
     "319\n" +
     "760\n" +
     "316\n" +
     "729\n" +
     "380\n" +
     "319\n" +
     "728\n" +
     "716\n";
  }
}
