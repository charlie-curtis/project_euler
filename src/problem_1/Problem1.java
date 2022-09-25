package problem_1;

import java.util.HashSet;
import java.util.Set;

public class Problem1 {

  public static void main(String [] args) {

    int number = 1000;
    System.out.printf("The sum of the multiples of 3 or 5 that are less than %d is %d%n", number, findMultiples(number));
    System.out.printf("{FAST} The sum of the multiples of 3 or 5 that are less than %d is %d%n", number, findMultiplesFast(number));
  }

  public static int findMultiples(int number) {

    int sum = 0;
    for (int i = 0; i < number; i++) {
      if (i % 3 == 0 || i % 5 == 0) {
        sum += i;
      }
    }
    return sum;
  }

  public static int findMultiplesFast(int number) {
    int i = 3;
    int sum = 0;
    while (i < number) {
      sum+=i;
      i +=3;
    }

    i = 5;
    while (i < number) {
      //don't double count
      sum += (i % 3 == 0) ? 0 : i;
      i +=5;
    }
    return sum;
  }
}
