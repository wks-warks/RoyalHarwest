// Codeforces 271A
import java.util.Scanner;

public class CF271A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int year = SC.nextInt();
        int nextDistinct = getDistinctDigitedYear(year+1); // Note that we only have to consider years after the given one, not the given one
        System.out.println(nextDistinct);
    }

    // Get next year all of whose digits are distinct
    static int getDistinctDigitedYear(int year) {
        boolean foundDistinctYear = checkIfDistinct(year);
        if (foundDistinctYear)
            return year;
        else
            return getDistinctDigitedYear(year+1);
    }

    // Checks and returns boolean whether or not the given year has completely distinct digits
    static boolean checkIfDistinct(int year) {
        String yearStr = Integer.toString(year);
        for (int d1 = 0; d1 < yearStr.length(); ++d1)
            for (int d2 = 0; d2 < yearStr.length(); ++d2) {
                if (d1 == d2)
                    continue;
                else {
                    char digit1 = yearStr.charAt(d1);
                    char digit2 = yearStr.charAt(d2);
                    if (digit1 == digit2) // Digits not distinct
                        return false;
                }
            }   
        return true; // All the digits are pairwise distinct
    }
}