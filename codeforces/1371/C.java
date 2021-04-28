import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      long vanilla = scanner.nextLong();
      long chocolate = scanner.nextLong();
      long type1Guests = scanner.nextLong();
      long type2Guests = scanner.nextLong();

      boolean allHappy = canEveryoneBeHappy(vanilla, chocolate, type1Guests, type2Guests);
      System.out.println(allHappy ? "Yes" : "No");
    }
  }

  static boolean canEveryoneBeHappy(long vanilla, long chocolate, long type1Guests, long type2Guests) {
    return (vanilla + chocolate >= type1Guests + type2Guests) && (Math.min(vanilla, chocolate) >= type2Guests);
  }
}