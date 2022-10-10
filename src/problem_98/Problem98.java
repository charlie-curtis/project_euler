package problem_98;

import helpers.FileParser;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem98 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  static HashMap<Integer, Set<Long>> anagramSquares = new HashMap<>();
  static List<List<String>> validWordAnagrams = new ArrayList<>();

  public static long compute() {

    FileParser parser = new FileParser("src/problem_98/words.txt");
    List<String> lines = Arrays.asList(parser.fromCsv());

    groupAnagrams(lines);
    computeAnagramSquares();
    return computeAnswer();
  }

  private static long computeAnswer()
  {
    long max = 0;
    for (int i = 0; i < validWordAnagrams.size(); i++) {
      List<String> wordsToCheck = validWordAnagrams.get(i);
      long potential = checkMappings(wordsToCheck);
      if (max < potential) {
        max = potential;
        System.out.printf("updating the GLOBAL max to %d%n", potential);
      }
    }
    return max;
  }

  private static int getMaxLength()
  {
    return validWordAnagrams
      .stream()
      .map(list -> list.get(0))
      .map(String::length)
      .reduce(0, Math::max);
  }

  private static void groupAnagrams(List<String> words)
  {
    Map<Integer, List<String>> hashCodeToAnagramListMap = new HashMap<>();
    words.forEach(
      word -> {
       int hashCode = Arrays.stream(word.split("")).sorted().collect(Collectors.joining()).hashCode();
       List<String> list = hashCodeToAnagramListMap.getOrDefault(hashCode, new ArrayList<>());
       list.add(word);
       hashCodeToAnagramListMap.put(hashCode, list);
      }
    );

    validWordAnagrams = hashCodeToAnagramListMap
      .values()
      .stream()
      .filter(list -> list.size() != 1)
      .toList();
  }

  private static long checkMappings(List<String> wordsToCheck)
  {
    Set<Long> numbersToCheck = anagramSquares.get(wordsToCheck.get(0).length());
    if (numbersToCheck == null) {
      return 0;
    }
    long max = 0;
    for (int i = 0; i < wordsToCheck.size(); i++) {
      for (int j = i+1; j < wordsToCheck.size(); j++) {
        Iterator<Long> itr = numbersToCheck.iterator();
        while (itr.hasNext()) {
          Long numberToCheck = itr.next();
          long potential = checkMapping(wordsToCheck.get(i), wordsToCheck.get(j), numberToCheck, numbersToCheck);
          if (max < potential) {
            System.out.printf("Updated max from %d to %d%n", max, potential);
            max = potential;
          }
        }
      }
    }
    return max;
  }

  private static boolean isPalindrome(String w1, String w2)
  {
    for (int i = 0; i < w1.length(); i++) {
      char a = w1.charAt(i);
      char b = w2.charAt(w2.length() - i - 1);

      if (a != b) {
        return false;
      }
    }
    return true;
  }

  private static long checkMapping(String w1, String w2, Long number, Set<Long> availableNumbers)
  {
    if (w1.length() != w2.length()) return 0;
    if (getNumberOfDigits(number) != w1.length()) return 0;
    if (isPalindrome(w1, w2)) return 0;

    Long originalNumber = number;
    HashMap<Character, Integer> charToIntMap = new HashMap<>();
    HashMap<Integer, Character> intToCharMap = new HashMap<>();

    for (int i = w1.length()-1; i>=0; i--) {
      int lastDigit = (int) (number % 10);
      //cannot remap
      if (charToIntMap.containsKey(w1.charAt(i)) && charToIntMap.get(w1.charAt(i)) != lastDigit) {
        return 0;
      } else if (intToCharMap.containsKey(lastDigit) && intToCharMap.get(lastDigit) != w1.charAt(i)) {
        return 0;
      }
      charToIntMap.put(w1.charAt(i), lastDigit);
      intToCharMap.put(lastDigit, w1.charAt(i));
      number /= 10;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < w2.length(); i++) {
      Character c = w2.charAt(i);
      if (!charToIntMap.containsKey(c)) {
        return 0;
      }
      sb.append(charToIntMap.get(c));
    }

    String s = sb.toString();
    if (s.charAt(0) == '0')  {
      return 0;
    }

    Long lookingFor = Long.parseLong(s);
    if (availableNumbers.contains(lookingFor)) {
      System.out.printf("Found Valid Pair: %s and %s with numbers %d and %d %n", w1, w2, originalNumber, lookingFor);
      return Math.max(originalNumber, lookingFor);
    }

    return 0;
  }

  private static int getNumberOfDigits(Long number)
  {
    int count = 0;
    while (number != 0) {
      count++;
      number /= 10;
    }
    return count;
  }

  private static void computeAnagramSquares()
  {
    HashMap<String, Integer> seenValues = new HashMap<>();

    //hardcode max length to 8 for now
    int maxLength = getMaxLength();
    maxLength = 8;
    long cutoff = BigInteger.valueOf(10).pow(maxLength).longValue();

    long i = 1;
    while (i*i < cutoff) {
      Long number = i*i;
      String key = getKey(number);
      int count = seenValues.getOrDefault(key, 0);
      count++;
      seenValues.put(key, count);
      int numberOfDigits = getNumberOfDigits(number);
      Set<Long> squaresWithSameNumberOfDigits = anagramSquares.getOrDefault(numberOfDigits, new HashSet<>());
      squaresWithSameNumberOfDigits.add(number);
      anagramSquares.put(numberOfDigits, squaresWithSameNumberOfDigits);
      i++;
    }

    //loop back through and remove any that don't have a corresponding anagram
    for (int j = 0; j < anagramSquares.size(); j++) {
      Set<Long> set = anagramSquares.get(j+1);
      Set<Long> filteredSet = new HashSet<>();
      for (Long k : set) {
        String key = getKey(k);
        if (seenValues.get(key) > 1) {
          filteredSet.add(k);
        }
      }
      anagramSquares.put(j+1, filteredSet);
    }
  }

  private static String getKey(Long number)
  {
    return Stream.of(String.valueOf(number).split("")).sorted().collect(Collectors.joining());
  }
}
