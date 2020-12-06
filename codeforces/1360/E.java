//Codeforces 1360E 
import java.util.Scanner;

public class CF1360E {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int polygonSize = SC.nextInt();
            String[] polygon = new String[polygonSize];
            for (int row = 0; row < polygonSize; ++row) {
                polygon[row] = SC.next();
            }
            boolean isPossible = checkPossibility(polygon);
            System.out.println(isPossible? "Yes" : "No");
        }
    }

    // Checks and returns whether or not a given arrangement is possible
    static boolean checkPossibility(String[] polygon) {
        for (int row = 0; row < polygon.length-1; ++row)
            for (int col = 0; col < polygon[row].length() - 1; ++col)
                if (polygon[row].charAt(col) == '1' && polygon[row+1].charAt(col) != '1' && polygon[row].charAt(col+1) != '1')
                    return false;
        
        return true;
    }
}
