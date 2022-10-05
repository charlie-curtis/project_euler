package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileParser {

  String fileName;
  public FileParser(String fileName)
  {
    this.fileName = fileName;
  }


  private BufferedReader tryOpen()
  {
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

  public String[] toStringArray() throws Exception
  {

    BufferedReader br = tryOpen();
    return br.readLine().replaceAll("\"","").split(",");
  }

  public int[][] to2DIntArray()
  {
    int[][] result = null;
    try {
      String filePath = new File(this.fileName).getAbsolutePath();
      BufferedReader br= new BufferedReader(
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
