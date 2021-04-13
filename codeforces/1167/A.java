import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      scanner.nextInt();
      String number = scanner.next();
      boolean telephoneNumberExtractable = isTelephoneNumberExtractable(number);
      System.out.println(telephoneNumberExtractable ? "YES" : "NO");
    }
  }

  static boolean isTelephoneNumberExtractable(String number) {
    if (number.length() >= 11) {
      for (int candidate = number.length() - 11; candidate >= 0; candidate -= 1) {
        if (number.charAt(candidate) == '8') {
          return true;
        }
      }
    }

    return false;
  }
}