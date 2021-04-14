import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int denominations = scanner.nextInt();
    int desiredSum = scanner.nextInt();

    int requiredCoins = computeRequiredCoins(denominations, desiredSum);
    System.out.println(requiredCoins);
  }

  static int computeRequiredCoins(int denominations, int desiredSum) {
    return leastIntGreater(desiredSum, denominations);
  }

  static int leastIntGreater(int numerator, int denominator) {
    return (numerator + denominator - 1) / denominator;
  }
}