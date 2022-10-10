package problem_98;

import helpers.FileParser;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem98 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  static List<Long> anagramSquares = new ArrayList<>();
  static List<List<String>> validWordAnagrams = new ArrayList<>();

  public static long compute() {

    FileParser parser = new FileParser("src/problem_98/words.txt");
    List<String> lines = Arrays.asList(parser.fromCsv());

    Map<Integer, List<String>> groupedAnagrams = groupAnagrams(lines);
    int maxLength = getMaxLength(groupedAnagrams);
    computeAnagramSquares(maxLength);
    return computeAnswer();
  }

  private static long computeAnswer()
  {
    long max = 0;
    for (int i = 0; i < validWordAnagrams.size(); i++) {
      List<String> wordsToCheck = validWordAnagrams.get(i);
      max = Math.max(checkMappings(wordsToCheck), max);

    }
    return max;
  }

  private static int getMaxLength(Map<Integer, List<String>> groupedAnagrams)
  {
    return groupedAnagrams
      .values()
      .stream()
      .map(list -> list.get(0))
      .map(String::length)
      .reduce(0, Math::max);
  }

  private static Map<Integer, List<String>> groupAnagrams(List<String> words)
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
    return hashCodeToAnagramListMap;
  }

  private static long checkMappings(List<String> wordsToCheck)
  {
    long max = 0;
    for (int i = 0; i < wordsToCheck.size(); i++) {
      for (int j = i+1; j < wordsToCheck.size(); j++) {
        for (int k = 0; k < anagramSquares.size(); k++) {
          max = Math.max(max, checkMapping(wordsToCheck.get(i), wordsToCheck.get(j), anagramSquares.get(k)));
        }
      }
    }
    return max;
  }

  private static long checkMapping(String w1, String w2, Long number)
  {
    if (w1.length() != w2.length()) {
      return 0;
    }
    boolean isPalindrome = true;
    for (int i = 0; i < w1.length(); i++) {
      char a = w1.charAt(i);
      char b = w2.charAt(w2.length()-i-1);

      if (a != b) {
        isPalindrome = false;
      }
    }
    if (isPalindrome) {
      return 0;
    }
    HashMap<Character, Integer> mapping = new HashMap<>();
    for (int i = w1.length()-1; i>=0; i--) {
      if (number == 0) {
        return 0;
      }
      int lastDigit = (int) (number % 10);
      if (mapping.containsKey(w1.charAt(i)) && mapping.get(w1.charAt(i)) != lastDigit) {
        return 0;
      }
      mapping.put(w1.charAt(i), lastDigit);
      number /= 10;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < w2.length(); i++) {
      Character c = w2.charAt(i);
      if (!mapping.containsKey(c)) {
        return 0;
      }
      sb.append(mapping.get(i));
    }

    String s = sb.toString();
    if (s.charAt(0) == '0')  {
      return 0;
    }

    Long lookingFor = Long.parseLong(s);
    if (anagramSquares.contains(lookingFor)) {
      int index = anagramSquares.indexOf(lookingFor);
      return Math.max(number, anagramSquares.get(index));
    }

    return 0;
  }

  private static void computeAnagramSquares(int maxLength)
  {
    HashMap<String, Integer> seenValues = new HashMap<>();

    long cutoff = BigInteger.valueOf(10).pow(maxLength).longValue();

    long i = 1;
    while (i*i <= cutoff) {
      Long number = i*i;
      String key = getKey(number);
      int count = seenValues.getOrDefault(key, 0);
      count++;
      seenValues.put(key, count);
      anagramSquares.add(number);
      i++;
    }

    //loop back through and remove any that don't have a corresponding anagram
    anagramSquares = anagramSquares.stream().filter(number -> {
      String key = getKey(number);
      int numberOfTimesSeen = seenValues.get(key);
      return numberOfTimesSeen > 1;
    }).collect(Collectors.toList());
  }

  private static String getKey(Long number)
  {
    return Stream.of(String.valueOf(number).split("")).sorted().collect(Collectors.joining());
  }
}
