import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int piles = scanner.nextInt();
      int limit = scanner.nextInt();

      List<Integer> candies = new ArrayList<Integer>(piles);
      for (int p = 0; p < piles; p += 1) {
        candies.add(scanner.nextInt());
      }

      int operations = countOperations(candies, limit);
      System.out.println(operations);
    }
  }

  static int countOperations(List<Integer> candies, int limit) {
    int min = getMin(candies);

    int operations = 0;
    for (var element : candies) {
      operations += (limit - element) / min;
    }
    int extras = (limit - min) / min;
    operations -= extras;

    return operations;
  }

  static int getMin(List<Integer> list) {
    int min = Integer.MAX_VALUE;

    for (var element : list) {
      min = Math.min(min, element);
    }

    return min;
  }
}