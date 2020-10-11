// Codeforces 732A
import java.util.Scanner;

public class CF732A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int shovelPrice = SC.nextInt();
        int spareValue = SC.nextInt(); // value of spare coin availabe
        int minShovelsBought = computeMinShovelsBought(shovelPrice, spareValue);
        System.out.println(minShovelsBought);
    }

    // Computes and returns minimum number of shovels bought such that there isn't any leftover change
    static int computeMinShovelsBought(int shovelPrice, int spareValue) {
        int pieces = 1;
        while (changeRemains(shovelPrice, spareValue, pieces))
            pieces += 1;
        return pieces;
    }

    // Checks and returns boolean whether or not any change remains if 'pieces' number of shovels are bought
    static boolean changeRemains(int shovelPrice, int spareValue, int pieces) {
        int totalPrice = shovelPrice*pieces;
        return (totalPrice % 10 != 0) && (totalPrice % 10 != spareValue);
    }
}