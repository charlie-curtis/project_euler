package problem_62;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem62 {

  public static void main(String[] args) {

    System.out.printf("The answer is %s\n", compute());
  }

  private static Map<String, List<Integer>> map = new HashMap<>();
  public static String compute() {
    int cutoff = 100_000;
    for (int i = 345; i < cutoff; i++) {
      BigInteger val = BigInteger.valueOf(i).pow(3);
      String s = Arrays.stream(val.toString().split("")).sorted().collect(Collectors.joining());
      List<Integer> list = map.getOrDefault(s, new ArrayList<>());
      list.add(i);
      if (list.size() == 5) {
        return BigInteger.valueOf(list.get(0)).pow(3).toString();
      }
      map.put(s, list);
    }
    return "-1";
  }
}
