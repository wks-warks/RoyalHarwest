import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int x1 = scanner.nextInt();
      int y1 = scanner.nextInt();

      int x2 = scanner.nextInt();
      int y2 = scanner.nextInt();

      int movesRequired = getMovesRequired(x1, y1, x2, y2);
      System.out.println(movesRequired);
    }
  }

  static int getMovesRequired(int x1, int y1, int x2, int y2) {
    if (x1 == x2 || y1 == y2) {
      return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    } else {
      return 2 + Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
  }
}