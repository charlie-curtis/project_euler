package problem_89;

import helpers.FileParser;

import java.util.Arrays;
import java.util.HashMap;

public class Problem89 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    initializeMap();
    FileParser parser = new FileParser("src/problem_89/roman.txt");
    String[] romanNumerals = parser.toStringArray();

    return Arrays.stream(romanNumerals).mapToInt(Problem89::getReductionAmount).sum();
  }

  private static int getReductionAmount(String romanNumeral)
  {
    int number = romanToInt(romanNumeral);
    String reducedRomanNumeral = intToRoman(number);
    return romanNumeral.length() - reducedRomanNumeral.length();
  }

  private static int[] boundaries = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  private static String[] representations = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
  private static String intToRoman(int num) {

    StringBuilder answer = new StringBuilder();
    for (int i = 0; i < boundaries.length; i++) {
      int boundary = boundaries[i];
      String repr = representations[i];
      while (num >= boundary) {
        answer.append(repr);
        num -= boundary;
      }
    }
    return answer.toString();
  }

  private static HashMap<String, Integer> map = new HashMap<>();
  private static void initializeMap()
  {
    map.put("M", 1000);
    map.put("CM", 900);
    map.put("D", 500);
    map.put("CD", 400);
    map.put("C", 100);
    map.put("XC", 90);
    map.put("L", 50);
    map.put("XL", 40);
    map.put("X", 10);
    map.put("IX", 9);
    map.put("V", 5);
    map.put("IV", 4);
    map.put("I", 1);
  }


  public static int romanToInt(String s) {


    int answer = 0;
    for (int i = 0; i < s.length(); i++) {
      Integer value = null;
      if (i < s.length() - 1) {
        //first try a "special" value
        value = map.get(s.substring(i, i + 2));
      }
      if (value != null) {
        //we read 2 characters this loop, so skip one
        i++;
      } else {
        value = map.get(s.substring(i, i + 1));
      }
      answer += value;
    }
    return answer;
  }
}
