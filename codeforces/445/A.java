import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int rows = scanner.nextInt();
    scanner.nextInt();

    for (int r = 0; r < rows; r += 1) {
      String input = scanner.next();
      String output = getOutput(input, r);
      System.out.println(output);
    }
  }

  static String getOutput(String input, int r) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < input.length(); i += 1) {
      if (input.charAt(i) == '-') {
        sb.append('-');
      } else {
        char piece = (r & 1) != (i & 1) ? 'B' : 'W';
        sb.append(piece);
      }
    }

    return sb.toString();
  }
}