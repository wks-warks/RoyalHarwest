//Codeforces 1326A 
import java.util.Scanner;

public class CF1326A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            String num = getUglyNumber(n);
            System.out.println(num);
        }
    }

    // Computes and returns ugly number
    static String getUglyNumber(int n) {
        if (n == 1)
            return "-1"; // Ugly number of length 1 does not exist
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; ++i)
            sb.append('5');
        sb.append('8');
        return sb.toString();
    }
}
