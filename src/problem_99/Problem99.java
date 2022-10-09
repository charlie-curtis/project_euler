package problem_99;

import helpers.FileParser;

import java.math.BigInteger;

public class Problem99 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_99/base_exp.txt");
    String[] lines = parser.toStringArray();

    BigInteger max = BigInteger.ZERO;
    int bestLine = 0;
    for (int i = 0; i < lines.length; i++) {
      System.out.printf("Currently on line %d%n", i);
      String[] line = lines[i].split(",");
      String base = line[0];
      String exp = line[1];
      BigInteger current = BigInteger.valueOf(Long.parseLong(base)).pow(Integer.parseInt(exp));
      if (current.compareTo(max) == 1) {
        max = current;
        bestLine = i + 1;
      }

    }
    return bestLine;
  }
}
