import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    int cities = scanner.nextInt();
    int LimakHome = scanner.nextInt() - 1;
    List<Boolean> hasCriminal = new ArrayList<Boolean>(cities);
    for (int c = 0; c < cities; c += 1) {
      hasCriminal.add(scanner.nextInt() == 1);      
    }

    int criminalsCaught = computeCriminalsCaught(cities, hasCriminal, LimakHome);
    System.out.println(criminalsCaught);
  }

  static int computeCriminalsCaught(int cities, List<Boolean> hasCriminal, int LimakHome) {
    int caught = hasCriminal.get(LimakHome) ? 1 : 0;
    int leftPtr = LimakHome - 1;
    int rightPtr = LimakHome  + 1;

    while (leftPtr >= 0 && rightPtr < cities) {
      if (hasCriminal.get(leftPtr) && hasCriminal.get(rightPtr)) {
        caught += 2;
      }

      leftPtr -= 1;
      rightPtr +=1 ;
    }

    while (leftPtr >= 0) {
      caught += hasCriminal.get(leftPtr--) ? 1 : 0;
    }

    while (rightPtr < cities) {
      caught += hasCriminal.get(rightPtr++) ? 1 : 0;
    }

    return caught;
  }
}
