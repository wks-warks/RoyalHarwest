import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int strLen = scanner.nextInt();
    int availableCount = scanner.nextInt();
    String str = scanner.next();

    int[] availableChars = new int[availableCount];
    for (int i = 0; i < availableCount; i++) {
      availableChars[i] = scanner.next().charAt(0);
    }

    long printableCount = getPrintableCount(str, availableChars);
    System.out.println(printableCount);
  }

  static long getPrintableCount(String str, int[] availableChars) {
    boolean[] available = new boolean[26];
    for (int i = 0; i < availableChars.length; i++) {
      available[availableChars[i]-'a'] = true;
    }

    long printableCount = 0;
    int currChars = 0;

    for (var ch : str.toCharArray()) {
      if (!available[ch-'a']) {
        printableCount += (long) currChars * (currChars + 1) >> 1;
        currChars = 0;
      } else {
        currChars += 1;
      }
    }

    printableCount += (long) currChars * (currChars + 1) >> 1;
    return printableCount;
  }
}