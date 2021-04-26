import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt(); // Points: Misha's problem
    int b = scanner.nextInt(); // Points: Vasya's problem
    
    int c = scanner.nextInt(); // Time: Misha's solve time
    int d = scanner.nextInt(); // Time: Vasya's solve time

    String result = getResult(a, b, c, d);
    System.out.println(result);
  }

  static int getScore(int problemPoints, int timeTaken) {
    int score = Math.max(3*problemPoints/10, problemPoints - problemPoints*timeTaken/250);
    return score;
  }
  
  static String getResult(int a, int b, int c, int d) {
    int mScore = getScore(a, c);
    int vScore = getScore(b, d);

    return mScore > vScore ? "Misha" : mScore == vScore ? "Tie" : "Vasya";
  }
}