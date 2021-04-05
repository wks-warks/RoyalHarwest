import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int test = 0; test < tests; test += 1) {
      int rows = scanner.nextInt();
      int cols = scanner.nextInt();
      long byColumnValue = scanner.nextLong();

      long byRowValue = getByRowValue(rows, cols, byColumnValue);
      System.out.println(byRowValue);
    }
  }

  static long getByRowValue(int rows, int cols, long byColumnValue) {
    int column = LIG(byColumnValue, rows);
    int row = (int) (byColumnValue - (column-1) * rows);
    
    long byRowValue = (long) (row - 1) * cols + column;
    return byRowValue;
  }

  static int LIG(long numerator, long denominator) {
    return (int) ((numerator + denominator-1) / denominator);
  }
}