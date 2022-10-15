package problem_81;

import helpers.FileParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem81 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_81/matrix.txt");
    int cutoff = 80;
    int[][] input = parser.to2DIntArray();
    int[][] holder = new int[cutoff][cutoff];

    for (int i = 0; i < holder.length; i++) {
      Arrays.fill(holder[i], Integer.MAX_VALUE);
    }
    holder[holder.length -1][holder.length-1] = input[holder.length-1][holder.length-1];

    for (int i = holder.length - 1; i >= 0; i--) {
      for (int j = holder.length - 1; j >= 0; j--) {
        if (i == holder.length-1 && j == holder.length-1) {
          continue;
        }
        List<Integer> options = new ArrayList<>();
        if (i+1 < holder.length) {
          options.add(holder[i+1][j]);
        }
        if (j+1 < holder.length) {
          options.add(holder[i][j+1]);
        }
        int minOption = options.stream().min(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        holder[i][j] = input[i][j] + minOption;
      }
    }

    return holder[0][0];
  }
}
