import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int a = scanner.nextInt();
      int b = scanner.nextInt();
      int moves = getMoves(a, b);
      System.out.println(moves);
    }
  }

  static int getMoves(int a, int b) {
    switch(Math.abs(a - b)) {
      case 0: return 0;
      case 1:
      case 2:
      case 5: return 1;
      default: return getMoves(Math.abs(a - b));
    }
  }

  static int getMoves(int diff) {
    int ans = diff/5;
    switch (diff % 5) {
      case 0: return ans;
      case 1:
      case 2: return ans + 1;
      case 3:
      case 4: return ans + 2;
      default: return -1;
    }
  }
}