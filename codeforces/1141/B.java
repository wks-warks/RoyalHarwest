import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int hoursPerDay = scanner.nextInt();
    List<Boolean> resting = new ArrayList<Boolean>(hoursPerDay);
    for (int h = 0; h < hoursPerDay; h += 1) {
      resting.add(scanner.nextInt() == 1 ? true : false);
    }

    int maxRest = computeMaxRest(resting);
    System.out.println(maxRest);
  }

  static int computeMaxRest(List<Boolean> resting) {
    int maxConsecutive = 0;
    int consecutive = 0;

    for (int i = 0; i < resting.size() << 1; i += 1) {
      if (resting.get(i % resting.size())) {
        consecutive += 1;
        maxConsecutive = Math.max(maxConsecutive, consecutive);
      } else {
        consecutive = 0;
      }
    }

    return maxConsecutive;
  }
}