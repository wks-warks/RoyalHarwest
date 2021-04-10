import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int firstPos = scanner.nextInt();
      int secondPos = scanner.nextInt();
      int thirdPos = scanner.nextInt();

      int minPossibleDistance = computeMinPossibleDistance(firstPos, secondPos, thirdPos);
      System.out.println(minPossibleDistance);
    }
  }

  static int computeMinPossibleDistance(int firstPos, int secondPos, int thirdPos) {
    int max = Math.max(firstPos, Math.max(secondPos, thirdPos));
    int min = Math.min(firstPos, Math.min(secondPos, thirdPos));

    return Math.max(0, max - min - 2) << 1;
  }
}