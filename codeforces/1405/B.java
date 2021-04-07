import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t += 1) {
      int elementCount = scanner.nextInt();
      long negativeSum = 0;
      long positiveSum = 0;

      for (int e = 0; e < elementCount; e += 1) {
        int num = scanner.nextInt();

        if (num < 0) {
          int free = (int) Math.min(Math.abs(num), positiveSum);
          positiveSum -= free;
          num += free;
          negativeSum += num;
        } else {
          positiveSum += num;
        }
      }

      long spendingRequired = -negativeSum;
      System.out.println(spendingRequired);
    }
  }
}