package problem_42;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class Problem42 {

  private static HashSet<Integer> triangleNumbers;

  public static void main(String[] args) throws Exception {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() throws Exception {
    initializeTriangleNumbers();
    isTriangleWord("SKY");
    String[] input = getInput();
    int count = 0;
    for (int i = 0; i < input.length; i++) {
      count += isTriangleWord(input[i]) ? 1 : 0;
    }
    return count;
  }

  private static void initializeTriangleNumbers() {
    triangleNumbers = new HashSet<>();
    int cutoff = 100_000_000;
    int i = 0;
    int value = 0;
    while (value <= cutoff) {

      value = i * (i + 1) / 2;
      triangleNumbers.add(value);
      i++;
    }
  }

  private static int getSumForWord(String s) {
    char[] chars = s.toCharArray();
    int sum = 0;
    for (int i = 0; i < chars.length; i++) {
      sum += chars[i] - 64;
    }
    return sum;
  }

  private static boolean isTriangleWord(String s) {
    int sum = getSumForWord(s);
    return triangleNumbers.contains(sum);
  }

  private static String[] getInput() throws Exception {
    String filePath = new File("src/problem_42/words.txt").getAbsolutePath();
    BufferedReader br = new BufferedReader(
      new FileReader(filePath)
    );
    return br.readLine().replaceAll("\"", "").split(",");
  }
}
