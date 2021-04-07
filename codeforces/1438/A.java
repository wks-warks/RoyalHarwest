import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests > 0) {
      int arrLen = scanner.nextInt();

      for (int i = 0; i < arrLen; i += 1) {
        System.out.print(tests + " ");
      }
      System.out.println();
      
      tests -= 1;
    }
  }
}