import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    String result = getResult(a + b);
    System.out.println(result);
  }

  static String getResult(int sum) {
    if (sum >= 10) {
      return "error";
    }
    else {
      return Integer.toString(sum);
    }
  }
}