package problem_85;

public class Problem85 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int maxGridWidth = 50;
    int maxGridHeight = 90;
    int count = 0;
    int closet = Integer.MAX_VALUE;
    int lookingFor = 2_000_000;
    int answer = -1;
    for (int i = 1; i <= maxGridWidth; i++) {
      for (int j = 1; j <= maxGridHeight; j++) {

        count = getCountForGridSize(i, j);
        int diff = Math.abs(lookingFor - count);
        if (diff < closet) {
          closet = diff;
          answer = i*j;
          System.out.printf("Updating max for grid %dx%d to %d. The diff was %d%n", i,j, count, diff);
        }
      }
    }
    return answer;
  }

  private static int getCountForGridSize(int gridWidth, int gridHeight)
  {
    int count = 0;
    for (int i = 1; i <= gridWidth; i++) {
      for (int j = 1; j <= gridHeight; j++) {
        int posX = i;
        int posY = j;
        for (int k = 0; posX+k <=gridWidth; k++) {
          for (int l = 0; posY+l <=gridHeight; l++) {
            count++;
          }
        }
      }
    }
    return count;
  }
}
