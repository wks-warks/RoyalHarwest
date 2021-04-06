import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int listSize = scanner.nextInt();
      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int i = 0; i < listSize; i += 1) {
        list.add(scanner.nextInt());
      }

      List<Integer> maxMEXList = getMaxMEXList(list);
      for (var mex : maxMEXList) {
        System.out.print(mex + " ");
      }
      System.out.println();
    }
  }

  static List<Integer> getMaxMEXList(List<Integer> list) {
    List<Integer> frequency = new ArrayList<Integer>(Collections.nCopies(101, 0));
    for (var element : list) {
      frequency.set(element, frequency.get(element) + 1);
    }

    List<Integer> maxMEXList = new ArrayList<Integer>(list.size());
    for (int num = 0; num <= 100; num += 1) {
      if (frequency.get(num) > 0) {
        frequency.set(num, frequency.get(num) - 1);
        maxMEXList.add(num);
      }
    }
    
    for (int num = 0; num <= 100; num += 1) {
      for (int i = 0; i < frequency.get(num); i += 1) {
        maxMEXList.add(num);
      }
    }

    return maxMEXList;
  }
}