//Codeforces 1363A 
import java.util.Scanner;

public class CF1363A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrLen = SC.nextInt();
            int select = SC.nextInt(); // Elements selected
            int[] arr = new int[arrLen];
            for (int i = 0; i < arrLen; ++i)
                arr[i] = SC.nextInt();
            boolean oddSumPossible = checkOddPossibility(arr, select);
            out(oddSumPossible);
        }
    }

    // Checks and returns whether elements can be selected in a way that gives an odd sum
    static boolean checkOddPossibility(int[] arr, int select) {
        int odd = 0; // Number of odd elements
        int even = 0; // Number of even elements
        for (int i = 0; i < arr.length; ++i)
            if ((arr[i] & 1) == 0)
                even += 1;
            else
                odd += 1;
        if (select == arr.length)
            return odd % 2 == 1;
        else {
            if (select % 2 == 1)
                return odd >= select || (odd > 0 && even > 0);
            else
                return odd > 0 && even > 0;
        }
    }

    // Prints output corresponding to array
    static void out(boolean condition) {
        if (condition)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
