import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int orangeCount = scanner.nextInt();
    int maxAcceptableSize = scanner.nextInt();
    int wasteCollectorSize = scanner.nextInt();

    int emptyingCount = 0;
    int accumulatedWaste = 0;
    for (int og = 0; og < orangeCount; og += 1) {
      int orangeSize = scanner.nextInt();

      if (orangeSize <= maxAcceptableSize) {
        accumulatedWaste += orangeSize;
      }

      if (accumulatedWaste > wasteCollectorSize) {
        emptyingCount += 1;
        accumulatedWaste = 0;
      }
    }

    System.out.println(emptyingCount);
  }
}