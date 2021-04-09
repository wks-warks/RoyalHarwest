import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    scanner.nextInt();
    String input = scanner.next();

    printJapaneseCrossword(input);
  }

  static void printJapaneseCrossword(String input) {
    int groups = 0;
    StringBuilder encryption = new StringBuilder();

    int prevCount = 0;
    for (var color : input.toCharArray()) {
      if (color == 'B') {
        if (prevCount == 0) {
          groups += 1;
        }

        prevCount += 1;
      } else {
        if (prevCount > 0) {
          encryption.append(prevCount + " ");
          prevCount = 0;
        }
      }
    }

    if (prevCount > 0) {
      encryption.append(prevCount + " ");
    }

    System.out.println(groups + "\n" + encryption.toString());
  }
}