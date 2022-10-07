package problem_2;

public class Problem2 {

  public static final int CUTOFF_VALUE = 4_000_000;

  public static void main(String[] args) {

    System.out.printf("\nThe sum of the even terms in fib less than %d is %d\n", CUTOFF_VALUE, evenTermFib());
  }

  public static int evenTermFib() {

    int previousTerm = 0;
    int currentTerm = 1;

    int evenSum = 0;
    while (currentTerm < CUTOFF_VALUE) {
      evenSum += (currentTerm % 2 == 0) ? currentTerm : 0;

      int temp = currentTerm;
      currentTerm += previousTerm;
      previousTerm = temp;
    }

    return evenSum;
  }
}
