import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();
    String abbrev = getAbbreviation(input);
    System.out.println(abbrev);
  }

  static String getAbbreviation(String input) {
    char initial = input.charAt(0);
    int num = input.length()-2;
    char terminal = input.charAt(num+1);
    return "" + initial + num + terminal;
  }
}