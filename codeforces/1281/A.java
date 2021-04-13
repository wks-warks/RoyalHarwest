import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      String sentence = scanner.next();
      String language = identifyLanguage(sentence);
      System.out.println(language);
    }
  }

  static String identifyLanguage(String sentence) {
    switch(sentence.charAt(sentence.length() - 1)) {
      case 'o' : return "FILIPINO";
      case 'u' : return "JAPANESE";
      default : return "KOREAN";
    }
  }
}