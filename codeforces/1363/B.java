import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      String binString = scanner.next();
      int operationsNeeded = computeOperationsNeeded(binString);
      System.out.println(operationsNeeded);
    }
  }

  static int computeOperationsNeeded(String binString) {
    List<Integer> onePrefixCount = new ArrayList<Integer>(Collections.nCopies(binString.length(), 0));
    onePrefixCount.set(0, binString.charAt(0) == '1' ? 1 : 0);
    int prev = onePrefixCount.get(0);

    for (int i = 1; i < binString.length(); i += 1) {
      onePrefixCount.set(i, binString.charAt(i) == '1' ? prev + 1 : prev);
      prev = onePrefixCount.get(i);
    }
    int oneCount = prev;

    int bestAnswer = binString.length();
    for (int i = 0; i < binString.length(); i += 1) {
      int onesTillHere = onePrefixCount.get(i);
      int zeroesTillHere = i + 1 - onesTillHere;
      int onesHereAfter = oneCount - onesTillHere;
      int zeroesHereAfter = binString.length() - 1 - i - onesHereAfter;

      bestAnswer = Math.min(bestAnswer, onesTillHere + zeroesHereAfter);
      bestAnswer = Math.min(bestAnswer, zeroesTillHere + onesHereAfter);
    }

    return bestAnswer;
  }
}