import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int planes = scanner.nextInt();
    List<Integer> likes = new ArrayList<Integer>(planes);
    for (int p = 0; p < planes; p += 1) {
      likes.add(scanner.nextInt() - 1); // Converting to 0-index and adding to list
    }

    boolean loveTriangleExists = doesLoveTriangleExist(likes);
    System.out.println(loveTriangleExists ? "YES" : "NO");
  }

  static boolean doesLoveTriangleExist(List<Integer> likes) {
    for (int p = 0; p < likes.size(); p += 1) {
      int first = p;
      int second = likes.get(first);
      int third = likes.get(second);
      int last = likes.get(third);
      
      if (first == last) {
        return true;
      }
    }

    return false;
  }
}