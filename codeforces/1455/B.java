import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int destination = scanner.nextInt();
      int moves = getMoves(destination);
      System.out.println(moves);
    }
  }

  static int getMoves(int destination) {
    if (destination <= 0) {
      return -destination;
    }

    int sumInverse = getSumInverse(destination);
    int sum = getSum(sumInverse);
    return sum - destination == 1 ? sumInverse + 1 : sumInverse;
  }

  static int getSumInverse(int sum) {
    // n*n + n - 2*sum = 0
    // (-1 + root(1+8*sum)) / 2 = n
    return (int) Math.ceil((-1 + Math.sqrt(1 + 8*sum)) / 2);
  }

  static int getSum(int n) {
    return n * (n + 1) >> 1;
  }
}