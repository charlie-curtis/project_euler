package problem_22;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Problem22 {

  public static void main(String[] args) throws Exception {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() throws Exception {
    List<String> names = Arrays.stream(getInput()).sorted().toList();

    long sum = 0;
    for (int i = 0; i < names.size(); i++) {
      long multiplier = i + 1;
      long weight = getWeightForWord(names.get(i));
      sum += multiplier * weight;
    }
    return sum;
  }

  private static int getWeightForWord(String s) {
    int sum = 0;
    int offset = 64;
    for (int i = 0; i < s.length(); i++) {
      sum += (int) s.charAt(i) - offset;
    }
    return sum;
  }

  private static String[] getInput() throws Exception {
    String filePath = new File("src/problem_22/names.txt").getAbsolutePath();
    BufferedReader br = new BufferedReader(
      new FileReader(filePath)
    );
    return br.readLine().replaceAll("\"", "").split(",");
  }
}
