import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int listSize = scanner.nextInt();
    List<Integer> list = new ArrayList<Integer>(listSize);
    for (int e = 0; e < listSize; e += 1) {
      list.add(scanner.nextInt());
    }
    Deque<Integer> uniques = getUniques(list);
    System.out.println(uniques.size());
    for (var element : uniques) {
      System.out.print(element + " ");
    }
    System.out.println();
  }

  static Deque<Integer> getUniques(List<Integer> list) {
    Deque<Integer> uniques = new ArrayDeque<Integer>();
    for (int i = list.size()-1; i >= 0; i -= 1) {
      int num = list.get(i);
      if (uniques.contains(num)) {
        continue;
      }
      uniques.addFirst(num);
    }
    return uniques;
  }
}

// 6
// 1 5 5 1 6 1
// x x 5 x 6 1