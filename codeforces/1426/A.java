//Codeforces 1426A 
import java.util.Scanner;

public class CF1426A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int houseNumber = SC.nextInt();
            int housesPerFloor = SC.nextInt(); // Houses per floor above the first
            int petyaFloor = computeFloor(houseNumber, housesPerFloor);
            System.out.println(petyaFloor);
        }
    }

    // Computs and returns the floor of Petya's residence
    static int computeFloor(int houseNumber, int housesPerFloor) {
        if (houseNumber <= 2)
            return 1;
        else
            return (1 + LIG(houseNumber-2, housesPerFloor));
    }

    // Returns Least Integer Greater than or equal to (double) Numerator / Denominator
    static int LIG(int numerator, int denominator) {
        return (numerator + denominator - 1) / denominator;
    }
}
