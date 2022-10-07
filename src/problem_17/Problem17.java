package problem_17;

import java.util.HashMap;

public class Problem17 {

  private static HashMap<Integer, String> map;

  public static void main(String[] args) {
    map = getMap();
    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    int cutoff = 1000;
    int sum = 0;
    for (int i = 1; i <= 1000; i++) {
      sum += printNumber(i).replaceAll("\\s", "").length();
      System.out.printf("%s%n", printNumber(i));
    }
    return sum;
  }

  private static String printNumber(int number) {
    if (number % 10 == 0 && number < 100) {
      //special number
      return map.get(number);
    }
    if (number <= 20) {
      //special number
      return map.get(number);
    }

    if (number == 1000) {
      //special number
      return map.get(number);
    }

    String response = "";
    int val = number;
    if (val <= 99) {
      //number is between 21 and 999
      int msb = val / 10;
      int lsb = val % 10;
      response = (map.get(msb * 10));
      String remaining = printNumber(lsb);
      if (remaining != null) {
        response += " " + remaining;
      }
    } else {
      int msb = val / 100;
      response = map.get(msb) + " hundred";
      String remaining = printNumber(number - msb * 100);
      if (remaining != null) {
        response += " and " + remaining;
      }

    }
    return response;
  }

  private static HashMap<Integer, String> getMap() {
    HashMap<Integer, String> map = new HashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");
    map.put(4, "four");
    map.put(5, "five");
    map.put(6, "six");
    map.put(7, "seven");
    map.put(8, "eight");
    map.put(9, "nine");
    map.put(10, "ten");
    map.put(11, "eleven");
    map.put(12, "twelve");
    map.put(13, "thirteen");
    map.put(14, "fourteen");
    map.put(15, "fifteen");
    map.put(16, "sixteen");
    map.put(17, "seventeen");
    map.put(18, "eighteen");
    map.put(19, "nineteen");
    map.put(20, "twenty");
    map.put(30, "thirty");
    map.put(40, "forty");
    map.put(50, "fifty");
    map.put(60, "sixty");
    map.put(70, "seventy");
    map.put(80, "eighty");
    map.put(90, "ninety");
    map.put(100, "hundred");
    map.put(1000, "one thousand");
    return map;
  }
}
