//Codeforces 599A 
import java.util.Scanner;

public class CF599A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int houseToShop1 = SC.nextInt();
        int houseToShop2 = SC.nextInt();
        int shop1ToShop2 = SC.nextInt();
        shop1ToShop2 = Math.min(shop1ToShop2, houseToShop1 + houseToShop2);
        houseToShop1 = Math.min(houseToShop1, houseToShop2 + shop1ToShop2);
        houseToShop2 = Math.min(houseToShop2, houseToShop1 + shop1ToShop2);
        int minimumDistanceWalked = computeMinimumDistance(houseToShop1, houseToShop2, shop1ToShop2);
        System.out.println(minimumDistanceWalked);
    }
    
    // Computes and returns minimum distance to be walked by patrick
    static int computeMinimumDistance(int houseToShop1, int houseToShop2, int shop1ToShop2) {
        return Math.min(houseToShop1 + shop1ToShop2 + houseToShop2, houseToShop2 + shop1ToShop2 + houseToShop1);
    }
}
