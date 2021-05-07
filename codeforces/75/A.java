import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String first = scanner.next();
    String second = scanner.next();

    boolean remainsEqual = checkRemainsEqual(first, second);
    System.out.println(remainsEqual ? "YES" : "NO");
  }

  static boolean checkRemainsEqual(String first, String second) {
    StringBuilder firstSB = new StringBuilder();
    for (var ch : first.toCharArray()) {
      if (ch != '0') {
        firstSB.append(ch);
      }
    }

    StringBuilder secondSB = new StringBuilder();
    for (var ch : second.toCharArray()) {
      if (ch != '0') {
        secondSB.append(ch);
      }
    }

    int result = Integer.parseInt(first) + Integer.parseInt(second);
    StringBuilder reslutSB = new StringBuilder();
    for (var ch : Integer.toString(result).toCharArray()) {
      if (ch != '0') {
        reslutSB.append(ch);
      }
    }

    int firstNew = Integer.parseInt(firstSB.toString());
    int secondNew = Integer.parseInt(secondSB.toString());
    int resultNew = Integer.parseInt(reslutSB.toString());

    return firstNew + secondNew == resultNew;
  }
}