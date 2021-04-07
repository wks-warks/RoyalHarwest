import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();

    while (tests-->0) {
      int tileTypes = scanner.nextInt();
      int sqaureSide = scanner.nextInt();
      
      boolean favourableTileFound = false;
      for (int type = 0; type < tileTypes; type += 1) {
        scanner.nextInt();
        int topRight = scanner.nextInt();
        int bottomLeft = scanner.nextInt();
        scanner.nextInt();
        
        favourableTileFound |= topRight == bottomLeft;
      }

      boolean constructable = ((sqaureSide & 1) == 0) && favourableTileFound;
      System.out.println(constructable ? "YES" : "NO");
    }
  }
}