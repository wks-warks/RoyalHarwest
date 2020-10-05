// Codeforces 136A
/*
* Correction:
* Input-
*  The first line contains one integer n (1 ≤ n ≤ 100) — the quantity of friends Petya invited to the party.
*  The second line contains n space-separated integers:
*  the i-th number is pi — the number of the friend who received a gift from friend number i.
*  It is guaranteed that each friend received exactly one gift.
*  It is possible that some friends do not share Petya's ideas of giving gifts to somebody else.
*  Those friends gave the gifts to themselves.
*/
import java.util.Scanner;

public class CF136A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numAttendees = SC.nextInt();
        int[] gaveTo = new int[numAttendees];
        for (int a = 0; a < numAttendees; ++a)
            gaveTo[a] = SC.nextInt();
        int[] gotFrom = getArrayGotFrom(gaveTo);
        out(gotFrom);
    }

    // Get array gotFrom based on array gaveTo
    static int[] getArrayGotFrom(int[] gaveTo) {
        int[] gotFrom = new int[gaveTo.length];
        for (int a = 0; a < gaveTo.length; ++a) {
            int receiverIdx = gaveTo[a] - 1;
            gotFrom[receiverIdx] = a + 1;
        }
        return gotFrom;
    }
    
    // Prints required output
    static void out(int[] arr) {
        for (int a = 0; a < arr.length; ++a)
            System.out.print(arr[a] + " ");
    }
}