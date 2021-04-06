import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PriorityQueue<Child> eatingQueue = new PriorityQueue<Child>(Collections.reverseOrder(new Child()));

    int childCount = scanner.nextInt();
    int candiesPerMove = scanner.nextInt();
    for (int c = 1; c <= childCount; c += 1) {
      int candiesDesired = scanner.nextInt();
      eatingQueue.add(new Child(LIG(candiesDesired, candiesPerMove), c));
    }

    System.out.println(eatingQueue.poll().getIdx());
  }

  static int LIG(int numerator, int denominator) {
    return (numerator + denominator - 1) / denominator;
  }
}

class Child implements Comparator<Child> {
  private int moves, idx;

  public Child() {

  }
  public Child(int moves, int idx) {
    this.moves = moves;
    this.idx = idx;
  }

  
  public Integer getMoves() {
    return moves;
  }
  public Integer getIdx() {
    return idx;
  }

  @Override
  public int compare(Child first, Child second) {
    if (first.getMoves() > second.getMoves()) {
      return 1;
    } else if (first.getMoves().equals(second.getMoves())) {
      if (first.getIdx() > second.getIdx()) {
        return 1;
      } else if (first.getIdx().equals(second.getIdx())) {
        return 0;
      } else {
        return -1;
      }
    } else {
      return -1;
    }
  }
}