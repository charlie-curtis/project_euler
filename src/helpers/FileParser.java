package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class FileParser {

  String fileName;

  public FileParser(String fileName) {
    this.fileName = fileName;
  }


  private BufferedReader tryOpen() {
    String filePath = new File(this.fileName).getAbsolutePath();
    BufferedReader br = null;
    try {
      br = new BufferedReader(
        new FileReader(filePath)
      );
    } catch (Exception e) {
      System.out.println("COULDN'T READ INPUT " + e.getMessage());
      System.exit(1);
    }
    return br;
  }

  private String tryReadline(BufferedReader br) {
    try {
      return br.readLine();
    } catch (Exception e) {
      System.out.println("COULDN'T READ INPUT " + e.getMessage());
      System.exit(1);
    }
    return null;
  }

  public char[] toCharArray()
  {
    BufferedReader br = tryOpen();
    String[] s = tryReadline(br).split(",");
    char[] returnValue = new char[s.length];
    for (int i = 0; i < s.length; i++) {
      returnValue[i] = (char)Integer.parseInt(s[i]);

    }
    return returnValue;
  }

  public String[] toStringArray() {

    BufferedReader br = tryOpen();
    ArrayList<String> list = new ArrayList<>();
    String line;
    while ((line = tryReadline(br)) != null) {
      list.add(line);
    }
    return list.toArray(String[]::new);
  }

  public String[] fromCsv() {

    BufferedReader br = tryOpen();
    String line = tryReadline(br);
    return line.replace("\"", "").split(",");
  }

  public int[][] to2DIntArray() {
    int[][] result = null;
    try {
      String filePath = new File(this.fileName).getAbsolutePath();
      BufferedReader br = new BufferedReader(
        new FileReader(filePath)
      );

      String row = null;
      int i = 0;
      while ((row = br.readLine()) != null) {
        String[] ar = row.split(",");
        if (result == null) {
          result = new int[ar.length][ar.length];
        }
        for (int j = 0; j < ar.length; j++) {
          result[i][j] = Integer.parseInt(ar[j]);
        }
        i++;
      }
    } catch (Exception e) {
      System.out.println("COULDN'T READ INPUT " + e.getMessage());
      System.exit(1);
    }
    return result;
  }
}
