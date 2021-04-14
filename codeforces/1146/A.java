import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();

    int maxBeautiful = computeMaxBeautiful(input);
    System.out.println(maxBeautiful);
  }

  static int computeMaxBeautiful(String input) {
    int aCount = 0;
    for (var ch : input.toCharArray()) {
      aCount += ch == 'a' ? 1 : 0;
    }

    return Math.min((aCount<<1)-1, input.length());
  }
}