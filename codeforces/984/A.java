import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int arrLen = scanner.nextInt();

    int[] arr = new int[arrLen];
    for (int i = 0; i < arrLen; i++) {
      arr[i] = scanner.nextInt();
    }

    Arrays.sort(arr);
    System.out.println(arr[(arrLen-1)>>1]);
  }
}