import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> prices = new ArrayList<Integer>(3);
    for (int b = 0; b < 3; b += 1) {
      prices.add(scanner.nextInt());
    }

    int minSpending = getMinSpending(prices);
    System.out.println(minSpending);
  }

  static int getMinSpending(List<Integer> prices) {
    int exclude = Collections.max(prices);
    int sum = prices.stream().flatMapToInt(IntStream::of).sum();
    return sum - exclude;
  }
}