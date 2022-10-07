package problem_1;

public class Problem1 {

  public static void main(String[] args) {

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
    int sum = 0;
    for (int value = 3, k = 1; value < number; k++, value = 3 * k) {
      sum += value;
    }

    for (int value = 5, k = 1; value < number; k++, value = 5 * k) {
      //don't double count
      sum += (value % 3 == 0) ? 0 : value;
    }
    return sum;
  }
}
