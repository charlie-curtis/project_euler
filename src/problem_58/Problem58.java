package problem_58;

import helpers.PrimeCalculator;

public class Problem58 {

  public static void main(String[] args) {

    compute();
  }

  public static long compute() {
    printStuffIteratively();
    return 0;
  }

  private static int numRows = 28001;
  private static void printStuffIteratively()
  {
    int length = numRows;
    int number = length*length;
    int[][] holder = new int[length][length];
    int iterations = 0;
    while (number > 0) {

      //RIGHT TO LEFT
      for (int i = length - 1; i >= iterations; i--) {
        holder[length - 1][i] = number--;
      }
      number++;
      //BOTTOM TO TOP
      for (int i = length - 1; i >= iterations; i--) {
        holder[i][iterations] = number--;
      }
      number++;
      //LEFT TO RIGHT
      for (int i = iterations; i < length; i++) {
        holder[iterations][i] = number--;
      }
      number++;
      // TOP TO BOTTOM
      for (int i = iterations; i < length- 1; i++) {
        holder[i][length- 1] = number--;
      }
      length--;
      iterations++;
    }


    /*
    for (int i = 0; i < holder.length; i++) {
      for (int j = 0; j < holder.length; j++) {
        System.out.printf("%s ", getNumberToPrint(holder[i][j]));
      }
      System.out.println();
    }
     */
    analyzeDiagonals(holder);
  }

  private static PrimeCalculator calc = PrimeCalculator.quickInitialize(numRows*numRows);
  private static void analyzeDiagonals(int[][] holder)
  {
    double numPrimes = 0;
    double totalNums = 1;
    int middle = holder.length / 2;
    int iteration = 1;
    while (true) {
      int num1 = holder[middle-iteration][middle-iteration];
      int num2 = holder[middle-iteration][middle+iteration];
      int num3 = holder[middle+iteration][middle+iteration];
      int num4 = holder[middle+iteration][middle-iteration];
      //System.out.printf("checking %d %d %d %d%n", num1, num2, num3, num4);
      numPrimes += calc.isPrime(num1) ? 1 : 0;
      numPrimes += calc.isPrime(num2) ? 1 : 0;
      numPrimes += calc.isPrime(num3) ? 1 : 0;
      numPrimes += calc.isPrime(num4) ? 1 : 0;
      totalNums+= 4;
      double ratio = numPrimes / totalNums;
      iteration++;
      System.out.printf("ratio is %.4f when sidelength is %d%n", ratio, (iteration*2)-1);
      if (ratio < .1) {
        break;
      }
    }

    int answer = iteration-1;
    answer = answer*2 + 1;
    System.out.printf("Found a value for i = %d, so the side length should be %d%n", iteration, answer);
  }

  private static String getNumberToPrint(int n)
  {
    String s = String.valueOf(n);
    int desiredPadding = 2 - s.length();

    int i = 0;
    StringBuffer sb = new StringBuffer();
    while (i < desiredPadding) {
      sb.append("0");
      i++;
    }
    sb.append(s);
    return sb.toString();
  }
}
