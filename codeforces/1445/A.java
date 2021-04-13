import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int listSize = scanner.nextInt();
      int sumLimit = scanner.nextInt();
    
      List<Integer> listA = new ArrayList<Integer>(listSize);
      for (int i = 0; i < listSize; i += 1) {
        listA.add(scanner.nextInt());
      }

      List<Integer> listB = new ArrayList<Integer>(listSize);
      for (int i = 0; i < listSize; i += 1) {
        listB.add(scanner.nextInt());
      }

      boolean boundable = isBoundable(listA, listB, sumLimit);
      System.out.println(boundable ? "Yes" : "No");
    }
  }

  static boolean isBoundable(List<Integer> listA, List<Integer> listB, int sumLimit) {
    int end = listA.size() - 1;
    for (int i = 0; i < listA.size(); i += 1) {
      if (listA.get(i) + listB.get(end-i) > sumLimit) {
        return false;
      }
    }

    return true;
  }
}