import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t += 1) {
      int songLength = scanner.nextInt();
      List<Integer> notes = new ArrayList<Integer>(songLength);
      for (int n = 0; n < songLength; n += 1) {
        notes.add(scanner.nextInt());
      }

      int diversity = computeDiversity(notes);
      System.out.println(diversity);
    }
  }

  static int computeDiversity(List<Integer> notes) {
    Collections.sort(notes);

    int diversity = 0;
    int prev = -1;
    for (var note : notes) {
      if (prev < note) {
        diversity += 1;
      } else if (prev == note) {
        diversity += 1;
        note += 1;
      } else {
        continue;
      }
      prev = note;
    }

    return diversity;
  }
}