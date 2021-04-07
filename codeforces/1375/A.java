import java.util.Scanner;


public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int elements = scanner.nextInt();
      
      for (int e = 0; e < elements; e += 1) {
        int num = scanner.nextInt();
        System.out.print(((e & 1) == 0 ? Math.abs(num) : -Math.abs(num)) + " ");
      }
      System.out.println();
    }
  }
}