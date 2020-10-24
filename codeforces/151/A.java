// Codeforces 151A
import java.util.Scanner;

public class CF151A {
    public static void main(String[] args) {
        Ingredients specificied = new Ingredients();
        int toastsPerPerson = computeToastsPerPerson(specificied);
        System.out.println(toastsPerPerson);
    }

    // Computes and returns maximum possible number of toast per friend 
    static int computeToastsPerPerson(Ingredients specificied) {
        int toastsPerPerson =   minOf(
                                    specificied.drinkAvailable() / specificied.drinkPerToast(),
                                    specificied.totalSlices() / specificied.slicesPerToast(),
                                    specificied.saltAvailable() / specificied.saltPerToast()
                                );
        return toastsPerPerson;
    }

    // Computes and returns minimum of 3 integers
    static int minOf(int num1, int num2, int num3) {
        return Math.min(num1, Math.min(num2, num3));
    }
}

class Ingredients {
    static final Scanner SC = new Scanner(System.in);
    // Available ingredients
    private int friends, bottles, bottleCapacity, limes, slicesOfEach, salt;
    // Required ingredients
    private int drinkPerToastPP, saltPerToastPP; // Per Person amount required
    // 1 Slice of lime per drink

    // Sets ingredients based on inputs
    public Ingredients() {
        // Available
        friends = SC.nextInt();
        bottles = SC.nextInt();
        bottleCapacity = SC.nextInt();
        limes = SC.nextInt();
        slicesOfEach = SC.nextInt();
        salt = SC.nextInt();
        // Required
        drinkPerToastPP = SC.nextInt();
        saltPerToastPP = SC.nextInt();
    }

    // Returns amount of drink available
    public int drinkAvailable() {
        return bottles*bottleCapacity;
    }

    // Returns lime-slices available
    public int totalSlices() {
        return limes*slicesOfEach;
    }

    // Returns salt available
    public int saltAvailable() {
        return salt;
    }

    // Returns amount of drink required to serve one toast to each friend
    public int drinkPerToast() {
        return drinkPerToastPP * friends;
    }

    // Returns slices required to serve one toast to each friend
    public int slicesPerToast() {
        return friends;
    }

    // Returns amount of salt required to serve one toast to each friend
    public int saltPerToast() {
        return saltPerToastPP * friends;
    }
}