import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    StringBuilder solution = new StringBuilder();
    for (int t = 0; t < tests; t++) {
      int arrLen = scanner.nextInt();
      int[] arr = new int[arrLen];
      for (int i = 0; i < arrLen; i++) {
        arr[i] = scanner.nextInt();
      }

      long maxProduct = getMaxProduct(arr);
      solution.append(maxProduct + "\n");
    }
    System.out.print(solution.toString());
  }

  static long getMaxProduct(int[] arr) {
    Sorter.sort(arr);
    long answer = Long.MIN_VALUE;
    
    int fromEnd = 5;
    for (int i = 0; i < 5; i++) {
      long candidate = getCandidate(arr, fromEnd - i);
      answer = Math.max(answer, candidate);
    }
    return answer;
  }

  static long getCandidate(int[] arr, int fromEnd) {
    int end = arr.length-1;
    long candidate = 1;
    for (int i = 0; i < fromEnd; i++) {
      candidate *= arr[end-i];
    }
    for (int i = 0; i + fromEnd < 5; i++) {
      candidate *= arr[i];
    }
    return candidate;
  }
}


class Sorter {
  static void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int r = i + (int) (Math.random() * (arr.length - i));
      int temp = arr[i];
      arr[i] = arr[r];
      arr[r] = temp;
    }
    Arrays.sort(arr);
  }
}