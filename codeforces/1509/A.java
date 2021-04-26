import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int otherCount = scanner.nextInt();
      List<Integer> oddHeights = new ArrayList<Integer>();
      List<Integer> evenHeights = new ArrayList<Integer>();
      
      for (int s = 0; s < otherCount; s++) {
        int height = scanner.nextInt();
        if ((height & 1) == 0) {
          evenHeights.add(height);
        } else {
          oddHeights.add(height);
        }
      }

      for (var height : oddHeights) {
        System.out.print(height + " ");
      }
      for (var height : evenHeights) {
        System.out.print(height + " ");
      }
      System.out.println();
    }
  }
}