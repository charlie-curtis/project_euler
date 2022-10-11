package problem_49;

import helpers.PrimeCalculator;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem49 {

  public static void main(String[] args) {

    System.out.printf("Computing... %n%n");
    compute();
    System.out.println("Done!");
  }

  private static PrimeCalculator calculator;
  public static long compute() {
    calculator = new PrimeCalculator(10_000);
    HashSet<String> answers = new HashSet<>();
    for (int i = 1000; i <10_000; i++) {
      if (!calculator.isPrime(i)) {
        continue;
      }
      int[] digits = {-1, -1, -1, -1};
      Set<Integer> result = countPrimePermutations(i, 0, digits);
      if (result.size() >= 3) {
       String answer = checkResult(result);
       if (answer != null) {
         answers.add(answer);
       }
      }
    }
    answers.forEach(System.out::println);
    return 0;
  }

  private static String checkResult(Set<Integer> result)
  {
    List<Integer> list = result.stream().sorted().toList();
    for (int i = 0; i < list.size(); i++) {
      for (int j = i+1; j < list.size(); j++) {
        for (int k = j+1; k < list.size(); k++) {
          int num1 = list.get(i);
          int num2 = list.get(j);
          int num3 = list.get(k);
          if (num3 - num2 == num2 - num1)
          {
            return "" + num1 + num2+ num3;
          }
        }
      }
    }
    return null;
  }

  private static int arrayToInt(int[] digits) {
    String result = "";
    for (int i = 0; i < digits.length; i++) {
      result += digits[i];
    }

    return Integer.parseInt(result);

  }
  private static Set<Integer> countPrimePermutations(int n, int startingIndex, int[] digits)
  {
    if (startingIndex == digits.length) {
      int val = arrayToInt(digits);
      if (!calculator.isPrime(val) || String.valueOf(n).length() != String.valueOf(val).length()) {
        return new HashSet<>();
      }
      return Set.of(val);
    }
    int value = Integer.parseInt(String.valueOf(n).substring(startingIndex, startingIndex+1));

    HashSet<Integer> perms = new HashSet<>();
    for (int i = 0; i < digits.length; i++) {
      int[] newDigits = copy(digits);
      if (digits[i] == -1) {
        newDigits[i] = value;
        perms.addAll(countPrimePermutations(n, startingIndex+1, newDigits));
      }
    }
    return perms;
  }

  private static int[] copy(int[]original)
  {
    int[] newArray = new int[original.length];
    for (int i = 0; i < original.length; i++) {
      newArray[i] = original[i];
    }
    return newArray;
  }
}
