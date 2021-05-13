import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int left = scanner.nextInt();
      int right = scanner.nextInt();
      System.out.println(left + " " + (left << 1));
    }
  }
}