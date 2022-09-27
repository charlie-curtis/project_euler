package problem_19;

import java.util.HashMap;

public class Problem19 {

  private static HashMap<Integer, String> daysOfWeek;
  private static HashMap<Integer, Integer> monthToDayCount;
  public static void main(String[] args) {

    initialize();
    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    //Jan 1, 1900 is a Monday -> that means Jan 1, 1901 is a
    int mod = 365 % 7;
    System.out.printf("January 1, 1901 mod 7 was %s%n", mod);

    //mod 1 -> so Jan 1, 1901 is a tuesday
    int answer = 0;
    int daysSinceFirstTuesday = 0;
    for (int i = 1901; i <= 2000; i++) {
      for (int j = 1; j <= 12; j++) {
        if (daysSinceFirstTuesday % daysOfWeek.size() == 5) {
          answer++;
        }

        int daysInMonth = monthToDayCount.get(j);
        if (j == 2 && isLeapYear(i)) {
          daysInMonth = monthToDayCount.get(j)+1;
        }
        daysSinceFirstTuesday += daysInMonth;
      }
    }

    return answer;
  }

  private static void initialize()
  {
    daysOfWeek = new HashMap<>();
    daysOfWeek.put(0, "Tuesday");
    daysOfWeek.put(1, "Wednesday");
    daysOfWeek.put(2, "Thursday");
    daysOfWeek.put(3, "Friday");
    daysOfWeek.put(4, "Saturday");
    daysOfWeek.put(5, "Sunday");
    daysOfWeek.put(6, "Monday");

    monthToDayCount = new HashMap<>();
    monthToDayCount.put(1, 31); //jan
    monthToDayCount.put(2, 28); //feb
    monthToDayCount.put(3, 31); //march
    monthToDayCount.put(4, 30); //april
    monthToDayCount.put(5, 31); //may
    monthToDayCount.put(6, 30); //june
    monthToDayCount.put(7, 31); //july
    monthToDayCount.put(8, 31); //aug
    monthToDayCount.put(9, 30); //sept
    monthToDayCount.put(10, 31); //oct
    monthToDayCount.put(11, 30); //nov
    monthToDayCount.put(12, 31); //dec
  }

  private static boolean isLeapYear(int year) {
    if (year % 100 == 0 && year % 400 != 0) {
      return false;
    }
    return (year % 4 == 0);
  }
}
