import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int multipleOf = scanner.nextInt();
    int modultoTo = scanner.nextInt();
    int moduloEquivalent = scanner.nextInt();
    boolean doable = checkDoability(multipleOf, modultoTo, moduloEquivalent);
    System.out.println(doable ? "YES" : "NO");
  }

  static boolean checkDoability(int multipleOf, int modultoTo, int moduloEquivalent) {
    // Naive approach : Since A and B <= 100, brute forcing (checking all multiples of A upto A*B should work)
    int gcd = Euclid.gcd(multipleOf, modultoTo);
    return moduloEquivalent % gcd == 0;
  }
}

class Euclid {
  static int gcd(int num1, int num2) {
    if (num1 == 0) {
      return num2;
    } else {
      return gcd(num2 % num1, num1);
    }
  }
}