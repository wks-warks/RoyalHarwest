//Codeforces 1304A 
import java.util.Scanner;

public class CF1304A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int leftPos = SC.nextInt();
            int rightPos = SC.nextInt();
            int jump1 = SC.nextInt();
            int jump2 = SC.nextInt();
            int collisionTime = computeCollisionTime(leftPos, rightPos, jump1, jump2); // Time at which collision occurs, given that each rabbit jumps once every second
            System.out.println(collisionTime);
        }
    }

    // Computes and returns time until collision, returns -1 if collision never occurs
    static int computeCollisionTime(int leftPos, int rightPos, int jump1, int jump2) {
        if ((rightPos-leftPos)%(jump1+jump2) != 0)
            return -1;
        else
            return (rightPos-leftPos)/(jump1+jump2);
    }
}
