//Codeforces 1316A 
import java.util.Scanner;

public class CF1316A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        while (tests-->0) {
            int n = SC.nextInt();
            int m = SC.nextInt();
            int total = 0;            
            for (int i = 0; i < n; ++i) {
                total += SC.nextInt();
            }
            System.out.println(Math.min(m, total));
        }
    }
}
