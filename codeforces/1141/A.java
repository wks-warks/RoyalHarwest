//Codeforces 1141A 
import java.util.Scanner;

public class CF1141A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int initial = SC.nextInt();
        int desired = SC.nextInt();
        int movesRequired = computeMovesRequired(initial, desired);
        System.out.println(movesRequired);
    }

    // Computes and returns number of moves required to obtain the desired number, returns -1 if impossible
    static int computeMovesRequired(int initial, int desired) {
        if (desired % initial != 0)
            return -1;
        
        int productRequired = desired/initial;
        int twoMuls = 0; // Times we multiply by 2
        while (productRequired % 2 == 0) {
            twoMuls += 1;
            productRequired >>= 1;
        }
        int threeMuls = 0; // Times we multiply by 3
        while (productRequired % 3 == 0) {
            threeMuls += 1;
            productRequired /= 3;
        }

        if (productRequired != 1)
            return -1;
        else
            return twoMuls + threeMuls;
    }
}
