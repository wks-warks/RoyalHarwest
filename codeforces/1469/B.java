import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int redCount = scanner.nextInt();
      List<Integer> reds = new ArrayList<Integer>(redCount);
      for (int r = 0; r < redCount; r += 1) {
        reds.add(scanner.nextInt());
      }

      int blueCount = scanner.nextInt();
      List<Integer> blues = new ArrayList<Integer>(blueCount);
      for (int b = 0; b < blueCount; b += 1) {
        blues.add(scanner.nextInt());
      }

      int maxFa = getMaxFa(reds, blues);
      System.out.println(maxFa);
    }
  }

  static int getMaxFa(List<Integer> reds, List<Integer> blues) {
    int redBest = 0;
    int redSum = 0;
    for (var num : reds) {
      redSum += num;
      redBest = Math.max(redBest, redSum);
    }

    int blueBest = 0;
    int blueSum = 0;
    for (var num : blues) {
      blueSum += num;
      blueBest = Math.max(blueBest, blueSum);
    }

    return redBest + blueBest;
  }
}