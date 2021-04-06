import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while(tests-->0) {
      scanner.nextInt();
      String input = scanner.next();
      boolean isBad = checkBad(input);
      System.out.println(isBad ? "Yes" : "No");
    }
  }

  static boolean checkBad(String input) {
    int terminalParentheses = 0;

    for (int i = input.length() - 1; i >= 0; i -= 1) {
      if (input.charAt(i) != ')') {
        break;
      }
      terminalParentheses += 1;
    }

    return (terminalParentheses << 1) > input.length();
  }
}