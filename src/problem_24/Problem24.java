package problem_24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem24 {

  public static void main(String[] args) {

    System.out.printf("Answer is %s%n", compute());
  }

  public static String compute() {

    String s = "0123456789";

    //tried 2785960341. it was wrong
    int cutoff = 1_000_000;
    permutations = new ArrayList<>();
    printRecursive(s, 0, s.length()-1);
    permutations.sort(Comparator.naturalOrder());

    return permutations.get(cutoff -1);
  }

  private static ArrayList<String> permutations;
  public static void printRecursive(String s, int startIndex, int endIndex)
  {
    if (startIndex == endIndex) {
      permutations.add(s);
      //System.out.printf("Answer is %s%n", s);
      return;
    }

    // 0 1 2
      //
    // 1 0 2
      //
    // 2 1 0
      //
    for (int i = startIndex; i <= endIndex; i++) {
      s = swap(s, startIndex, i);
      //System.out.printf("Calling recursive with i = %d and s = %s%n", startIndex+1, s);
      printRecursive(s, startIndex+1, endIndex);
      s = swap(s, startIndex, i);

    }
  }

  private static String swap(String s, int i, int j)
  {
    char[] values = s.toCharArray();
    values[i] = s.charAt(j);
    values[j] = s.charAt(i);
    return String.valueOf(values);
  }
}
