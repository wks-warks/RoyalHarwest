import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String numString = scanner.next();

    String result = getResult(numString);
    System.out.println(result);
  }

  static String getResult(String numString) {
    boolean resultPossible = false;

    StringBuilder result = new StringBuilder(numString);
    char presEnd = numString.charAt(numString.length() - 1);
    int replacementIdx = numString.length() - 1;

    for (int i = numString.length() - 2; i >= 0; i--) {
      if (isEven(numString.charAt(i))) {
        resultPossible = true;

        if (numString.charAt(i) < presEnd || replacementIdx == numString.length() - 1) {
          replacementIdx = i;
        }
      }
    }

    result.setCharAt(numString.length() - 1, numString.charAt(replacementIdx));
    result.setCharAt(replacementIdx, presEnd);
  
    return resultPossible ? result.toString() : "-1";
  }

  static boolean isEven(char ch) {
    return (ch & 1) == 0; // '0' -> 48 therefore subtraction (ch - '0') retains parity
  }
}