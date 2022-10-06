package problem_345;

import java.math.BigInteger;
import java.util.HashSet;

public class Problem345 {

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();
    System.out.printf("The answer is %d\n", compute());
    long endTime = System.currentTimeMillis();
    System.out.printf("The elapsed time, in seconds, was %d%n ", (startTime-endTime)/1000);
  }

  public static long compute() {
    int[][] grid = getGrid();

    BigInteger combos = BigInteger.ONE;
    for (int i = 2; i <= 15; i++) {
      combos = combos.multiply(BigInteger.valueOf(i));
    }
    long numbersToCheck = combos.longValue();
    System.out.printf("Beginning to check combos. There are %d combos to check%n", numbersToCheck);

    long count = 0;
    int max = 0;
    for (int a1 = 0; a1 < grid.length; a1++) {
      for (int a2 = 0; a2 < grid.length; a2++) {
        if (a2 == a1) {
          continue;
        }
        for (int a3 = 0; a3 < grid.length; a3++) {
          if (a3 == a2 || a3 == a1) {
            continue;
          }
          for (int a4 = 0; a4 < grid.length; a4++) {
            if (a4 == a3 || a4 == a2 || a4 == a1) {
              continue;
            }
            for (int a5 = 0; a5 < grid.length; a5++) {
              if (a5 == a4 || a5 == a3 || a5 == a2 || a5 == a1) {
                continue;
              }
              for (int a6 = 0; a6 < grid.length; a6++) {
                if (a6 == a5 || a6 == a4 || a6 == a3 || a6 == a2 || a6 == a1) {
                  continue;
                }
                for (int a7 = 0; a7 < grid.length; a7++) {
                  if (a7== a6 || a7 == a5 || a7 == a4 || a7 == a3 || a7 == a2 || a7 == a1) {
                    continue;
                  }
                  for (int a8 = 0; a8 < grid.length; a8++) {
                    if (a8 == a7 || a8== a6 || a8 == a5 || a8 == a4 || a8 == a3 || a8 == a2 || a8 == a1) {
                      continue;
                    }
                    for (int a9 = 0; a9 < grid.length; a9++) {
                      if (a9 == a8 || a9 == a7 || a9== a6 || a9 == a5 || a9 == a4 || a9 == a3 || a9 == a2 || a9 == a1) {
                        continue;
                      }
                      for (int a10 = 0; a10 < grid.length; a10++) {
                        if (a10 == a9 || a10 == a8 || a10 == a7 || a10== a6 || a10 == a5 || a10 == a4 || a10 == a3 || a10 == a2 || a10 == a1) {
                          continue;
                        }
                        for (int a11 = 0; a11 < grid.length; a11++) {
                          if (a11 == a10 || a11 == a9 || a11 == a8 || a11 == a7 || a11== a6 || a11 == a5 || a11 == a4 || a11 == a3 || a11 == a2 || a11 == a1) {
                            continue;
                          }
                          for (int a12 = 0; a12 < grid.length; a12++) {
                            if (a12 == a11 || a12 == a10 || a12 == a9 || a12 == a8 || a12 == a7 || a12== a6 || a12 == a5 || a12 == a4 || a12 == a3 || a12 == a2 || a12 == a1) {
                              continue;
                            }
                            for (int a13 = 0; a13 < grid.length; a13++) {
                              if (a13 == a12 || a13 == a11 || a13 == a10 || a13 == a9 || a13 == a8 || a13 == a7 || a13== a6 || a13 == a5 || a13 == a4 || a13 == a3 || a13 == a2 || a13 == a1) {
                                continue;
                              }
                              for (int a14 = 0; a14 < grid.length; a14++) {
                                if (a14 == a13 || a14 == a12 || a14 == a11 || a14 == a10 || a14 == a9 || a14 == a8 || a14 == a7 || a14== a6 || a14 == a5 || a14 == a4 || a14 == a3 || a14 == a2 || a14 == a1) {
                                  continue;
                                }
                                for (int a15 = 0; a15 < grid.length; a15++) {
                                  if (a15 == a14 || a15 == a13 || a15 == a12 || a15 == a11 || a15 == a10 || a15 == a9 || a15 == a8 || a15 == a7 || a15== a6 || a15 == a5 || a15 == a4 || a15 == a3 || a15 == a2 || a15 == a1) {
                                    continue;
                                  }
                                  count++;

                                  if (count % 100_000_000 == 0) {
                                    System.out.printf("Checked %d of %d (%.4f) percent %n", count, numbersToCheck, (double)count*100/numbersToCheck);
                                  }
                                  int sum =
                                      grid[0][a1] +
                                      grid[1][a2] +
                                      grid[2][a3] +
                                      grid[3][a4] +
                                        grid[4][a5] +
                                        grid[5][a6] +
                                        grid[6][a7] +
                                        grid[7][a8] +
                                        grid[8][a9] +
                                        grid[9][a10] +
                                        grid[10][a11] +
                                        grid[11][a12] +
                                        grid[12][a13] +
                                        grid[13][a14] +
                                        grid[14][a15];
                                  max = Math.max(max, sum);
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return max;
  }

  private static int[][] getGrid()
  {
    String s = "7  53 183 439 863 497 383 563  79 973 287  63 343 169 583\n" +
      "627 343 773 959 943 767 473 103 699 303 957 703 583 639 913\n" +
      "447 283 463  29  23 487 463 993 119 883 327 493 423 159 743\n" +
      "217 623   3 399 853 407 103 983  89 463 290 516 212 462 350\n" +
      "960 376 682 962 300 780 486 502 912 800 250 346 172 812 350\n" +
      "870 456 192 162 593 473 915  45 989 873 823 965 425 329 803\n" +
      "973 965 905 919 133 673 665 235 509 613 673 815 165 992 326\n" +
      "322 148 972 962 286 255 941 541 265 323 925 281 601  95 973\n" +
      "445 721  11 525 473  65 511 164 138 672  18 428 154 448 848\n" +
      "414 456 310 312 798 104 566 520 302 248 694 976 430 392 198\n" +
      "184 829 373 181 631 101 969 613 840 740 778 458 284 760 390\n" +
      "821 461 843 513  17 901 711 993 293 157 274  94 192 156 574\n" +
      "34 124   4 878 450 476 712 914 838 669 875 299 823 329 699\n" +
      "815 559 813 459 522 788 168 586 966 232 308 833 251 631 107\n" +
      "813 883 451 509 615  77 281 613 459 205 380 274 302  35 805";

    String[] rows = s.split("\\n");
    String[][] stringArray = new String[15][15];

    for (int i = 0; i < stringArray.length; i++) {
      String row = rows[i];
      stringArray[i] = row.split("\\s+");
    }

    int[][] returnMe = new int[15][15];
    for (int i = 0; i < stringArray.length; i++) {
      for (int j = 0; j < stringArray.length; j++) {
        returnMe[i][j] = Integer.parseInt(stringArray[i][j]);
      }
    }
    return returnMe;

  }
}
