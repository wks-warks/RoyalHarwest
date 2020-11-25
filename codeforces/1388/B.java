//Codeforces 1388B 
import java.util.Scanner;

public class CF1388B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            String num = getNum(n);
            solution.append(num + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns required number (string) corresponding to length 'n' specified
    static String getNum(int n) {
        StringBuilder num = new StringBuilder();
        int eights = LIG(n, 4);
        for (int i = 0; i < n-eights; ++i)
            num.append('9');
        for (int e = 0; e < eights; ++e)
            num.append('8');
        return num.toString();
    }

    // Computes and returns Least Integer Greater than or equal to numerator / denominator
    static int LIG(int numerator, int denominator) {
        return (numerator + denominator - 1) / denominator;
    }
}
