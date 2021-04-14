import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    scanner.nextInt();
    
    String encoded = scanner.next();
    String decoded = decode(encoded);
    System.out.println(decoded);
  }

  static String decode(String encoded) {
    Deque<Character> decodedDeque = new ArrayDeque<Character>();

    if ((encoded.length() & 1) == 0) {
      for (int i = 0; i < encoded.length(); i += 1) {

        if ((i & 1) == 0) {
          decodedDeque.addFirst(encoded.charAt(i));
        } else {
          decodedDeque.add(encoded.charAt(i));
        }
      }
    } else {
      for (int i = 0; i < encoded.length(); i += 1) {

        if ((i & 1) == 0) {
          decodedDeque.add(encoded.charAt(i));
        } else {
          decodedDeque.addFirst(encoded.charAt(i));
        }
      }
    }

    StringBuilder decoded = new StringBuilder();
    while (decodedDeque.size() > 0) {
      decoded.append(decodedDeque.poll());
    }

    return decoded.toString();
  }
}