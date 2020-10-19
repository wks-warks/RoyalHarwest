// Codeforces 935A
import java.util.Scanner;

public class CF935A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int employees = SC.nextInt();
        int possibleTeamDivisions = getTeamDivisions(employees);
        System.out.println(possibleTeamDivisions);
    }

    // Computes and returns number of possible team divisions (follows simple brute force approach)
    static int getTeamDivisions(int employees) {
        int possibleDivisions = 0;
        for (int leaders = 1; 2 * leaders <= employees; ++leaders)
            if (employees % leaders == 0)
                possibleDivisions += 1;
        return possibleDivisions;
    }
}