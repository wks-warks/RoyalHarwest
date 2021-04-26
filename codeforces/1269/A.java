import java.util.Scanner;

public class ain {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();
    printAnswer(num);
  }

  static void printAnswer(int num) {
    int first = (num & 1) == 0 ? 4 : 9;
    int second = first + num;

    System.out.println(second + " " + first);
  }
}