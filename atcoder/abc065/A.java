import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int additionalSafetyPeriod = scanner.nextInt();
    int originallyUntilExpiry = scanner.nextInt();
    int eatenAfter = scanner.nextInt();
    String state = getState(additionalSafetyPeriod, originallyUntilExpiry, eatenAfter);
    System.out.println(state);
  }

  static String getState(int additionalSafetyPeriod, int originallyUntilExpiry, int eatenAfter) {
    if (eatenAfter <= originallyUntilExpiry) {
      return "delicious";
    } else if (eatenAfter <= originallyUntilExpiry + additionalSafetyPeriod) {
      return "safe";
    } else {
      return "dangerous";
    }
  }
}