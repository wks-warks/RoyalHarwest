//Codeforces 1409B 
import java.util.Scanner;

public class CF1409B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int num1 = SC.nextInt();
            int num2 = SC.nextInt();
            int lim1 = SC.nextInt(); // Lower limit for num1 post reduction
            int lim2 = SC.nextInt(); // Lower limit for num2 post reduction
            int reductions = SC.nextInt();
            long minProduct = computeMinProduct(num1, num2, lim1, lim2, reductions);
            System.out.println(minProduct);
        }
    }

    // Computes and returns minimum product after reducing num1 and num2 as best possible
    static long computeMinProduct(int num1, int num2, int lim1, int lim2, int reductions) {
        int num1Reduction, num2Reduction;
        num1Reduction = Math.min(reductions, num1-lim1);
        reductions -= num1Reduction;
        num2Reduction = Math.min(reductions, num2-lim2);
        long firstCase = (long)(num1-num1Reduction) * (num2-num2Reduction);

        reductions += num1Reduction; // Restoring to original
        num2Reduction = Math.min(reductions, num2-lim2);
        reductions -= num2Reduction;
        num1Reduction = Math.min(reductions, num1-lim1);
        long secondCase = (long)(num1-num1Reduction) * (num2-num2Reduction);
        return Math.min(firstCase, secondCase);
    }
}
