package problem_41;

import java.util.Arrays;

public class Problem41 {

  private static int count = 0;
  private static final int CUTOFF = 987654321;
  private static long startTime;
  private static final boolean[] primes = new boolean[CUTOFF + 1];

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    //we want to start with 987654321, go through all the permutations, and test if it is prime. If it isn't
    //then we drop the 1, and do the same thing
    //permute("9876543210", 0);
    startTime = System.currentTimeMillis();
    initalizePrimes();
    permute(String.valueOf(CUTOFF), 0);
    exitWithMessage(String.format("No prime found for any permutation of %d. Adjust your cutoff%n", CUTOFF));
    return count;
  }

  //private static int CUTOFF = 987_654_321;
  private static void permute(String s, int startIndex) {
    if (startIndex == s.length()) {
      if (count % 1000 == 0) {
        System.out.printf("Progress: %d%n", count);
      }
      if (isPrime(s)) {
        exitWithMessage(String.format("%s is PRIME. We're done!", s));
      }
      count++;
      return;
    }
    for (int i = startIndex; i < s.length(); i++) {
      String s1 = moveCharacterToLocation(s, i, startIndex);
      permute(s1, startIndex + 1);
    }
  }

  private static void exitWithMessage(String msg) {
    System.out.println(msg);
    System.out.printf("RUNTIME: %d seconds", (System.currentTimeMillis() - startTime) / 1000);
    System.exit(0);
  }

  private static String moveCharacterToLocation(String s, int currentLocation, int desiredLocation) {
    // 1 2 3 4 5 6
    StringBuffer sb = new StringBuffer();
    char[] charArray = s.toCharArray();
    char characterToMove = s.charAt(currentLocation);
    for (int i = currentLocation; i > desiredLocation; i--) {
      charArray[i] = charArray[i - 1];
    }
    charArray[desiredLocation] = characterToMove;
    sb.append(charArray);
    return sb.toString();
  }

  private static void initalizePrimes() {
    System.out.println("computing primes");
    Arrays.fill(primes, true);
    primes[1] = false;

    for (int i = 2; i <= CUTOFF; i++) {
      int k = 2;
      while ((long) i * k <= CUTOFF) {
        primes[i * k] = false;
        k++;
      }
      if (i % 1_000 == 0) {
        System.out.printf("Current # %d%n", i);
      }
    }
    System.out.println("done computing primes");
  }

  private static boolean isPrime(String s) {
    return primes[Integer.parseInt(s)];
  }


}
