import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int listSize = scanner.nextInt();
      int zeroCount = 0;
      int oneCount = 0;

      for (int i = 0; i < listSize; i += 1) {
        if (scanner.nextInt() == 0) {
          zeroCount += 1;
        } else {
          oneCount += 1;
        }
      }

      int count = Math.max(zeroCount, oneCount);
      int selectedBit = zeroCount >= oneCount ? 0 : 1;
      if ((count & 1) == 1 && selectedBit != 0) {
        count -= 1;
      }

      System.out.println(count);
      while (count-->0) {
        System.out.print(selectedBit + " ");
      }
      System.out.println();
    }
  }
}
