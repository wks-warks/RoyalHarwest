import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int sticks = scanner.nextInt();
    int select = scanner.nextInt();
    PriorityQueue<Integer> stickLengths = new PriorityQueue<Integer>(Collections.reverseOrder());
    for (int s = 0; s < sticks; s += 1) {
      stickLengths.add(scanner.nextInt());
    }
    int toyLength = getToyLength(stickLengths, select);
    System.out.println(toyLength);
  }

  static int getToyLength(PriorityQueue<Integer> stickLengths, int select) {
    int toyLength = 0;
    for (int s = 0; s < select; s += 1) {
      toyLength += stickLengths.poll();
    }
    return toyLength;
  }
}