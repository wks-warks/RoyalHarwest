// Codeforces 1374B
import java.util.Scanner;

public class CF1374B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int  n = SC.nextInt();
            int movesToGet1 = computesMovesRequired(n);
            System.out.println(movesToGet1);
        }
    }

    // Computes and returns moves required to get 1 from n, returns -1 if impossible
    static int computesMovesRequired(int n) {
        /*
        * Represent n as 2pow(X) * 3pow(Y) * Other, where Other is coprime with 2, 3
        * If Other != 1, then return -1
        * Also, if X > Y, return -1
        */
        int pow2 = 0; // Power of 2 contained in n
        while (n % 2 == 0) {
            n >>= 1;
            pow2 += 1;
        }
        int pow3 = 0; // Power of 3 contained in n
        while (n % 3 == 0) {
            n /= 3;
            pow3 += 1;
        }
        if (n != 1 || pow2 > pow3)
            return -1;
        else
            return 2*pow3 - pow2;
    }
}