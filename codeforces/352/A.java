import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cards = scanner.nextInt();

    int[] values = new int[cards];
    for (int c = 0; c < cards; c++) {
      values[c] = scanner.nextInt();
    }

    String largestNum = getLargestNum(values);
    System.out.println(largestNum);
  }

  static String getLargestNum(int[] values) {
    int fives = 0;
    int zeroes = 0;

    for (var num : values) {
      if (num == 0) {
        zeroes += 1;
      } else {
        fives += 1;
      }
    }

    if (zeroes < 1) {
      return "-1";
    }

    if (fives < 9) {
      return "0";
    } else {
      
      int fivesToPut = fives - fives % 9;
      StringBuilder largestNum = new StringBuilder();
      while (fivesToPut-- > 0) {
        largestNum.append('5');
      }
      while (zeroes-- > 0) {
        largestNum.append('0');
      }

      return largestNum.toString();
    }
  }
}