import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String odd = scanner.next();
    String even = scanner.next();
    String original = getOriginal(odd, even);
    System.out.println(original);
  }

  static String getOriginal(String odd, String even) {
    int idx = 1;
    StringBuilder original = new StringBuilder();
    while (idx <= odd.length() + even.length()) {
      if (isOdd(idx)) {
        original.append(odd.charAt(idx>>1));
      } else {
        original.append(even.charAt((idx>>1)-1));
      }
      idx += 1;
    }
    return original.toString();
  }

  static boolean isOdd(int num) {
    return (num & 1) != 0;
  }
}