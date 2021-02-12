import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> words = new ArrayList<String>(3);
    for (int s = 0; s < 3; s += 1) {
      words.add(scanner.next());
    }
    String acronym = getAcronym(words);
    System.out.println(acronym);
  }

  static String getAcronym(List<String> words) {
    StringBuilder acronym = new StringBuilder();
    for (var word : words) {
      acronym.append(word.charAt(0));
    }
    return acronym.toString().toUpperCase();
  }
}