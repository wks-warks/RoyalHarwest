import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t += 1) {
      int blocks = scanner.nextInt();
      List<Integer> heights = new ArrayList<Integer>(blocks);
      for (int b = 0; b < blocks; b += 1) {
        heights.add(scanner.nextInt());
      }

      boolean beautifiable = checkBeatifiable(heights);
      System.out.println(beautifiable ? "Yes" : "No");
    }
  }

  static boolean checkBeatifiable(List<Integer> heights) {
    List<Long> prefixSum = getPrefixSum(heights);

    for (int i = 0; i < prefixSum.size(); i += 1) {
      if (prefixSum.get(i) < sumUpto(i)) {
        return false;
      }
    }

    return true;
  }

  static long sumUpto(int num) {
    return (long) num * (num + 1) / 2;
  }

  static List<Long> getPrefixSum(List<Integer> list) {
    List<Long> prefixSum = new ArrayList<Long>(list.size());
    prefixSum.add((long) list.get(0));
    
    for (int i = 1; i < list.size(); i += 1) {
      prefixSum.add(prefixSum.get(i-1) + list.get(i));
    }

    return prefixSum;
  }
}