package problem_17;

import java.util.HashMap;

public class Problem17 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    int cutoff = 1000;
    var map = getMap();
    for (int i = 1; i <= 1000; i++) {
      int val = i;
      StringBuffer sb = new StringBuffer();
      if (val <= 20 || val == 1000) {
        sb.append(map.get(val));
      } else if (val > 20 && val < 100) {
        int firstNum = val % 10;
        val /= 10;
        int secondNum = val % 10;
        sb.append(map.get(secondNum * 10));
        if (firstNum != 0) {
          sb.append(" " + map.get(firstNum));
        }
      } else {
        int firstNum = val % 10;
        val /= 10;
        int secondNum = val % 10;
        val /= 10;
        int thirdNum = val % 10;
        sb.append(map.get(thirdNum) + " hundred");
        if (firstNum != 0 || secondNum != 0) {
          sb.append(" and ");
          if (secondNum != 0) {
            sb.append(map.get(secondNum*10));
          }
          if (firstNum != 0) {
            sb.append(" " + map.get(firstNum));
          }
        }
      }
      System.out.println(sb.toString().trim());
    }

    return 0;
  }

  private static HashMap<Integer, String> getMap()
  {
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
