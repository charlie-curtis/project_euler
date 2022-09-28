package problem_24;

import java.util.ArrayList;
import java.util.List;

public class Problem24 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {

    List<Character> myList = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    String s = "012";

    for (int i = 0; i < myList.size(); i++) {
      for (int j = i+1; j < myList.size(); j++) {
        for (int k = j+1; k < myList.size(); k++) {
          for (int l = k+1; l < myList.size(); l++) {
            for (int m = l+1; m < myList.size(); m++) {
              for (int n = m+1; n < myList.size(); n++) {
                for (int o = n+1; o < myList.size(); o++) {
                  for (int p = o+1; p < myList.size(); p++) {
                    for (int q = p+1; q < myList.size(); q++) {
                      for (int r = q + 1; r < myList.size(); r++) {
                        System.out.printf("%s%s%s%s%s%s%s%s%s%s%n",
                          myList.get(i),
                          myList.get(j),
                          myList.get(k),
                          myList.get(l),
                          myList.get(m),
                          myList.get(n),
                          myList.get(o),
                          myList.get(p),
                          myList.get(q),
                          myList.get(r)
                        );
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

    return 0;
  }

  public static String printRecursive(String s)
  {
    if (s.length() == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      if (i != 0) {
        sb.append(printRecursive(s.substring(0, i)));
      }
      sb.append(s.substring(i, i+1));
      if (i+1 != s.length()) {
        sb.append(printRecursive(s.substring(i+1, s.length())));
      }
    }
    return sb.toString();
  }
}
