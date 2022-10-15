package problem_90;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem90 {

  private static HashMap<String, Set<Integer>> uniqueSets = new HashMap<>();

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    Integer[] selectionsSoFar = new Integer[6];
    populateMap(9, 0, selectionsSoFar);
    Collection<Set<Integer>> results = uniqueSets.values();

    int answer = 0;
    for (Set<Integer> i : results) {
      for (Set<Integer> j : results) {
        if (doesSatisfy(i, j)) {
          answer++;
        }
      }
    }
    return answer;
  }

  private static Set<String> seen = new HashSet<>();
  private static boolean doesSatisfy(Set<Integer> ar1, Set<Integer> ar2)
  {
    String key1 = getKey(ar1.toArray(Integer[]::new));
    String key2 = getKey(ar2.toArray(Integer[]::new));
    String combined = key1.compareTo(key2) > 0 ? key2 + key1 : key1 + key2;
    if (seen.contains(combined)) {
      return false;
    }
    seen.add(combined);
    boolean has1 = (ar1.contains(0) && ar2.contains(1) || ar1.contains(1) && ar2.contains(0));
    boolean has4 = (ar1.contains(0) && ar2.contains(4) || ar1.contains(4) && ar2.contains(0));
    boolean has9 = ((ar1.contains(0) && (ar2.contains(6) || ar2.contains(9))) || (ar2.contains(0) && (ar1.contains(6) || ar1.contains(9))));
    boolean has16 = ((ar1.contains(1) && (ar2.contains(6) || ar2.contains(9))) || (ar2.contains(1) && (ar1.contains(6) || ar1.contains(9))));
    boolean has25 = (ar1.contains(2) && ar2.contains(5) || ar1.contains(5) && ar2.contains(2));
    boolean has36 = ((ar1.contains(3) && (ar2.contains(6) || ar2.contains(9))) || (ar2.contains(3) && (ar1.contains(6) || ar1.contains(9))));
    boolean has49 = ((ar1.contains(4) && (ar2.contains(6) || ar2.contains(9))) || (ar2.contains(4) && (ar1.contains(6) || ar1.contains(9))));
    boolean has64 = ((ar1.contains(4) && (ar2.contains(6) || ar2.contains(9))) || (ar2.contains(4) && (ar1.contains(6) || ar1.contains(9))));
    boolean has81 = (ar1.contains(8) && ar2.contains(1) || ar1.contains(1) && ar2.contains(8));

    return (has1 && has4 && has9 && has16 && has25 && has36 && has49 && has64 && has81);
  }

  private static void populateMap(int currentNumber, int numbersChosen, Integer[] selectionSoFar)
  {
    if (numbersChosen == 6) {
      String s = getKey(selectionSoFar);
      uniqueSets.put(s, Set.of(selectionSoFar));
    }
    if (currentNumber == -1) {
      return;
    }
    //assume we don't choose this number to be in the set
    populateMap(currentNumber -1, numbersChosen, selectionSoFar);

    if (numbersChosen < 6) {
      //assume we do choose this number to be in the set
      Integer[] copiedArray = copy(selectionSoFar);
      copiedArray[numbersChosen] = currentNumber;
      populateMap(currentNumber - 1, ++numbersChosen, copiedArray);
    }

  }

  private static Integer[] copy(Integer[] selectionSoFar)
  {
    Integer[] copiedArray = new Integer[selectionSoFar.length];
    for (int i = 0; i < selectionSoFar.length; i++) {
      copiedArray[i] = selectionSoFar[i];
    }
    return copiedArray;
  }

  private static String getKey(Integer[] selectionSoFar)
  {
    String sortedString = Arrays.stream(selectionSoFar).sorted().map(String::valueOf).collect(Collectors.joining());
    return sortedString;
  }
}
