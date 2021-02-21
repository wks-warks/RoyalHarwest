import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a, b, c;
    a = scanner.nextInt();
    b = scanner.nextInt();
    c = scanner.nextInt();
    boolean ge_a = c >= a;
    boolean le_b = c <= b;
    System.out.println(ge_a && le_b ? "Yes" : "No");
  }
}