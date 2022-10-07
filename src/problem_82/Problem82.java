package problem_82;

import helpers.FileParser;

import java.util.HashMap;
import java.util.Objects;

public class Problem82 {

  private static final HashMap<Cell, Integer> bestMoveNoGoingDown = new HashMap<>();
  private static final HashMap<Cell, Integer> bestMoveOverall = new HashMap<>();

  /**
   * This problem was tricky. Its a DP problem where no matter which direction you solve from, you will
   * encounter values that have not been memoized yet. This is because we are allowed to traverse this
   * square in 3 directions: forward, up, down. It's the up & down part that make the problem tricky.
   *
   * @param args
   */
  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
    //For some reason the recursive version has a bug where it is off by a small margin (~6%)
    //System.out.printf("The answer is %d\n", computeSolveRecursively());
  }

  private static int[][] getMockInput() {
    int[][] input = {
      {131, 673, 234, 103, 18},
      {201, 96, 342, 965, 150},
      {630, 803, 746, 422, 111},
      {537, 699, 497, 121, 956},
      {805, 732, 524, 37, 331}
    };
    return input;
  }

  public static long computeSolveRecursively() {
    FileParser parser = new FileParser("src/problem_82/matrix.txt");
    int[][] input = parser.to2DIntArray();

    //int[][] input = getMockInput();

    int min = Integer.MAX_VALUE;
    for (int j = input.length - 1; j >= 0; j--) {
      for (int i = input.length - 1; i >= 0; i--) {
        getBestMove(i, j, input, false);
      }
      for (int i = input.length - 1; i >= 0; i--) {
        getBestMove(i, j, input, true);
      }
    }
    for (int i = 0; i < input.length; i++) {
      min = Math.min(min, bestMoveOverall.get(new Cell(i, 0)));
    }
    return min;
  }

  public static int getBestMove(int i, int j, int[][] input, boolean canGoDown) {

    if (j == input.length - 1) {
      return input[i][j];
    }

    Cell cell = new Cell(i, j);
    if (!canGoDown && bestMoveNoGoingDown.containsKey(cell)) {
      return bestMoveNoGoingDown.get(cell);
    } else if (canGoDown && bestMoveOverall.containsKey(cell)) {
      return bestMoveOverall.get(cell);
    }

    //set originally to w/e the cell directly to your left is
    int min = getBestMove(i, j + 1, input, canGoDown) + input[i][j];

    if (!canGoDown) {
      if (i - 1 >= 0) {
        min = Math.min(min, getBestMove(i - 1, j, input, canGoDown) + input[i][j]);
      }
    } else {
      //get the cached value from the previous run
      min = Math.min(min, bestMoveNoGoingDown.get(new Cell(i, j)));

      if (i + 1 < input.length) {
        //see if you can beat anything by going down
        min = Math.min(min, getBestMove(i + 1, j, input, true) + input[i][j]);
      }
    }

    if (canGoDown) {
      bestMoveOverall.put(cell, min);
    } else {
      bestMoveNoGoingDown.put(cell, min);
    }
    return min;
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_82/matrix.txt");
    /*
    int[][] input = getMockInput();
     */
    int[][] input = parser.to2DIntArray();

    int[][] holder = new int[input.length][input.length];
    for (int i = holder.length - 1; i >= 0; i--) {
      holder[i][holder.length - 1] = input[i][holder.length - 1];
    }

    for (int j = holder.length - 2; j >= 0; j--) {
      for (int i = 0; i < holder.length; i++) {
        int minMove = input[i][j] + holder[i][j + 1];
        if (i - 1 >= 0) {
          if (holder[i - 1][j] != 0) {
            minMove = Math.min(minMove, holder[i - 1][j] + input[i][j]);
          } else {
            //check for best possible move upwards -- decreasing i
            int sumSeenSoFar = 0;
            for (int k = i - 1; k >= 0; k--) {
              sumSeenSoFar += input[k][j];
              minMove = Math.min(minMove, input[i][j] + sumSeenSoFar + holder[k][j + 1]);
            }
          }
        }
        if (i + 1 != holder.length) {
          if (holder[i + 1][j] != 0) {
            minMove = Math.min(minMove, holder[i + 1][j] + input[i][j]);
          } else {
            //check for best possible move downwards -- increasing i
            int sumSeenSoFar = 0;
            for (int k = i + 1; k < holder.length; k++) {
              sumSeenSoFar += input[k][j];
              minMove = Math.min(minMove, input[i][j] + sumSeenSoFar + holder[k][j + 1]);
            }
          }
        }
        holder[i][j] = minMove;
      }
    }

    int answer = Integer.MAX_VALUE;
    for (int i = 0; i < holder.length; i++) {
      answer = Math.min(answer, holder[i][0]);
    }
    return answer;
  }

  protected static class Cell {
    private final int i;
    private final int j;

    public Cell(int i, int j) {
      this.i = i;
      this.j = j;

    }

    public int hashCode() {
      return Objects.hash(i, j);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }

      if (!(obj instanceof Cell c)) {
        return false;
      }

      return (c.i == this.i) && (c.j == this.j);
    }
  }
}
