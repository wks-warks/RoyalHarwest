import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long k = scanner.nextLong();
    List<Long> numList = getNumList(k);
    System.out.println(numList.size());
    for (var num : numList) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  static List<Long> getNumList(long k) {
    List<Long> numList = new ArrayList<Long>(50);
    long reductionsForEach = k / 50;
    int additionalReductions = (int) (k % 50);
    for (int i = 0; i < additionalReductions; i += 1) {
      long decrement = (reductionsForEach + 1) * 50;
      long increment = 49 * reductionsForEach + additionalReductions - 1;
      numList.add(49 + decrement - increment);
    }
    for (int i = additionalReductions; i < 50; i += 1) {
      long decrement = reductionsForEach * 50;
      long increment = 49 * reductionsForEach + additionalReductions;
      numList.add(49 + decrement - increment);
    }
    return numList;
  }
}