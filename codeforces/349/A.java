//Codeforces 349A 
import java.util.Scanner;

public class CF349A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int people = SC.nextInt();
        boolean success = canServe(people);
        out(success);
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition? "YES" : "NO");
    }

    // Checks and returns whether the clerk can sell a ticket to each person
    static boolean canServe(int people) {
        int twentyFives = 0;
        int fifties = 0;
        // No need to keep a count of 100-bills
        for (int p = 0; p < people; ++p) {
            int bill = SC.nextInt();
            if (bill == 25)
                twentyFives += 1;
            else if (bill == 50) {
                if (twentyFives == 0)
                    return false;
                fifties += 1;
                twentyFives -= 1;
            }
            else {
                if (fifties > 0 && twentyFives > 0) {
                    fifties -= 1;
                    twentyFives -= 1;
                }
                else if (twentyFives >= 3) {
                    twentyFives -= 3;
                }
                else
                    return false;
            }
        }
        // Served all
        return true;
    }
}
