package problem_82;

import helpers.FileParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem82 {

  /**
   * This problem was tricky. Its a DP problem where no matter which direction you solve from, you will
   * encounter values that have not been memoized yet. This is because we are allowed to traverse this
   * square in 3 directions: right, up, down. It's the up & down part that make the problem tricky.
   *
   * @param args
   */
  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  //returns TRUE if any changes were made to the map. If it returns false, then we know the map is done
  //propagating changes
  private static boolean computeDp(int[][] input, int[][] holder)
  {
    boolean didAnyChangesOccur = false;
    for (int j = holder.length-2; j >= 0; j--) {
      for (int i = holder.length-1; i >=0 ; i--) {
        List<Integer> options = new ArrayList<>();

        if (j + 1 < holder.length) {
          options.add(holder[i][j + 1]);
        }
        if (i + 1 < holder.length) {
          options.add(holder[i + 1][j]);
        }
        if (i - 1 >= 0) {
          options.add(holder[i - 1][j]);
        }
        int minMove = options.stream().min(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        int prev = holder[i][j];
        holder[i][j] = input[i][j] + minMove;
        didAnyChangesOccur = didAnyChangesOccur || holder[i][j] != prev;
      }
    }
    return didAnyChangesOccur;
  }
  public static long compute() {
    FileParser parser = new FileParser("src/problem_82/matrix.txt");
    int[][] input = parser.to2DIntArray();

    int[][] holder = new int[input.length][input.length];
    for (int i = 0; i < holder.length; i++) {
      Arrays.fill(holder[i], Integer.MAX_VALUE);
    }
    for (int i = holder.length-1; i>=0; i--) {
      holder[i][holder.length-1] = input[i][holder.length-1];
    }

    int count = 0;
    boolean didChangesOccur = true;
    while (didChangesOccur) {
      count++;
      didChangesOccur = computeDp(input, holder);
    }

    System.out.printf("Map resolved in %d iterations%n", count);

    return Arrays.stream(holder).map(row -> row[0]).min(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
  }
}
