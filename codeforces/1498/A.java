import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      long num = scanner.nextLong();
      long ans = getAns(num);
      System.out.println(ans);
    }
  }

  static long getAns(long num) {
    int digitSum = getDigitSum(num);
    long gcdSum = GCD(num, digitSum);
    return gcdSum > 1 ? num : getAns(num+1);
  }

  static int getDigitSum(long num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }
    return sum;
  }

  static long GCD(long num1, long num2) {
    if (num1 == 0) {
      return num2;
    } else {
      return GCD(num2 % num1, num1);
    }
  }
}