import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int people = scanner.nextInt();
    int showerPeriod = scanner.nextInt();
    List<Integer> switchingTimes = new ArrayList<Integer>(people);
    for (int p = 0; p < people; p += 1) {
      switchingTimes.add(scanner.nextInt());
    }
    
    int showerDuration = getShowerDuration(switchingTimes, showerPeriod);
    System.out.println(showerDuration);
  }

  static int getShowerDuration(List<Integer> switchingTimes, int showerPeriod) {
    int showerDuration = 0;
    int start = -1;
    int end = -1;
    for (var switchingTime : switchingTimes) {
      if (switchingTime > end) {
        showerDuration += end - start;
        start = switchingTime;
        end = switchingTime + showerPeriod;
      } else {
        end = switchingTime + showerPeriod;
      }
    }
    showerDuration += end - start;
    return showerDuration;
  }
}