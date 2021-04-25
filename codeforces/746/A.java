import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int lemons = scanner.nextInt();
    int apples = scanner.nextInt();
    int pears = scanner.nextInt();

    int consumption = getConsumption(lemons, apples, pears);
    System.out.println(consumption);
  }

  static int getConsumption(int lemons, int apples, int pears) {
    int compotes = Math.min(lemons, Math.min(apples >> 1, pears >> 2));
    return 7 * compotes;
  }
}