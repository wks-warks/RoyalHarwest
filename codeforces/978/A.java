import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int listSize = scanner.nextInt();
    List<Integer> list = new ArrayList<Integer>(listSize);
    for (int e = 0; e < listSize; e += 1) {
      list.add(scanner.nextInt());
    }

    List<Integer> uniques = getUniques(list);
    
    System.out.println(uniques.size());
    for (var element : uniques) {
      System.out.print(element + " ");
    }
    System.out.println();
  }

  static List<Integer> getUniques(List<Integer> list) {
    List<Integer> positions = new ArrayList<Integer>(Collections.nCopies(1001, -1));
    
    int uniqueCount = 0;
    for (int i = 0; i < list.size(); i += 1) {
      int num = list.get(i);
      // pos[num] == -1 (kya maine abhi tak num nahi dekha hai?)
      // agar haan, => num naya hai
      if (positions.get(num) == -1) {
        uniqueCount += 1;
      }
      positions.set(num, i);
    }

    List<Integer> uniques = new ArrayList<Integer>(Collections.nCopies(list.size(), -1));
    for (int num = 1; num <= 1_000; num += 1) {
      if (positions.get(num) == -1) {
        continue;
      }
      uniques.set(positions.get(num), num);
    }

    List<Integer> answer = new ArrayList<Integer>();
    for (var num : uniques) {
      if (num != -1) {
        answer.add(num);
      }
    }

    return answer;
  }
}

// 6
// 1 5 5 1 6 1

// i = 0
// num = 1
// pos[num] is -1
// pos[num] = 0
// uniqueCount INCREMENT

// i = 3
// num = 1
// pos[num] is 0
// != -1
// uniqueCount REMAINS SAME

// -1 -1 5 -1 6 1
// x  x  5 x  6 1
