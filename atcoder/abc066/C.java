import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int elementCount = scanner.nextInt();
    Deque<Integer> elements = new ArrayDeque<Integer>(elementCount);
    for (int i = 0; i < elementCount; i += 1) {
      if ((i & 1) == 0) {
        elements.add(scanner.nextInt());
      } else {
        elements.addFirst(scanner.nextInt());
      }
    }

    boolean traverseInReverse = (elementCount & 1) == 1;
    StringBuilder output = new StringBuilder();
    for (int e = 0; e < elementCount; e += 1) {
      if (traverseInReverse) {
        output.append(elements.pollLast() + " ");
      } else {
        output.append(elements.poll() + " ");
      }
    }

    System.out.println(output.toString());
  }
}