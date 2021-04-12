import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int barrelCount = scanner.nextInt();
      int maxPours = scanner.nextInt();

      List<Integer> barrelWater = new ArrayList<Integer>();
      for (int b = 0; b < barrelCount; b += 1) {
        barrelWater.add(scanner.nextInt());
      }

      long maxDifference = computeMaxDifference(barrelCount, maxPours, barrelWater);
      System.out.println(maxDifference);
    }
  }

  static long computeMaxDifference(int barrelCount, int maxPours, List<Integer> barrelWater) {
    Collections.sort(barrelWater);

    int max = barrelWater.get(barrelCount - 1);

    long addedMax = 0;
    for (int i = 0; i < maxPours; i += 1) {
      addedMax += barrelWater.get(barrelCount - 2 - i);
    }
    long diff = max + addedMax;

    return diff;
  }
}