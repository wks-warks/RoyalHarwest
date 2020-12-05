//Codeforces 1283B 
import java.util.Scanner;

public class CF1283B {
    static final Scanner SC = new Scanner(System.in);

    static int candies, kids, distributable;
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            candies = SC.nextInt();
            kids = SC.nextInt();
            distributable = maxDistributable();
            System.out.println(distributable);
        }
    }

    // Computes and returns maximum number of candies distributable
    static int maxDistributable() {
        return Math.min(candies, candies/kids * kids + kids/2);
    }
}
