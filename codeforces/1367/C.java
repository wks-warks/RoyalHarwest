//Codeforces 1367C 
import java.util.Scanner;

public class CF1367C {
    static final Scanner SC = new Scanner(System.in);
    
    static int tables, dangerousDistance;
    static String occupancy;
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            tables = SC.nextInt();
            dangerousDistance = SC.nextInt();
            occupancy = SC.next();
            int occupancyAdditions = computeOccupancyAdditions();
            solution.append(occupancyAdditions + "\n");
        }
        System.out.print(solution);
    }

    // Computes and returns additional seats occupiable
    static int computeOccupancyAdditions() {
        int perSeatAddition = dangerousDistance + 1;
        int occupancyAdditions = 0;
        int prev = -perSeatAddition;
        for (int i = 0; i < occupancy.length(); ++i)
            if (occupancy.charAt(i) == '1') {
                int consideredDistance = i - prev - perSeatAddition;
                occupancyAdditions += consideredDistance / perSeatAddition;
                prev = i;
            }
        if (prev != occupancy.length()-1) {
            occupancyAdditions += (occupancy.length() - 1 - prev) / perSeatAddition;
        }
        return occupancyAdditions;
    }
}
