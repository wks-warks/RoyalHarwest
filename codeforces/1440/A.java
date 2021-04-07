import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int tests = scanner.nextInt();
    
    while (tests-->0) {
      int stringSize = scanner.nextInt();
      int price0 = scanner.nextInt();
      int price1 = scanner.nextInt();
      int priceChange = scanner.nextInt();
      String binString = scanner.next();

      int costToBuy = computeCostToBuy(price0, price1, priceChange, binString);
      System.out.println(costToBuy);
    }
  }

  static int computeCostToBuy(int price0, int price1, int priceChange, String binString) {
    int costToBuy = 0;
    int minPrice0 = Math.min(price0, priceChange + price1);
    int minPrice1 = Math.min(price1, priceChange + price0);

    for (var ch : binString.toCharArray()) {
      if (ch == '0') {
        costToBuy += minPrice0;
      } else {
        costToBuy += minPrice1;
      }
    }

    return costToBuy;
  }
}