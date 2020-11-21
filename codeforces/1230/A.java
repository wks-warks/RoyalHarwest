//Codeforces 1230A 
import java.util.Scanner;

public class CF1230A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int[] candies = new int[4];
        for (int i = 0; i < 4; ++i)
            candies[i] = SC.nextInt();
        if (isEvenlyDistributable(candies)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    // Checks and returns whether or not the bags can be evenly distributed
    static boolean isEvenlyDistributable(int[] candies) {
        int sum = 0;
        for (int bag : candies)
            sum += bag;
        
        int max = 0;
        for (int bag : candies)
            max = Math.max(max, bag);
        
        int min = Integer.MAX_VALUE;
        for (int bag : candies)
            min = Math.min(min, bag);
        
        return ((min+max) * 2 == sum) || (max * 2 == sum);
    }
}
