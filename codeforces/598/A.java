import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int n = scanner.nextInt();
      long trickySum = getTrickySum(n);
      
      System.out.println(trickySum);
    }
  }

  static long getTrickySum(int n) {
    long normalSum = (long) n * (n + 1) >> 1;
    long trickySum = normalSum;

    int bit = 1;
    while (bit <= n) {
      trickySum -= bit << 1;
      bit <<= 1;
    }

    return trickySum;
  }
}