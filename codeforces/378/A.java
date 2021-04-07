import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int firstNum = scanner.nextInt();
    int secondNum = scanner.nextInt();

    printResults(firstNum, secondNum);
  }

  static void printResults(int firstNum, int secondNum) {
    int firstWins = 0;
    int secondWins = 0;
    int draws = 0;

    for (int i = 1; i <= 6; i += 1) {
      if (Math.abs(firstNum - i) < Math.abs(secondNum - i)) {
        firstWins += 1;
      } else if (Math.abs(firstNum - i) == Math.abs(secondNum - i)) {
        draws += 1;
      } else {
        secondWins += 1;
      }
    }

    System.out.println(firstWins + " " + draws + " " + secondWins);
  }
}