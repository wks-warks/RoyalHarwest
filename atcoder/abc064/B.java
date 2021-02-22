import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int houses = scanner.nextInt();
    int leastCoordinate = Integer.MAX_VALUE;
    int maxCoordinate = Integer.MIN_VALUE;
    for (int h = 0; h < houses; h += 1) {
      int coordinate = scanner.nextInt();
      leastCoordinate = Math.min(leastCoordinate, coordinate);
      maxCoordinate = Math.max(maxCoordinate, coordinate);
    }
    System.out.println(maxCoordinate - leastCoordinate);
  }
}