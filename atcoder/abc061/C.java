import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

public class Main {

  static Map<Integer, Long> numbers = new TreeMap<Integer, Long>();
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int operations;
    long searchIndex;
    operations = scanner.nextInt();
    searchIndex = scanner.nextLong();
    for (int o = 0; o < operations; o += 1) {
      int number = scanner.nextInt();
      if (!numbers.containsKey(number)) {
        numbers.put(number, 0L);
      }
      int count = scanner.nextInt();
      numbers.put(number, numbers.get(number) + count);
    }
    int answer = getAnswer(searchIndex);
    System.out.println(answer);
  }

  static int getAnswer(long searchIndex) {
    long considered = 0;
    for (var numEntry : numbers.entrySet()) {
      int num = numEntry.getKey();
      long count = numEntry.getValue();
      if (considered + count >= searchIndex) {
        return num;
      }
      considered += count;
    }
    return -1; // IndexOutOfBounds
  }
}