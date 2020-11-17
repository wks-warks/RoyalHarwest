//Codeforces 651A 
import java.util.Scanner;

public class CF651A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int charge1 = SC.nextInt();
        int charge2 = SC.nextInt();
        int playableTime = computeTime(charge1, charge2);
        System.out.println(playableTime);
    }

    // Computes and returns amount of time playable
    static int computeTime(int charge1, int charge2) {
        if (Math.min(charge1, charge2) == 0 || (charge1 + charge2 == 2))
            return 0;
        else
            return 1 + computeTime(Math.max(charge1, charge2) - 2, Math.min(charge1, charge2) + 1);
    }
}