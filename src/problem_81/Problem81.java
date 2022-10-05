package problem_81;

import helpers.FileParser;

public class Problem81 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_81/matrix.txt");
    int cutoff = 80;
    int[][] input = parser.to2DIntArray();
    int[][] holder = new int[cutoff][cutoff];

    holder[cutoff-1][cutoff-1] = input[cutoff-1][cutoff-1];
    for (int j = holder.length - 2; j >= 0; j--) {
      holder[holder.length-1][j] = input[holder.length-1][j] + holder[holder.length-1][j+1];
    }
    for (int i = holder.length - 2; i >= 0; i--) {
      holder[i][holder.length-1] = input[i][holder.length-1] + holder[i+1][holder.length-1];
    }

    for (int i = holder.length - 2; i >=0; i--) {
      for (int j = holder.length - 2; j>=0; j--) {
        holder[i][j] = input[i][j] + Math.min(holder[i][j+1], holder[i+1][j]);
      }
    }

    return holder[0][0];
  }
}
