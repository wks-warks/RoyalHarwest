import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next();
    int maxEvenLength = getMaxEvenLength(str.substring(0, str.length()-1));
    System.out.println(maxEvenLength);
  }

  static int getMaxEvenLength(String str) {
    if (isEven(str)) {
      return str.length();
    } else {
      return getMaxEvenLength(str.substring(0, str.length()-1));
    }
  }

  static boolean isEven(String str) {
    if ((str.length() & 1) != 0) {
      return false;
    }
    for (int ptr1 = 0, ptr2 = str.length() >> 1; ptr2 < str.length(); ptr1 += 1, ptr2 += 1) {
      if (str.charAt(ptr1) != str.charAt(ptr2)) {
        return false;
      }
    }
    return true;
  }
}