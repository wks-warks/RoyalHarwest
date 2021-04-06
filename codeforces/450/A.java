import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int answer = 0;
    int maxMoves = 0;
    int childCount = scanner.nextInt();
    int candiesPerMove = scanner.nextInt();
    for (int c = 1; c <= childCount; c += 1) {
      int candiesDesired = scanner.nextInt();
      int moves = LIG(candiesDesired, candiesPerMove);
      if (moves >= maxMoves) {
        maxMoves = moves;
        answer = c;
      }
    }

    System.out.println(answer);
  }

  static int LIG(int numerator, int denominator) {
    return (numerator + denominator - 1) / denominator;
  }
}