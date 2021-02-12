import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String first = scanner.next();
    String second = scanner.next();
    String result = comparisonResult(first, second);
    System.out.println(result);
  }

  static String comparisonResult(String first, String second) {
    if (first.length() > second.length()) {
      return "GREATER";
    } else if (second.length() > first.length()) {
      return "LESS";
    } else {
      if (first.compareTo(second) > 0) {
        return "GREATER";
      } else if (first.compareTo(second) == 0) {
        return "EQUAL";
      } else {
        return "LESS";
      }
    }
  }
}