import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scanner.nextInt();
    }

    long evenSum = getEvenSum(arr);
    System.out.println(evenSum);
  }

  static long getEvenSum(int[] arr) {
    int oddCount = 0;
    int minOdd = Integer.MAX_VALUE;

    long arrSum = 0;
    for (var num : arr) {
      if ((num & 1) == 1) {
        oddCount += 1;
        minOdd = Math.min(minOdd, num);
      }

      arrSum += num;
    }

    return arrSum - ((oddCount & 1) == 1 ? minOdd : 0);
  }
}