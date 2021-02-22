import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int users = scanner.nextInt();
    List<Integer> ratings = new ArrayList<Integer>(users);
    for (int u = 0; u < users; u += 1) {
      ratings.add(scanner.nextInt());
    }
    String colors = getColors(ratings);
    System.out.println(colors);
  }

  static String getColors(List<Integer> ratings) {
    List<Integer> usersByGroup = new ArrayList<Integer>(Collections.nCopies(9, 0));
    /*
      0: gray
      1: brown
      2: green
      3: cyan
      4: blue
      5: yellow
      6: orange
      7: red
      8: any
    */

    int minColors = 0;
    int additionalInMax = 0;
    for (var rating : ratings) {
      boolean groupAssigned = false;
      for (int i = 1; i <= 8; i += 1) {
        int cap = 400*i;
        if (rating < cap) {
          if (usersByGroup.get(i-1) == 0) {
            usersByGroup.set(i-1, 1);
            minColors += 1;
          }
          groupAssigned = true;
          break;
        }
      }
      if (!groupAssigned) {
        additionalInMax += 1;
      }
    }
    int maxColors = minColors + additionalInMax;
    minColors = Math.max(minColors, 1);
    String colors = minColors + " " + maxColors;
    return colors;
  }
}