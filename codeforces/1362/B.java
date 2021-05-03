import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t++) {
      int numbers = scanner.nextInt();

      Set<Integer> original = new HashSet<Integer>();
      for (int i = 0; i < numbers; i++) {
        original.add(scanner.nextInt());
      }

      int k = computeK(original);
      System.out.println(k);
    }
  }

  static int computeK(Set<Integer> original) {
    for(int candidate = 1; candidate <= 1024; candidate++) {
      Set<Integer> XorSet = new HashSet<Integer>();
      
      for (var num : original) {
        XorSet.add(num ^ candidate);
      }

      if (original.equals(XorSet)) {
        return candidate;
      }
    }

    return -1;
  }
}