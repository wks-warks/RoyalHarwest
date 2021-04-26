import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int strings = scanner.nextInt();

    for (int s = 0; s < strings; s++) {
      String inp = scanner.next();
      boolean diverse = isDiverse(inp);
      System.out.println(diverse ? "YES" : "NO");
    }
  }

  static boolean isDiverse(String inp) {
    char[] inpArr = inp.toCharArray();
    Arrays.sort(inpArr);
    
    for (int i = 1; i < inpArr.length; i++) {
      int diff = inpArr[i] - inpArr[i-1];
      if (diff != 1) {
        return false;
      }
    }
    return true;
  }
}