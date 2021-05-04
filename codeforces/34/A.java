import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int soldiers = scanner.nextInt();

    int[] heights = new int[soldiers];
    for (int s = 0; s < soldiers; s++) {
      heights[s] = scanner.nextInt();
    }

    printAnswer(heights);
  }

  static void printAnswer(int[] heights) {
    int idx1 = -1;
    int idx2 = -1;
    int minDiff = Integer.MAX_VALUE;

    for (int i = 0; i < heights.length; i++) {
      int left = (i - 1 + heights.length) % heights.length;
      int right = (i + 1) % heights.length;

      int leftDiff = Math.abs(heights[i] - heights[left]);
      int rightDiff = Math.abs(heights[i] - heights[right]);

      if (leftDiff < minDiff) {
        minDiff = leftDiff;
        idx1 = left + 1;
        idx2 = i + 1;
      }

      if (rightDiff < minDiff) {
        minDiff = rightDiff;
        idx1 = right + 1;
        idx2 = i + 1;
      }
    }

    System.out.println(idx1 + " " + idx2);
  }
}