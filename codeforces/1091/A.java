import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    int yellowCount = scanner.nextInt();
    int blueCount = scanner.nextInt();
    int redCount = scanner.nextInt();

    int maxTotal = computeMaxTotal(yellowCount, blueCount, redCount);
    System.out.println(maxTotal);
  }

  static int computeMaxTotal(int yellowCount, int blueCount, int redCount) {
    int yellowMax = yellowCount;
    yellowMax = Math.min(yellowMax, blueCount - 1);
    yellowMax = Math.min(yellowMax, redCount - 2);
  
    return 3 * yellowMax + 3;
  }
}