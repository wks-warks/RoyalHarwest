import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();

    String winner = getWinner(input);
    System.out.println(winner);
  }

  static String getWinner(String input) {
    List<Integer> charCount = new ArrayList<Integer>(Collections.nCopies(26, 0));
    for (var ch : input.toCharArray()) {
      charCount.set(ch - 'a', charCount.get(ch - 'a') + 1);
    }
  
    return getWinner(charCount, 0);
  }

  static String getWinner(List<Integer> charCount, int move) {
    if (isPalindrome(charCount)) {
      return (move & 1) == 0 ? "First" : "Second";
    }

    boolean willLose = true;
    int candidate = -1;
    for (int alph = 0; alph < 26; alph += 1) {
      if (charCount.get(alph) > 0) {
        candidate = alph;
        charCount.set(alph, charCount.get(alph) - 1);
        willLose = !isPalindrome(charCount);
        charCount.set(alph, charCount.get(alph) + 1);
        
        if (!willLose) {
          break;
        }
      }
    }

    charCount.set(candidate, charCount.get(candidate) - 1);
    return getWinner(charCount, move + 1);
  }

  static boolean isPalindrome(List<Integer> charCount) {
    int odds = 0;
    for (var count : charCount) {
      if ((count & 1) == 1) {
        odds += 1;
      }
    }

    return odds <= 1;
  }
}