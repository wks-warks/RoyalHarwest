import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      scanner.nextInt();
      String input = scanner.next();
      
      int winner = getWinner(input);
      System.out.println(winner);
    }
  }

  static int getWinner(String input) {
    boolean RazeHasOdd = false;
    boolean BreachHasEven = false;

    for (int i = 0; i < input.length(); i += 1) {
      int num = input.charAt(i) - '0';

      if (isOdd(i)) {
        BreachHasEven |= !isOdd(num);
      } else {
        RazeHasOdd |= isOdd(num);
      }
    }

    if (isOdd(input.length())) {
      return RazeHasOdd ? 1 : 2;
    } else {
      return BreachHasEven ? 2 : 1;
    }
  }

  static boolean isOdd(int num) {
    return (num & 1) == 1;
  }
}