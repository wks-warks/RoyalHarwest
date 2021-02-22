import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int brackets = scanner.nextInt();
    String bracketSequence = scanner.next();
    String resultantSequence = getResultantSequence(bracketSequence);
    System.out.println(resultantSequence);
  }

  static String getResultantSequence(String bracketSequence) {
    Deque<Character> unresolved = new ArrayDeque<Character>();
    for (var bracket : bracketSequence.toCharArray()) {
      if (unresolved.size() == 0) {
        unresolved.addFirst(bracket);
      } else {
        char top = unresolved.pollFirst();
        if (top == '(' && bracket == ')') {
          continue;
        }
        unresolved.addFirst(top);
        unresolved.addFirst(bracket);
      }
    }

    StringBuilder initial = new StringBuilder();
    StringBuilder terminal = new StringBuilder();
    for (var bracket : unresolved) {
      if (bracket == ')') {
        initial.append('(');
      } else {
        terminal.append(')');
      }
    }
    return initial.toString() + bracketSequence + terminal.toString();
  }
}