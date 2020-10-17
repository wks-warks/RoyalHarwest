// Codeforces 706B
import java.util.Scanner;
import java.util.Arrays;

public class CF706B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int shops = SC.nextInt(); // Number of shops
        int[] prices = new int[shops]; // Prices of drink at different shops
        for (int s = 0; s < shops; ++s)
            prices[s] = SC.nextInt();
        int days = SC.nextInt(); // Days to consider
        int[] spends = new int[days]; // Spending capacity on different days
        for (int d = 0; d < days; ++d)
            spends[d] = SC.nextInt();
        int[] options = computeBuyingOptions(prices, spends);
        out(options);
    }

    // Computes and returns array containing number of buying options on different days
    static int[] computeBuyingOptions(int[] prices, int[] spends) {
        Arrays.sort(prices);
        int[] buyingOptions = new int[spends.length];
        for (int day = 0; day < spends.length; ++day)
            buyingOptions[day] = countNumbersUpto(prices, spends[day]);
        return buyingOptions;
    }

    // Computes and returns number of integers in the array(assumed-sorted) less than or equal to the limit specified
    static int countNumbersUpto(int[] arr, int limit) {
        int answer = 0;
        int lIdx = 0;
        int rIdx = arr.length-1;
        while (lIdx <= rIdx) {
            int mid = (lIdx + rIdx)/2;
            int midVal = arr[mid];
            if (midVal <= limit) {
                answer = mid+1; // converting to 1-based indexing
                lIdx = mid+1;
            }
            else
                rIdx = mid-1;
        }
        return answer;
    }


    // Prints output corresponding to given array
    static void out(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            System.out.println(arr[i]);
    }
}