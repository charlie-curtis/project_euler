package problem_28;

public class Problem28 {

  private static final int GRID_SIZE = 1001;
  private static final int[][] valueHolder = new int[GRID_SIZE][GRID_SIZE];

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    fillInArray(0, GRID_SIZE - 1, GRID_SIZE * GRID_SIZE, GRID_SIZE);
    //printGrid();
    return sumDiagonals();
  }

  private static void printGrid() {
    for (int i = 0; i < valueHolder.length; i++) {
      for (int j = 0; j < valueHolder.length; j++) {
        if (valueHolder[i][j] < 10) {
          System.out.printf(" %d ", valueHolder[i][j]);
        } else {
          System.out.printf("%d ", valueHolder[i][j]);
        }
      }
      System.out.println();
    }
  }

  private static int sumDiagonals() {
    int answer = 0;
    for (int i = 0; i < valueHolder.length; i++) {
      answer += valueHolder[i][i];
    }

    // 0 4, 1 3, 2 2, 3 1, 4 0
    for (int i = 0; i < valueHolder.length; i++) {
      int j = valueHolder.length - 1 - i;
      answer += valueHolder[i][j];
    }
    //middle value was counted twice
    answer--;
    return answer;
  }

  private static void fillInArray(int i, int j, int startingNumber, int distanceToFill) {
    if (startingNumber == 1) {
      //fill and return
      valueHolder[i][j] = 1;
      return;
    }
    int count = 0;
    //FILL from RIGHT TO LEFT
    while (count < distanceToFill) {
      valueHolder[i][j] = startingNumber;
      j--;
      startingNumber--;
      count++;
    }
    j++;
    startingNumber++;
    count = 0;
    //FILL from Top to BOTTOM
    while (count < distanceToFill) {
      valueHolder[i][j] = startingNumber;
      i++;
      startingNumber--;
      count++;
    }
    i--;

    count = 0;
    startingNumber++;
    //FILL from LEFT to RIGHT
    while (count < distanceToFill) {
      valueHolder[i][j] = startingNumber;
      j++;
      startingNumber--;
      count++;
    }
    j--;

    count = 0;
    startingNumber++;
    distanceToFill--;
    while (count < distanceToFill) {
      valueHolder[i][j] = startingNumber;
      i--;
      startingNumber--;
      count++;
    }
    i++;

    j--;
    fillInArray(i, j, startingNumber, distanceToFill - 1);
  }
}
