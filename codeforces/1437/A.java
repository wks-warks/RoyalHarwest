import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int lowerBound = scanner.nextInt();
      int upperBound = scanner.nextInt();

      boolean canFoolEveryone = checIfkCanFoolEveryone(lowerBound, upperBound);
      System.out.println(canFoolEveryone ? "Yes" : "No");
    }
  }

  static boolean checIfkCanFoolEveryone(int lowerBound, int upperBound) {
    int mod = (upperBound >> 1) + 1;
    return (((lowerBound - mod) << 1) >= mod || (upperBound >> 1) < lowerBound);
  }
}