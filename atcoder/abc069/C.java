import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int seqLength = scanner.nextInt();
    List<Integer> sequence = new ArrayList<Integer>(seqLength);
    for (int e = 0; e < seqLength; e += 1) {
      sequence.add(scanner.nextInt());
    }

    boolean snukeHappy = verifySnukeHappiness(sequence);
    System.out.println(snukeHappy ? "Yes" : "No");
  }

  static boolean verifySnukeHappiness(List<Integer> sequence) {
    int mulOf4 = 0;
    int mulOf2 = 0;
    int mulOfNeither = 0;
    for (var num : sequence) {
      if (num % 4 == 0) {
        mulOf4 += 1;
      } else if (num % 2 == 0) {
        mulOf2 += 1;
      } else {
        mulOfNeither += 1;
      }
    }
    if (mulOf4 >= mulOfNeither) {
      return true;
    }
    if (mulOfNeither - mulOf4 == 1) {
      return mulOf2 == 0;
    }
    return false;
  }
}