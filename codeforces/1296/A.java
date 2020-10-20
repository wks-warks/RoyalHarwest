// Codeforces 1296A
import java.util.Scanner;

public class CF1296A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int numElements = SC.nextInt();
            int[] elements = new int[numElements];
            for (int i = 0; i < numElements; ++i)
                elements[i] = SC.nextInt();
            boolean oddArrayPossible = checkOddPossibility(elements);
            out(oddArrayPossible);
        }
    }

    // Computes and returns whether an array with an odd sum is required
    static boolean checkOddPossibility(int[] elements) {
        boolean hasOdd = false;
        boolean hasEven = false;
        for (int i = 0; i < elements.length; ++i)
            if (elements[i] % 2 == 0)
                hasEven = true;
            else
                hasOdd = true;
        
        if (hasOdd == false || (hasEven == false && elements.length % 2 == 0))
            return false;
        else
            return true;
    }

    // Prints ouptut corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}