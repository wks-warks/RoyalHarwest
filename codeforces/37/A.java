import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    int maxStackSize = 0;
    Map<Integer, Integer> heightsAndCounts = new HashMap<Integer, Integer>();
    int bars = scanner.nextInt();
    for (int b = 0; b < bars; b += 1) {
      int height = scanner.nextInt();
      int prevCount = heightsAndCounts.containsKey(height) ? heightsAndCounts.get(height) : 0;
      int newCount = prevCount + 1;
      heightsAndCounts.put(height, newCount);

      maxStackSize = Math.max(maxStackSize, newCount);
    }

    System.out.println(maxStackSize + " " + heightsAndCounts.size());
  }
}