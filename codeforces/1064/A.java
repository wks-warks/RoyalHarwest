import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    List<Integer> sides = new ArrayList<Integer>(3);
    for (int s = 0; s < 3; s++) {
      sides.add(scanner.nextInt());
    }

    int movesNeeded = computeMovesNeeded(sides);
    System.out.println(movesNeeded);
  }

  static int computeMovesNeeded(List<Integer> sides) {
    Collections.sort(sides);
    return Math.max(0, (sides.get(2) - sides.get(1) + 1) - sides.get(0));
  }
}