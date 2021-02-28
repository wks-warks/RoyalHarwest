import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int rows = scanner.nextInt()-1;
    int cols = scanner.nextInt()-1;
    System.out.println(rows*cols);
  }
}