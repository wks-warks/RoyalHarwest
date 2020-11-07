//Codeforces 448A 
import java.util.Scanner;

public class CF448A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] cups = new int[3];
        for (int i = 0; i < 3; ++i)
            cups[i] = SC.nextInt();
        int[] medals = new int[3];
        for (int i = 0; i < 3; ++i)
            medals[i] = SC.nextInt();
        int shelves = SC.nextInt();
        boolean canAccommodate = checkAccommodation(cups, medals, shelves);
        out(canAccommodate);
    }

    // Checks and tells whether all the medals and cups can be accommodated based on the conditions
    static boolean checkAccommodation(int[] cups, int[] medals, int availableShelves) {
        int totalCups = arraySum(cups);
        int totalMedals = arraySum(medals);
        int cupShelves = LIG(totalCups, 5);
        int medalShelves = LIG(totalMedals, 10);
        return cupShelves + medalShelves <= availableShelves;
    }

    // Returns sum of elements of array
    static int arraySum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; ++i)
            sum += array[i];
        return sum;
    }

    // Computes and returns least integer greater than (double) numerator/denominator
    static int LIG(int numerator, int denominator) {
        return (numerator+denominator-1) / denominator;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
