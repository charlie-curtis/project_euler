package problem_33;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem33 {

  public static void main(String[] args) {

    System.out.printf("The answer is %.0f\n", compute());
  }

  public static double compute() {

    for (int i = 10; i <= 99; i++) {
      for (int j = i+1; j<=99; j++) {
        if (isCuriousFunction(i, j)) {
          reduceAndStore(i,j);
        }
      }
    }
    double num = answers.stream().map(Map.Entry::getKey).reduce(1, (a, b) -> a*b);
    double denom = answers.stream().map(Map.Entry::getValue).reduce(1, (a, b) -> a*b);
    return denom/ num;
  }

  private static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a%b);
  }

  private static Set<AbstractMap.SimpleEntry<Integer, Integer>> answers = new HashSet<>();
  private static void reduceAndStore(int i, int j)
  {
      int gcd = gcd(i,j);
      int num = i / gcd;
      int denom = j / gcd;
      System.out.printf("Reduced %d/%d to %d/%d%n", i,j, num, denom);
      AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(num,denom);
      answers.add(entry);
  }

  private static boolean isCuriousFunction(int num, int denom)
  {
    if (num % 10 == 0 && denom % 10 == 0) {
      //trivial example
      return false;
    }

    double lookingFor = (double)num/denom;
    double numMsb = (num/10) % 10;
    double numLsb = num % 10;
    double denMsb = (denom/10) % 10;
    double denLsb = (denom) % 10;
    double[] numDigits = { numMsb, numLsb };
    double[] denomDigits = { denMsb, denLsb};
    for (int i = 0; i<2; i++) {
      for (int j = 0; j < 2; j++) {
        //this just grabs the two other digits we aren't considering. If they aren't equal,
        //then this can't be a curious number
        double otherNumDigit = numDigits[(i+1) %2];
        double otherDenDigit = denomDigits[(j+1) %2];
        if (otherNumDigit != otherDenDigit) {
          continue;
        }
        if (numDigits[i] / denomDigits[j] == lookingFor) {
          return true;
        }
      }
    }
    return false;
  }
}
