import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int a = scanner.nextInt();
      int b = scanner.nextInt();

      printAnswer(a, b);
    }
  }

  static void printAnswer(int a, int b) {
    if (b == 1) {
      System.out.println("No");
    } else {
      long x = (long) a * b + a;
      long y = (long) a * b - a;
      long z = x + y;

      System.out.println("Yes");
      System.out.println(x + " " + y + " " + z);
    }
  }
}

/*
1
56 8
YES
56 448 504
*/