//Codeforces 1324C 
import java.util.Scanner;

public class CF1324C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            String jumpConstraints = SC.next();
            int minJumpStrengh = computeStrengthRequired(jumpConstraints);
            System.out.println(minJumpStrengh);
        }
    }

    // Computes and returns minimum jump-strength required
    static int computeStrengthRequired(String jumpConstraints) {
        int pos, prevPos = -1;
        int strReq = 1; // Minimum Strength required
        for (pos = 0; pos < jumpConstraints.length(); ++pos)
            if (jumpConstraints.charAt(pos) == 'R') {
                strReq = Math.max(strReq, pos-prevPos);
                prevPos = pos;
            }
        strReq = Math.max(strReq, pos-prevPos);
        return strReq;
    }
}
