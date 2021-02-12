import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> heights = new ArrayList<Integer>(3);
    for (int pole = 0; pole < 3; pole += 1) {
      heights.add(scanner.nextInt());
    }
    boolean inLine = heights.get(2) - heights.get(1) == heights.get(1) - heights.get(0);
    System.out.println(inLine ? "YES" : "NO");
  }
}