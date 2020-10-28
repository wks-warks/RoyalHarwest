//Codeforces 149A 
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class CF149A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int minGrowth = SC.nextInt();
        Integer[] growthByMonth = new Integer[12];
        for (int m = 0; m < 12; ++m)
            growthByMonth[m] = SC.nextInt();
        int monthsToWater = computeMonthsToWater(minGrowth, growthByMonth); // Minimum number of months to water
        System.out.println(monthsToWater);
    }

    // Checks and returns minimum number of months to water
    static int computeMonthsToWater(int minGrowth, Integer[] growthByMonth) {
        Arrays.sort(growthByMonth, Collections.reverseOrder()); // We shall target the months of maximal growth first
        int growth = 0;
        for (int i = 0; i < 12; ++i) {
            if (growth >= minGrowth)
                return i;
            growth += growthByMonth[i];
        }
        if (growth >= minGrowth)
            return 12;
        else
            return -1;
    }
}
