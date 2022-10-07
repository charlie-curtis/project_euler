package problem_78;

import helpers.PrimeCalculator;

import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Problem78 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  //private static int CUTOFF = 20;
  private static int CUTOFF = 60_000;

  private static int LOOKING_FOR = 1_000_000;
  //private static int LOOKING_FOR = 176;

  public static long compute() {

    HashMap<AbstractMap.SimpleEntry<Integer, Integer>, BigInteger> map = bottomUpMap(CUTOFF);

    int answer = 0;
    BigInteger modValue = BigInteger.valueOf(LOOKING_FOR);
    BigInteger zero = BigInteger.ZERO;

    for (int i = 1; i <= CUTOFF; i++) {
      AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(i, i);
      if (map.get(entry).mod(modValue).equals(zero)) {
       answer = i;
       break;
      }
    }
    return answer;
  }

  private static HashMap<AbstractMap.SimpleEntry<Integer, Integer>, BigInteger> bottomUpMap(int n)
  {
    //row is n
    //col is valueToConsider
    HashMap<AbstractMap.SimpleEntry<Integer, Integer>, BigInteger> dp = new HashMap<>();

    for (int i = 0; i <= n; i++) {
      dp.put(new AbstractMap.SimpleEntry<>(i, 0), BigInteger.ZERO);
    }

    for (int j = 0; j<=n; j++) {
      AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(0, j);
      dp.put(entry, BigInteger.ONE);
    }


    for (int i = 1; i <= n; i++) {
      if (i % 10 == 0) {
        System.out.printf("Computing for i = %d. That is %.4f percent%n", i, (double)i*100/CUTOFF);
      }
      for (int j = 1; j<=n; j++) {

        //This will be the sum if we don't use this value at
        //all in our answer. So for example, if value = 3, and n = 10,
        //this will count all the scenarios where we add to 10 without
        //using a 3 (i.e. 5+2+1+1+1 is one such example)
        BigInteger sum = dp.get(new AbstractMap.SimpleEntry<>(i, j-1));

        //if we use value once, it looks like
        //dp[n-i][j-1]
        //if we use it twice, it looks like dp[n-2*i][j-1]
        for (int k = 1; k*j <= i; k++) {
          AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(i -k*j, j-1);
          sum = sum.add(dp.get(entry));
        }

        dp.put(new AbstractMap.SimpleEntry<>(i,j), sum);
      }
    }
    return dp;
  }
}
