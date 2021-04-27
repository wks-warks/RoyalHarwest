import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();

    int index = getIndex(num);
    System.out.println(index);
  }

  static int getIndex(int num) {
    String numStr = Integer.toString(num);
    int baseValue = getBase(numStr);
    int relativePos = getRelativePos(1, 1 << numStr.length(), numStr);
    return baseValue + relativePos;
  }

  static int getRelativePos(int left, int right, String numStr) {
    if (numStr.charAt(0) == '4') {
      right -= (right - left + 1) >> 1;
    } else {
      left += (right - left + 1) >> 1;
    }
    return left != right ? getRelativePos(left, right, numStr.substring(1)) : left;
  }

  static int getBase(String numStr) {
    int len = numStr.length();
    return (1 << len) - 2;
  }
}