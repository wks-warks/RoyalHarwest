import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    boolean properlyDistributable = a % 3 == 0 || b % 3 == 0 || (a+b) % 3 == 0;
    System.out.println(properlyDistributable ? "Possible" : "Impossible");
  }
}