import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int queries = scanner.nextInt();

    while (queries-->0) {
      long waterNeeded = scanner.nextLong();
      int oneLitreCost = scanner.nextInt();
      int twoLitreCost = scanner.nextInt();

      long minCost = computeMinCost(waterNeeded, oneLitreCost, twoLitreCost);
      System.out.println(minCost);
    }
  }

  static long computeMinCost(long waterNeeded, int oneLitreCost, int twoLitreCost) {
    long firstCandidate = waterNeeded * oneLitreCost;
    long secondCandidate = (waterNeeded>>1) * twoLitreCost + (waterNeeded&1) * oneLitreCost;
    return Math.min(firstCandidate, secondCandidate);
  }
}