import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int answer = getAnswer(n);
    System.out.println(answer);
  }
  
  static int getAnswer(int n) {
    int i;
    for (i = 0; i < 8; i += 1) {
      if (n < (1<<i)) {
        i -= 1;
        break;
      }
    }
    return 1<<i;
  }
}