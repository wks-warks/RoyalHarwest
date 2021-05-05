import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int arrLen = scanner.nextInt();

    int[] arr = new int[arrLen];
    for (int i = 0; i < arrLen; i++) {
      arr[i] = scanner.nextInt();
    }

    int minInstability = getMinInstability(arr);
    System.out.println(minInstability);
  }

  static int getMinInstability(int[] arr) {
    int max1, max2, min1, min2;
    max1 = max2 = Integer.MIN_VALUE;
    min1 = min2 = Integer.MAX_VALUE;

    for (var num : arr) {
      if (num > max2) {
        max2 = num;
      }
      if (max2 > max1) {
        max1 ^= max2;
        max2 ^= max1;
        max1 ^= max2;
      }

      if (num < min2) {
        min2 = num;
      }
      if (min2 < min1) {
        min1 ^= min2;
        min2 ^= min1;
        min1 ^= min2;
      }
    }

    return Math.min(max2 - min1, max1 - min2);
  }
}