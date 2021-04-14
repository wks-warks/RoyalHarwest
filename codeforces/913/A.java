import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int power = scanner.nextInt();
    int num = scanner.nextInt();

    int numModulo = getNumModulo(power, num);
    System.out.println(numModulo);
  }

  static int getNumModulo(int power, int num) {
    return power >= 30 ? num : num % (1<<power);
  }
}