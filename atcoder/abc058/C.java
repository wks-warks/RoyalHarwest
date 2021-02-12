import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int stringCount = scanner.nextInt();
    List<String> strings = new ArrayList<String>(stringCount);
    for (int s = 0; s < stringCount; s += 1) {
      strings.add(scanner.next());
    }
    String longestPossible = getLongestPossible(strings);
    System.out.println(longestPossible);
  }

  static String getLongestPossible(List<String> strings) {
    List<Integer> frequency = new ArrayList<Integer>(26);
    for (int c = 0; c < 26; c += 1) {
      frequency.add(Integer.MAX_VALUE);
    }

    for (var string : strings) {
      List<Integer> charFrequency = getCharFrequency(string);
      for (int c = 0; c < 26; c += 1) {
        frequency.set(c, Math.min(frequency.get(c), charFrequency.get(c)));
      }
    }

    StringBuilder longestPossible = new StringBuilder();
    for (int c = 0; c < 26; c += 1) {
      for (int count = 0; count < frequency.get(c); count += 1) {
        longestPossible.append((char) ('a' + c));
      }
    }
    return longestPossible.toString();
  }

  static List<Integer> getCharFrequency(String string) {
    List<Integer> charFrequency = new ArrayList<Integer>(26);
    for (int c = 0; c < 26; c += 1) {
      charFrequency.add(0);
    }
    for (var ch : string.toCharArray()) {
      int deviation = ch - 'a';
      charFrequency.set(deviation, charFrequency.get(deviation) + 1);
    }
    return charFrequency;
  }
}