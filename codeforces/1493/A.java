import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int upperLimit = scanner.nextInt();
      int forbiddenSum = scanner.nextInt();

      printChosen(upperLimit, forbiddenSum);
    }
  }

  static void printChosen(int upperLimit, int forbiddenSum) {
    int firstChosen = (forbiddenSum + 1) >> 1;
    int chosenCount = upperLimit - firstChosen;

    System.out.println(chosenCount);
    for (int chosen = firstChosen; chosen <= upperLimit; chosen += 1) {
      if (chosen != forbiddenSum) {
        System.out.print(chosen + " ");
      }
    }
    System.out.println();
  }
}