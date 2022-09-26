package problem_15;

public class Problem15 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    final int GRID_SIZE = 20;
    long[][] holder = new long[GRID_SIZE+1][GRID_SIZE+1];

    for (int i = 0; i <= GRID_SIZE; i++) {
      holder[i][0] = 1;
    }
    for (int j = 0; j <= GRID_SIZE; j++) {
        holder[0][j] = 1;
    }

    for (int i = 1; i <= GRID_SIZE; i++) {
      for (int j = 1; j <= GRID_SIZE; j++) {
        holder[i][j] = holder[i-1][j] + holder[i][j-1];
      }
    }

    return holder[GRID_SIZE][GRID_SIZE];
  }
}
