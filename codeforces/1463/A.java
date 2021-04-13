import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int first = scanner.nextInt();
      int second = scanner.nextInt();
      int third = scanner.nextInt();

      boolean killableTogether = areKillableTogether(first, second, third);
      System.out.println(killableTogether ? "YES" : "NO");
    }
  }

  static boolean areKillableTogether(int first, int second, int third) {
    int min = Math.min(first, Math.min(second, third));
    int max = Math.max(first, Math.max(second, third));
    int median = first + second + third - min - max;
  
    return areKillableTogether(min, median, max, 0);
  }

  static boolean areKillableTogether(int min, int median, int max, int shots) {
    if (min < 0) {
      return false;
    }

    if (median < max) {
      int additionalShots = max - median;
      int AOEdamage = (shots % 7 + additionalShots) / 7;
      max -= additionalShots;
      median -= AOEdamage;
      min -= AOEdamage;
      return areKillableTogether(min, median, max, shots + additionalShots);

    } else {
      if (min < median) {
        int additionalShots = median - min;
        int AOEdamage = (shots % 7 + additionalShots) / 7;
        max -= AOEdamage;
        median -= additionalShots;
        min -= AOEdamage;
        return areKillableTogether(min, median, max, shots + additionalShots);

      } else {
        switch (min % 3) {
          case 0: return shots % 7 == 0;
          case 1: return shots % 7 == 6;
          default: return shots % 7 == 3;
        }
      }
    }
  }
}