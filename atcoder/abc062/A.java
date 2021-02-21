import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> group1 = Arrays.asList(new Integer[]{1, 3, 5, 7, 8, 10, 12});
    List<Integer> group2 = Arrays.asList(new Integer[]{4, 6, 9, 11});
    int num1 = scanner.nextInt();
    int num2 = scanner.nextInt();
    boolean inGroup1 = group1.contains(num1) && group1.contains(num2);
    boolean inGroup2 = group2.contains(num1) && group2.contains(num2);
    if (inGroup1 || inGroup2) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
  }
}