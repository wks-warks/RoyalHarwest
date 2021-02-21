import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cities = scanner.nextInt();
    int roads = scanner.nextInt();
    List<Integer> roadCount = new ArrayList<Integer>(Collections.nCopies(cities, 0));
    for (int r = 0; r < roads; r += 1) {
      int first = scanner.nextInt();
      int second = scanner.nextInt();
      roadCount.set(first-1, roadCount.get(first-1) + 1);
      roadCount.set(second-1, roadCount.get(second-1) + 1);
    }
    for (var count : roadCount) {
      System.out.println(count);
    }
  }
}