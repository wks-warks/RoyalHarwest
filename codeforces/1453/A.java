import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    for (int t = 0; t < tests; t += 1) {
      int n = scanner.nextInt();
      int m = scanner.nextInt();
      
      Set<Integer> present = new HashSet<Integer>();
      for (int i = 0; i < n; i += 1) {
        present.add(scanner.nextInt());
      }

      int answer = 0;
      for (int i = 0; i < m; i += 1) {
        if (present.contains(scanner.nextInt())) {
          answer += 1;
        }
      }
      
      System.out.println(answer);
    }
  }
}