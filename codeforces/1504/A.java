import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    StringBuilder sb = new StringBuilder();
    for (int t = 0; t < tests; t++) {
      String s = scanner.next();
      String solution = getSolution(s);
      sb.append(solution + "\n");
    }
    System.out.print(sb.toString());
  }

  static String getSolution(String s) {
    int countAtStart = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != 'a') {
        break;
      }
      countAtStart++;
    }
    if (countAtStart == s.length()) {
      return "NO";
    }

    int countAtEnd = 0;
    for (int i = s.length()-1; i >= 0; i--) {
      if (s.charAt(i) != 'a') {
        break;
      }
      countAtEnd++;
    }

    if (countAtStart >= countAtEnd) {
      return "YES\n" + "a" + s;
    } else {
      return "YES\n" + s + "a";
    }
  }
}