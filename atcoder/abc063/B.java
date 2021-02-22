import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();
    boolean allDifferent = checkIfDifferent(input);
    System.out.println(allDifferent ? "yes" : "no");
  }

  static boolean checkIfDifferent(String input) {
    Set<Character> characters = new HashSet<Character>();
    for (var ch : input.toCharArray()) {
      characters.add(ch);
    }
    return characters.size() == input.length();
  }
}