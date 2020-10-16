// Codeforces 1335B
import java.util.Scanner;

public class CF1335B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int strLen = SC.nextInt();
            int period = SC.nextInt();
            int distinctChars = SC.nextInt();
            String result = getRequiredString(strLen, period, distinctChars);
            System.out.println(result); 
        }
    }

    // Computes and returns the result string
    static String getRequiredString(int strLen, int period, int distinctChars) {
        StringBuilder sb = new StringBuilder();
        int sbLen = 0;
        while (strLen > sbLen++) {
            int offset = sbLen % distinctChars;
            sb.append((char)(97+offset));
        }
        return sb.toString();
    }
}