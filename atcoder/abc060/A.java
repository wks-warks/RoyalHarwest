import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> words = new ArrayList<String>(3);
    for (int w = 0; w < 3; w += 1) {
      words.add(scanner.next());
    }
    String output = getOutput(words);
    System.out.println(output);
  }

  static String getOutput(List<String> words) {
    if (canChain(words.get(0), words.get(1)) && canChain(words.get(1), words.get(2))) {
      return "YES";
    } else {
      return "NO";
    }
  }

  static boolean canChain(String first, String second) {
    return first.charAt(first.length()-1) == second.charAt(0);
  }
}