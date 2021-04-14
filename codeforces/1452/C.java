import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      String bracketSequence = scanner.next();
      int movesPossible = computeMovesPossible(bracketSequence);
      System.out.println(movesPossible);
    }
  }
  
  static int computeMovesPossible(String bracketSequence) {
    int roundLeft = 0;
    int squareLeft = 0;
    int moves = 0;

    for (var bracket : bracketSequence.toCharArray()) {
      switch(bracket) {
        case '('  : roundLeft += 1;
                    break;
        case ')'  : moves += roundLeft > 0 ? 1 : 0;
                    roundLeft -= roundLeft > 0 ? 1 : 0;
                    break;
        case '['  : squareLeft += 1;
                    break;
        case ']'  : moves += squareLeft > 0 ? 1 : 0;
                    squareLeft -= squareLeft > 0 ? 1 : 0;
                    break;
        default : System.exit(1);
      }
    }

    return moves;
  }
}