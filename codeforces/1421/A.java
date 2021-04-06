import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while(tests-->0) {
      int num1 = scanner.nextInt();
      int num2 = scanner.nextInt();
      int minXOR = getMinXOR(num1, num2);
      System.out.println(minXOR);
    }
  }

  static int getMinXOR(int num1, int num2) {
    int answer = 0;
    int bit = 1;

    while (bit <= num1 || bit <= num2) {
      if ((bit & num1) != (bit & num2)) {
        answer += bit;
      }
      bit <<= 1;
    }

    return answer;
  }
}