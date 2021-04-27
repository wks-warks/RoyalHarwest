import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    int s = scanner.nextInt();

    boolean reachable = isReachable(a, b, s);
    System.out.println(reachable ? "Yes" : "No");
  }

  static boolean isReachable(int a, int b, int s) {
    int minMoves = Math.abs(a) + Math.abs(b);
    return s >= minMoves && ((s - minMoves) & 1) == 0;
  }
}