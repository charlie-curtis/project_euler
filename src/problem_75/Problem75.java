package problem_75;

public class Problem75 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    int cutoff = 12_000;
    int answer = 0;
    for (int L = 12; L <= cutoff; L++) {
      if (L % 100 == 0) {
        System.out.println(L);
      }
      int count = getRightTriangles(L);
      if (count == 1) {
        answer++;
      }
    }
    return answer;
  }

  private static int getRightTriangles(int L)
  {
    int count = 0;
    for (int i = 1; i+2<=L; i++) {
      for (int j = i; i+j+1<=L; j++) {
        int k = L - i - j;
        if (k < j) {
          //this makes sure we don't double count
          continue;
        }
        if (i*i + j*j == k*k || j*j + k*k == i*i || i*i + k*k == j*j) {
          count++;
          if (count > 1) {
            return count;
          }
        }
      }
    }
    return count;
  }
}
