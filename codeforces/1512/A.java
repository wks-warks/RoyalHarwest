import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int listSize = scanner.nextInt();
      List<Integer> list = new ArrayList<Integer>(listSize);

      for (int e = 0; e < listSize; e++) {
        list.add(scanner.nextInt());
      }

      int differentIdx = getDifferentIdx(list);
      System.out.println(differentIdx);
    }
  }

  static int getDifferentIdx(List<Integer> list) {
    Nums type1 = null;
    Nums type2 = null;

    for (int i = 0; i < list.size(); i++) {
      if (type1 == null) {
        type1 = new Nums(list.get(i), i+1, 1);
      } else if (type1.getNumber() == list.get(i)) {
        type1.incrementCount();
      } else if (type2 == null) {
        type2 = new Nums(list.get(i), i+1, 1);
      } else {
        type2.incrementCount();
      }
    }

    return type1.getCount() < type2.getCount() ? type1.getFirstIdx() : type2.getFirstIdx();
  }
}

class Nums {
  private int number, firstIdx, count;

  public Nums() {
    
  }
  public Nums(int number, int firstIdx, int count) {
    this.number = number;
    this.firstIdx = firstIdx;
    this.count = count;
  }

  public int getNumber() {
    return number;
  }
  public int getFirstIdx() {
    return firstIdx;
  }
  public int getCount() {
    return count;
  }

  void incrementCount() {
    count++;
  }
}