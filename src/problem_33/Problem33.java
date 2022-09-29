package problem_33;

public class Problem33 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    for (int i = 0; i <= 99; i++){
      for (int j = 1; j <= 99; j++){
        if (isCurious(i, j)) {
          System.out.printf("%d and %d are curious%n", i, j);
        }
      }
    }
    return 0;
  }

  private static boolean isCurious(int i, int j)
  {
    if (i % 10 == 0 && j % 10 == 0) {
      return false;
    }
    int[] num1Digits = new int[2];
    int[] num2Digits = new int[2];

    num1Digits[1] = i % 10;
    num1Digits[0] = (i / 10) % 10;

    num2Digits[1] = j % 10;
    num2Digits[0] = (j / 10) % 10;

    
    return false;
  }
}
