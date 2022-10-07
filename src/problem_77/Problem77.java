package problem_77;

import helpers.PrimeCalculator;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class Problem77 {

  /**
   * Question
   * What is the first value which can be written as the sum of primes in over five thousand different ways?
   */
  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  //interestingly, using a cutoff of 1M causes a stack overflow
  private static int CUTOFF = 750;

  private static int LOOKING_FOR = 5000;
  private static List<Integer> primes;

  //Using SimpleEntry here is messy. You can see what happens when we try to filter. Honestly this
  //is one of the downsides of using java. I want to be able to create a map where the key is a
  //user defined class and the value is a number. I can do that by creating my own private class,
  //but then I need to implement extra boilerplate code (i.e. compareTo, hashCode, equals)
  private static HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Integer> memo = new HashMap<>();
  public static long compute() {
    PrimeCalculator calculator = new PrimeCalculator(CUTOFF);
    primes = calculator.getListOfPrimes().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    getCombinations(CUTOFF, 0);


    //Look over the entries in the memo pad, if the entry is prime, subtract 1 (or else we'd get an off-by-one
    //error), then filter out any values that are less than 5k, then sort by the number ascending, then grab
    //the first value of n and return it.
    Optional<Integer> answer = memo.entrySet().stream()
        .map(entry -> {
      if (calculator.isPrime(entry.getKey().getKey())) {
        entry.setValue(entry.getValue() - 1);
      }
      return entry;
    }).filter((entry) -> entry.getValue() >= LOOKING_FOR)
      .sorted((entryA, entryB) -> entryA.getKey().getKey().compareTo(entryB.getKey().getKey()))
      .map((entry) -> entry.getKey().getKey())
      .findFirst();

    return answer.orElse(-1);
  }

  private static int getCombinations(int n, int index)
  {
    AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(n, index);
    if (memo.containsKey(entry)) {
      return memo.get(entry);
    }
    if (n == 0) {
      return 1;
    }
    if (n < 0 || primes.size() <= index) {
      return 0;
    }

    //this is the case where we don't use this number at all
    int sum = getCombinations(n, index+1);

    int prime = primes.get(index);
    //this handles the case where we use this prime number 1 or more times
    sum+= getCombinations(n - prime, index);
    memo.put(entry, sum);
    return sum;
  }
}
