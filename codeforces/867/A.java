import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    scanner.next();
    String abode = scanner.next();

    boolean moreToSF = checkMoreToSF(abode);
    System.out.println(moreToSF ? "YES" : "NO");
  }

  static boolean checkMoreToSF(String abode) {
    char prev = abode.charAt(0);
    int toSF = 0;
    int toSt = 0;

    for (var ch : abode.toCharArray()) {
      if (prev != ch) {
        if (ch == 'F') {
          toSF++;
        } else {
          toSt++;
        }
        prev = ch;
      }
    }

    return toSF > toSt;
  }
}