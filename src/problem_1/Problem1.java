package problem_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Problem1 {

  public static void main(String [] args) {

    int number = 1000;
    System.out.printf("\nThe sum of the multiples of 3 or 5 that are less than %d is %d\n", number, findMultiples(number));
    System.out.printf("\n{HASHSET} The sum of the multiples of 3 or 5 that are less than %d is %d\n", number, findMultiplesUsingSet(number));
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

  public static int findMultiplesUsingSet(int number) {
    Set<Integer> set = new HashSet<>();

    int i = 3;
    while (i < number) {
      set.add(i);
      i +=3;
    }

    i = 5;
    while (i < number) {
      set.add(i);
      i +=5;
    }
    return set.stream().reduce(0, (a,b) -> a+b);
  }
}
