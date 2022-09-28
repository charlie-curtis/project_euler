package problem_24;

import java.util.ArrayList;
import java.util.Comparator;

public class Problem24 {

  private static int CUTOFF = 1_000_000;
  public static void main(String[] args) {

    System.out.printf("Answer is %s%n", compute());
  }

  /**
   * I solved the problem 2 ways.
   * The first way is printing the characters in lexicographical order. This was tricky because
   * it requires special attention to how characters are being swapped.
   * The second way is just permutating the characters, adding them to a set, then returning
   * the 1 millionth entry in the set.
   * @return
   */
  public static String compute() {

    String s = "0123456789";

    //tried 2785960341. it was wrong
    permutations = new ArrayList<>();
    printRecursive(s, 0, s.length()-1);
    permutations.sort(Comparator.naturalOrder());

    //We can either do this using a set, or print the 1M value
    return permutations.get(CUTOFF-1);
  }

  private static ArrayList<String> permutations;
  private static int count = 0;
  public static void printRecursive(String s, int startIndex, int endIndex)
  {
    if (startIndex == endIndex) {
      //no more characters left to permutate
      count++;
      //System.out.println(s);
      if (count == CUTOFF) {
        System.out.printf("The 1M value is %s%n", s);
      }
      permutations.add(s);
      return;
    }

    String original = s;
    for (int i = startIndex; i <= endIndex; i++) {
      s = insertCharacterForward(s, i, startIndex);
      printRecursive(s, startIndex+1, endIndex);
      s = original;
    }
  }

  private static String insertCharacterForward(String s, int currentLocation, int desiredLocation)
  {
    // 0 1 2 3 4 5 6 7
    char[] values = s.toCharArray();
    char charToMove = values[currentLocation];
    for (int i = currentLocation; i > desiredLocation; i--) {
      values[i] = values[i-1];
    }
    values[desiredLocation] = charToMove;
    return String.valueOf(values);
  }
}
