import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();
    for (int t = 0; t < tests; t++) {
      int districts = scanner.nextInt();
      int[] gangs = new int[districts];
      for (int d = 0; d < districts; d++) {
        gangs[d] = scanner.nextInt();
      }

      int[][] roads = getRoads(districts, gangs);
      if (roads == null) {
        System.out.println("NO");
      } else {
        System.out.println("YES");
        for (var r : roads) {
          System.out.println(r[0] + " " + r[1]);
        }
      }
    }
  }

  static int[][] getRoads(int districts, int[] gangs) {
    int firstDistrict = 1; // 1-indexed
    int otherDistrict = -1;
    int g1 = gangs[firstDistrict-1];

    for (int d = 0; d < districts; d++) {
      if (gangs[d] != g1) {
        otherDistrict = d+1;
        break;
      }
    }

    if (otherDistrict == -1) {
      return null;
    }

    int[][] roads = new int[districts-1][2];
    roads[0][0] = firstDistrict;
    roads[0][1] = otherDistrict;
    int r = 1;
    for (int d = 1; d < districts; d++) {
      if (d+1 == otherDistrict) {
        continue;
      }
      roads[r][0] = d+1;
      roads[r][1] = gangs[roads[r-1][0]-1] != gangs[roads[r][0]-1] ? roads[r-1][0] : roads[r-1][1];
      r++;
    }
    return roads;
  }
}