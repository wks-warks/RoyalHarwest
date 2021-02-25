import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int sticks = scanner.nextInt();
    int select = scanner.nextInt();
    List<Integer> stickLengths = new ArrayList<Integer>();
    for (int s = 0; s < sticks; s += 1) {
      stickLengths.add(scanner.nextInt());
    }
    int toyLength = getToyLength(stickLengths, select);
    System.out.println(toyLength);
  }

  static int getToyLength(List<Integer> stickLengths, int select) {
    Collections.sort(stickLengths, Collections.reverseOrder());
    int toyLength = 0;
    for (int s = 0; s < select; s += 1) {
      toyLength += stickLengths.get(s);
    }
    return toyLength;
  }
}