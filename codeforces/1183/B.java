import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int queries = scanner.nextInt();

    for (int q = 0; q < queries; q++) {
      int n = scanner.nextInt();
      int k = scanner.nextInt();
      
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = scanner.nextInt();
      }

      int equalizedPrice = getEqualizedPrice(arr, k);
      System.out.println(equalizedPrice);
    }
  }

  static int getEqualizedPrice(int[] arr, int k) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (var num : arr) {
      min = Math.min(num, min);
      max = Math.max(num, max);
    }

    if (min + k < max - k) {
      return -1;
    } else {
      return min + k;
    }
  }
}