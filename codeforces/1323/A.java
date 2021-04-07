import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int listSize = scanner.nextInt();

      int firstOdd = -1;
      int firstEven = -1;
      for (int e = 0; e < listSize; e += 1) {
        int element = scanner.nextInt();
        if ((element & 1) == 0 && firstEven == -1) {
          firstEven = e + 1;
        } else if (firstOdd == -1) {
          firstOdd = e + 1;
        }
      }

      printAnswers(firstOdd, firstEven, listSize);
    }
  }

  static void printAnswers(int firstOdd, int firstEven, int listSize) {
    if (firstEven != -1) {
      System.out.println(1);
      System.out.println(firstEven);
    } else if (firstOdd == listSize) {
      System.out.println(-1);
    } else {
      System.out.println(2);
      System.out.println(firstOdd + " " + (firstOdd + 1));
    }
  }
}