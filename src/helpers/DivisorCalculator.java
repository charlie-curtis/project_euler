package helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DivisorCalculator {

  public static Set<Integer> getDivisors(int n)
  {
    Set<Integer> divisors = new HashSet<>();
    for (int i = 1; i*i <= n; i++) {
      if (n % i == 0) {
        divisors.add(i);
        divisors.add(n/i);
      }
    }
    return divisors;
  }
  public static Set<Integer> getProperDivisors(int n)
  {
    Set<Integer> divisors = new HashSet<>();
    divisors.add(1);
    for (int i = 2; i*i <= n; i++) {
      if (n % i == 0) {
        divisors.add(i);
        divisors.add(n/i);
      }
    }
    return divisors;
  }
}
