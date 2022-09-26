package problem_18;

import java.util.stream.IntStream;

public class Problem18 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d%n", compute());
  }

  public static long compute() {
    int[][] input = getInput();
    int[][] answerHolder = new int[15][15];

    for (int i = 0; i < 15; i++) {
      for (int j = 0; j <= i; j++) {
        int max = input[i][j];
        if (i-1 >= 0) {
          max = Math.max(max, input[i][j] + answerHolder[i-1][j]);
          if (j-1 >= 0) {
            max = Math.max(max, input[i][j] + answerHolder[i - 1][j - 1]);
          }
        }
        answerHolder[i][j] = max;
      }
    }
    return IntStream.of(answerHolder[14]).max().getAsInt();
  }

  private static int[][] getInput()
  {
    int[][] input = new int[15][15];
    String inputString = stringInput();
    String[] line = inputString.split("\\n");
    for (int i = 0; i < input.length; i++) {
      String[] lineArray = line[i].split(" ");
      for (int j = 0; j<=i; j++) {
        input[i][j] = Integer.parseInt(lineArray[j]);
      }
    }
    return input;
  }

  private static String stringInput()
  {
    return "75\n" +
      "95 64\n" +
      "17 47 82\n" +
      "18 35 87 10\n" +
      "20 04 82 47 65\n" +
      "19 01 23 75 03 34\n" +
      "88 02 77 73 07 63 67\n" +
      "99 65 04 28 06 16 70 92\n" +
      "41 41 26 56 83 40 80 70 33\n" +
      "41 48 72 33 47 32 37 16 94 29\n" +
      "53 71 44 65 25 43 91 52 97 51 14\n" +
      "70 11 33 28 77 73 17 78 39 68 17 57\n" +
      "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
      "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
      "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
  }
}
