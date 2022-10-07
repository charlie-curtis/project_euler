package problem_31;

public class Problem31 {

  private static final int CUTOFF = 200;
  private static final int[] denominations = {1, 2, 5, 10, 20, 50, 100, 200};
  private static final ValueHolder[][] memo = new ValueHolder[CUTOFF + 1][denominations.length];

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    return findCombos(CUTOFF, 0);
  }

  private static long findCombos(int amt, int idx) {

    if (amt == 0) {
      return 1;
    }

    if (idx == denominations.length) {
      return 0;
    }

    if (memo[amt][idx] != null) {
      return memo[amt][idx].value;
    }

    int answer = 0;
    //scenario 1 - we don't use this coin
    answer += findCombos(amt, idx + 1);

    //scenario 2 - we use this coin 1 or more times
    int tempAmt = amt;
    while (amt - denominations[idx] >= 0) {
      answer += findCombos(amt - denominations[idx], idx + 1);
      amt -= denominations[idx];
    }

    memo[tempAmt][idx] = new ValueHolder(answer);
    return answer;
  }

  private static class ValueHolder {
    int value;

    ValueHolder(int i) {
      value = i;
    }
  }
}
