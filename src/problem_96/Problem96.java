package problem_96;

import helpers.FileParser;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem96 {

  public static void main(String[] args) {

    System.out.printf("The answer is %d\n", compute());
  }

  public static long compute() {
    FileParser parser = new FileParser("src/problem_96/sudoku.txt");
    String[] lines = parser.toStringArray();
    ArrayList<Integer> results = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      int[][] board = new int[9][9];
      for (int j = 0; j < 10; j++) {
        if (j == 0) {
          //get rid of the "Grid X" text
          continue;
        }
        String line = lines[i*10 + j];
        String[] stringValues = line.split("");
        int[] values = Arrays.stream(stringValues).mapToInt(Integer::parseInt).toArray();
        board[j - 1] = values;
      }
      int answer = getTopLeftCornerIfValid(9, 0, 0, board);
      System.out.printf("Adding %d to the answer%n", answer);
      results.add(answer);
    }
    return results.stream().reduce(0, (a1, a2) -> a1+a2);
  }

  private static boolean isAtEndOfBoard(int i, int j)
  {
    return (i == 9 && j == 0);
  }

  private static boolean wereAllNumbersPlaced(int numbersToPlace)
  {
    return numbersToPlace == 0;
  }

  private static boolean areThereCellsLeftToTryForRow(int i, int j)
  {
    return (j <= 8);
  }

  private static boolean isCellEmpty(int i, int j, int[][] currentBoard)
  {
    return currentBoard[i][j] == 0;
  }

  //i & j run from 0 to 8
  private static int getTopLeftCornerIfValid(int numberToPlace, int i, int j, int[][] currentBoard)
  {
    if (isAtEndOfBoard(i, j)) {
      //if we made it to the end of the board without failing validation, we're good
      return Integer.parseInt("" + currentBoard[0][0] + currentBoard[0][1] + currentBoard[0][2]);
    } else if (wereAllNumbersPlaced(numberToPlace)) {
      //If all numbers were placed successfully on this row, we can continue
      return getTopLeftCornerIfValid(9, i + 1, 0, currentBoard);
    } else if (!areThereCellsLeftToTryForRow(i, j)) {
      //we weren't able to find a valid spot to place a number with this setup
      return 0;
    } else if (doesRowHaveValue(numberToPlace, i, j, currentBoard)) {
      //this row already has the value -- no need to place it again, so go to the next one
      return getTopLeftCornerIfValid(numberToPlace-1, i, 0, currentBoard);
    }

    if (!isCellEmpty(i, j, currentBoard) || doesColumnHaveValue(numberToPlace, i, j, currentBoard)
        || doesSquareHaveValue(numberToPlace, i, j, currentBoard)) {
      //number has already been placed -- can't place it here
      return getTopLeftCornerIfValid(numberToPlace, i, j+1, currentBoard);
    }

    //if we mae it here, its a valid move
    int[][] newBoard = copy(currentBoard);
    newBoard[i][j] = numberToPlace;
    int resultIfIPlaceNumberHere = getTopLeftCornerIfValid(numberToPlace - 1, i, 0, newBoard);
    int resultIfIDontPlaceNumberHere = getTopLeftCornerIfValid(numberToPlace, i, j+1, currentBoard);
    return Math.max(resultIfIDontPlaceNumberHere, resultIfIPlaceNumberHere);
  }

  private static int[][] copy(int[][] board)
  {
    int[][] newBoard = new int[board.length][board.length];
    for (int i = 0; i < newBoard.length; i++) {
      for (int j = 0; j < newBoard.length; j++) {
        newBoard[i][j] = board[i][j];
      }
    }
    return newBoard;
  }

  private static boolean doesRowHaveValue(int numberToPlace, int i, int j, int[][] currentBoard)
  {
    for (int k = 0; k <= 8; k++) {
      if (currentBoard[i][k] == numberToPlace) {
        //already exists in the row
        return true;
      }
    }
    return false;
  }

  private static boolean doesColumnHaveValue(int numberToPlace, int i, int j, int[][] currentBoard)
  {
    for (int k = 0; k <= 8; k++) {
      if (currentBoard[k][j] == numberToPlace) {
        //already exists in the column
        return true;
      }
    }
    return false;
  }

  private static boolean doesSquareHaveValue(int numberToPlace, int i, int j, int[][] currentBoard)
  {
    int rowOffset = 3*(i / 3);
    int colOffset = 3*(j / 3);
    for (int k = rowOffset; k < rowOffset+3; k++) {
      for (int l = colOffset; l < colOffset+3; l++) {
        if (currentBoard[k][l] == numberToPlace) {
          //already exists in the column
          return true;
        }
      }
    }
    return false;
  }
}
