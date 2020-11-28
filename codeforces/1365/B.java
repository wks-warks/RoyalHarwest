//Codeforces 1365B 
import java.util.Scanner;

public class CF1365B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int arrSize = SC.nextInt();
            int[] arr = new int[arrSize];
            for (int i = 0; i < arrSize; ++i)
                arr[i] = SC.nextInt();
            int[] type = new int[arrSize];
            for (int i = 0; i < arrSize; ++i)
                type[i] = SC.nextInt();
            boolean canSort = checkSortability(arr, type);
            out(canSort);
        }
    }

    // Checks and returns whether the given array can be sorted using the type information available
    static boolean checkSortability(int[] arr, int[] type) {
        int defaultType = type[0];
        for (int i = 1; i < type.length; ++i)
            if (type[i] != defaultType)
                return true;
        
        for (int i = 1; i < arr.length; ++i)
            if (arr[i] < arr[i-1])
                return false;
        
        return true;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition ? "Yes" : "No");
    }
}
