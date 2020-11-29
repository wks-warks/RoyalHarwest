//Codeforces 214A 
import java.util.Scanner;

public class CF214A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt();
        int m = SC.nextInt();
        int satisfactoryPairs = countSatisfactoryPairs(n, m);
        System.out.println(satisfactoryPairs);
    }

    // Counts and returns number of satisfactory pairs
    static int countSatisfactoryPairs(int n, int m) {
        int satisfactoryPairs = 0;
        for (int i = 0; i * i <= n; ++i) {
            for (int j = 0; j * j <= m; ++j) {
                if ((i*i+j == n) && (i+j*j == m))
                    satisfactoryPairs += 1;
            }
        }
        return satisfactoryPairs;
    }
}
