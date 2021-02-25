import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cards = scanner.nextInt();
    List<Integer> cardValues = new ArrayList<Integer>(cards);
    for (int c = 0; c < cards; c += 1) {
      cardValues.add(scanner.nextInt());
    }
    long minDiff = getMinDiff(cardValues);
    System.out.println(minDiff);
  }

  static long getMinDiff(List<Integer> cardValues) {
    List<Long> prefixSum = getPrefixSum(cardValues);
    long totalSum = prefixSum.get(prefixSum.size()-1);
    long minDiff = Long.MAX_VALUE;
    for (int i = 0; i < prefixSum.size()-1; i += 1) {
      long remainder = totalSum - prefixSum.get(i);
      long diff = Math.abs(prefixSum.get(i) - remainder);
      minDiff = Math.min(minDiff, diff);
    }
    return minDiff;
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