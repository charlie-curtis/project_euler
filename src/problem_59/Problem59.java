package problem_59;

import helpers.FileParser;

import java.util.ArrayList;
import java.util.List;

public class Problem59 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
    char[] keyToTry = {'e', 'z', 'p'};
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_59/cipher.txt");

    char[] charsToTry = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't'+
      'u', 'v', 'w', 'x', 'y', 'z'};
    char[] decryptMe = parser.toCharArray();
    List<List<Character>> topKeys = new ArrayList<>();
    char[] potentialKey = new char[3];
    int max = 0;
    for (int i = 0; i < charsToTry.length; i++) {
      for (int j = 0; j < charsToTry.length; j++) {
        for (int k = 0; k < charsToTry.length; k++) {
          char a = charsToTry[i];
          char b = charsToTry[j];
          char c = charsToTry[k];
          int result = tryKey(a, b, c, decryptMe, false);
          if (result > max) {
            max = result;
            System.out.printf("found new max of %d for %c%c%c%n", max, a,b,c);
            List<Character> keyToTry = List.of(a,b,c);
            topKeys.add(keyToTry);
          }
        }
      }
    }
    System.out.println("Now going to use the top keys to see if any of them make sense");
    printUsingKey(topKeys, decryptMe);
    return 0;
  }


  private static void printUsingKey(List<List<Character>> topKeys, char[] decryptMe)
  {
    for (int i = 0; i < topKeys.size(); i++)  {
      List<Character> key = topKeys.get(i);
      System.out.printf("now trying %c %c %c%n", key.get(0), key.get(1), key.get(2));
      tryKey(key.get(0), key.get(1), key.get(2), decryptMe, true);
      System.out.println();
    }
  }

  private static int tryKey(char a, char b, char c, char[] decryptMe, boolean shouldPrint)
  {
    int count = 0;
    int sum = 0;
    char[] key = {a,b,c};
    for (int i = 0; i < decryptMe.length; i++)  {
      char x = decryptMe[i];
      x = (char)(x ^ key[i%3]);
      if ((x >= 65 && x <= 90) || (x >= 97 && x<=122)) {
        count++;
      }
      if (shouldPrint) {
        sum+= x;
        System.out.print(x);
      }
    }
    if (shouldPrint) {
      System.out.printf("%nThe sum was %d%n", sum);
    }
    return count;
  }
}
